// controllers/profiles.js
const axios = require("axios");

const MONGO_SERVER = "http://localhost:3000";

async function getProfile(username) {
  const res = await axios.get(`${MONGO_SERVER}/profiles/${username}`);
  return res.data;
}

module.exports = { getProfile };
