const fs = require("fs");
const csv = require("csv-parser");
const mongoose = require("mongoose");
const Profile = require("../models/profiles");

const mongoURI = "mongodb://localhost:27017/anime_db";

function parseNumber(value) {
  if (value === "" || value === undefined) return null;
  // Remove commas and convert to number
  const clean = value.toString().replace(/,/g, "");
  const num = Number(clean);
  return isNaN(num) ? null : num;
}

async function importData() {
  try {
    await mongoose.connect(mongoURI);
    console.log("Connected to MongoDB");

    const results = [];

    await new Promise((resolve, reject) => {
      fs.createReadStream("./data/profiles.csv")
        .pipe(csv())
        .on("data", (row) => {
          // Skip rows without a username
          if (!row.username || row.username.trim() === "") return;

          const cleanRow = {
            username: row.username,
            gender: row.gender || null,
            birthday: row.birthday || null,
            location: row.location || null,
            joined: row.joined || null,
            watching: parseNumber(row.watching),
            completed: parseNumber(row.completed),
            on_hold: parseNumber(row.on_hold),
            dropped: parseNumber(row.dropped),
            plan_to_watch: parseNumber(row.plan_to_watch),
          };
          results.push(cleanRow);
        })
        .on("end", resolve)
        .on("error", reject);
    });

    console.log(`Read ${results.length} records from CSV`);

    await Profile.deleteMany({});
    console.log("Cleared existing data");

    await Profile.insertMany(results);
    console.log(`Inserted ${results.length} records into MongoDB`);

    await mongoose.connection.close();
    console.log("Done!");
  } catch (error) {
    console.error("Error importing data:", error);
    process.exit(1);
  }
}

importData().catch(console.error);
