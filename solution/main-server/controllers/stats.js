// controllers/stats.js
const axios = require("axios");

const MONGO_SERVER = "http://localhost:3000";

async function getStatsByAnimeId(malId) {
  const res = await axios.get(`${MONGO_SERVER}/stats/${malId}`);
  return res.data;
}

module.exports = { getStatsByAnimeId };
