const fs = require("fs");
const csv = require("csv-parser");
const mongoose = require("mongoose");
const Recommendation = require("../models/recommendations");

const mongoURI = "mongodb://localhost:27017/anime_db";

async function importData() {
  try {
    // 1. Connect to MongoDB
    await mongoose.connect(mongoURI);
    console.log("Connected to MongoDB");

    // 2. Read the CSV and store in an array
    const results = [];

    await new Promise((resolve, reject) => {
      fs.createReadStream("./data/recommendations.csv")
        .pipe(csv())
        .on("data", (row) => {
          // Convert empty strings to null
          const cleanRow = {};
          for (const key in row) {
            cleanRow[key] = row[key] === "" ? null : Number(row[key]);
          }
          results.push(cleanRow);
        })
        .on("end", resolve)
        .on("error", reject);
    });

    console.log(`Read ${results.length} records from CSV`);

    // 3. Delete previous data and insert new data
    await Recommendation.deleteMany({});
    console.log("Cleared existing data");

    await Recommendation.insertMany(results);
    console.log(`Inserted ${results.length} records into MongoDB`);

    // 4. Close connection
    await mongoose.connection.close();
    console.log("Done!");
  } catch (error) {
    console.error("Error importing data:", error);
    process.exit(1);
  }
}

importData();
