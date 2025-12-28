const axios = require('axios');

const MONGO_SERVER = 'http://localhost:3000';

async function getStatsByAnimeId(malId) {
  try {
    const res = await axios.get(`${MONGO_SERVER}/stats/${malId}`);
    return res.data;
  } catch (error) {
    console.error(`Error fetching stats for anime ${malId}:`, error.message);
    return null;
  }
}

async function getRecommendationsByAnimeId(malId) {
  try {
    const res = await axios.get(`${MONGO_SERVER}/recommendations/${malId}`);
    return res.data;
  } catch (error) {
    console.error(`Error fetching recommendations for anime ${malId}:`, error.message);
    return [];
  }
}

module.exports = { getStatsByAnimeId, getRecommendationsByAnimeId };
