const fs = require("fs");
const csv = require("csv-parser");
const mongoose = require("mongoose");
const Rating = require("../models/ratings");

const mongoURI = "mongodb://localhost:27017/anime_db";
const BATCH_SIZE = 10000;
const LIMIT = 1000000; // We cant import 124M records, so limit to 1M for testing TODO: Check if it is necesary to remove limit

async function importData() {
  try {
    await mongoose.connect(mongoURI);
    console.log("Connected to MongoDB");

    await Rating.deleteMany({});
    console.log("Cleared existing data");

    let batch = [];
    let totalInserted = 0;
    let stopped = false;

    await new Promise((resolve, reject) => {
      const stream = fs
        .createReadStream("./data/ratings.csv")
        .pipe(csv())
        .on("data", async (row) => {
          if (stopped) return;
          if (!row.username || row.username.trim() === "") return;

          batch.push({
            username: row.username,
            anime_id: row.anime_id === "" ? null : Number(row.anime_id),
            status: row.status || null,
            score: row.score === "" ? null : Number(row.score),
            is_rewatching:
              row.is_rewatching === "" ? null : Number(row.is_rewatching),
            num_watched_episodes:
              row.num_watched_episodes === ""
                ? null
                : Number(row.num_watched_episodes),
          });

          if (batch.length >= BATCH_SIZE) {
            stream.pause();
            const toInsert = batch;
            batch = [];
            await Rating.insertMany(toInsert);
            totalInserted += toInsert.length;
            console.log(`Inserted ${totalInserted} records...`);

            if (LIMIT && totalInserted >= LIMIT) {
              stopped = true;
              stream.destroy();
              resolve();
            } else {
              stream.resume();
            }
          }
        })
        .on("end", async () => {
          if (!stopped && batch.length > 0) {
            await Rating.insertMany(batch);
            totalInserted += batch.length;
          }
          resolve();
        })
        .on("error", reject);
    });

    console.log(`Done! Total inserted: ${totalInserted}`);
    await mongoose.connection.close();
  } catch (error) {
    console.error("Error importing data:", error);
    process.exit(1);
  }
}

importData().catch(console.error);
