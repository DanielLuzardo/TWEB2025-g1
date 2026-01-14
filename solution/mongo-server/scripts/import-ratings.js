const fs = require("fs");
const csv = require("csv-parser");
const mongoose = require("mongoose");
const Rating = require("../models/ratings");

const mongoURI = "mongodb://localhost:27017/anime_db";
const BATCH_SIZE = 50000; // Depending on the pc it can crash because of memory limits

async function importData() {
  try {
    await mongoose.connect(mongoURI);
    console.log("Connected to MongoDB");

    await Rating.deleteMany({});
    console.log("Cleared existing data");

    let batch = [];
    let totalInserted = 0;
    const startTime = Date.now();

    await new Promise((resolve, reject) => {
      const stream = fs
          .createReadStream("./data/ratings.csv")
          .pipe(csv())
          .on("data", async (row) => {
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
              await Rating.insertMany(toInsert, { ordered: false });
              totalInserted += toInsert.length;

              // Progreso con tiempo estimado
              const elapsed = (Date.now() - startTime) / 1000;
              const rate = totalInserted / elapsed;
              console.log(`Inserted ${totalInserted.toLocaleString()} records... (${Math.round(rate).toLocaleString()}/sec)`);

              stream.resume();
            }
          })
          .on("end", async () => {
            if (batch.length > 0) {
              await Rating.insertMany(batch, { ordered: false });
              totalInserted += batch.length;
            }
            resolve();
          })
          .on("error", reject);
    });

    const totalTime = ((Date.now() - startTime) / 1000 / 60).toFixed(2);
    console.log(`Done! Total inserted: ${totalInserted.toLocaleString()} in ${totalTime} minutes`);
    await mongoose.connection.close();
  } catch (error) {
    console.error("Error importing data:", error);
    process.exit(1);
  }
}

importData().catch(console.error);
importData().catch(console.error);