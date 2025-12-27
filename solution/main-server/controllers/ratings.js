// controllers/ratings.js
const axios = require("axios");

const MONGO_SERVER = "http://localhost:3000";

async function getRatingsByUsername(username) {
  const res = await axios.get(`${MONGO_SERVER}/ratings/user/${username}`);
  return res.data;
}

async function getRatingsByAnimeId(animeId) {
  const res = await axios.get(`${MONGO_SERVER}/ratings/anime/${animeId}`);
  return res.data;
}

module.exports = { getRatingsByUsername, getRatingsByAnimeId };
