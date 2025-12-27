// controllers/favorites.js
const axios = require("axios");

const MONGO_SERVER = "http://localhost:3000";

async function getFavoritesByUsername(username) {
  const res = await axios.get(`${MONGO_SERVER}/favorites/${username}`);
  return res.data;
}

module.exports = { getFavoritesByUsername };
