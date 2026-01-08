const fs = require("fs");
const csv = require("csv-parser");
const mongoose = require("mongoose");
const Favorite = require("../models/favorites");

const mongoURI = "mongodb://localhost:27017/anime_db";
const BATCH_SIZE = 10000; // Insert in batches to avoid memory issues

async function importData() {
  try {
    await mongoose.connect(mongoURI);
    console.log("Connected to MongoDB");

    await Favorite.deleteMany({});
    console.log("Cleared existing data");

    let batch = [];
    let totalInserted = 0;

    await new Promise((resolve, reject) => {
      fs.createReadStream("./data/favs.csv")
        .pipe(csv())
        .on("data", async (row) => {
          if (!row.username || row.username.trim() === "") return;

          batch.push({
            username: row.username,
            fav_type: row.fav_type || null,
            id: row.id === "" ? null : Number(row.id),
          });

          if (batch.length >= BATCH_SIZE) {
            const toInsert = batch;
            batch = [];
            await Favorite.insertMany(toInsert);
            totalInserted += toInsert.length;
            console.log(`Inserted ${totalInserted} records...`);
          }
        })
        .on("end", async () => {
          // Insertar los que queden
          if (batch.length > 0) {
            await Favorite.insertMany(batch);
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
