// controllers/recommendations.js
const axios = require("axios");

const MONGO_SERVER = "http://localhost:3000";

async function getRecommendationsByAnimeId(malId) {
  const res = await axios.get(`${MONGO_SERVER}/recommendations/${malId}`);
  return res.data;
}

module.exports = { getRecommendationsByAnimeId };
