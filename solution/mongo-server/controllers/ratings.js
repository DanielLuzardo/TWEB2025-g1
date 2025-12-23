const Rating = require("../models/ratings");

async function getAll() {
  return Rating.find().lean();
}

async function getByUsername(username) {
  return Rating.find({ username: username }).lean();
}

async function getByAnimeId(anime_id) {
  return Rating.find({ anime_id: anime_id }).lean();
}

async function create(ratingData) {
  const rating = new Rating(ratingData);
  return rating.save();
}

module.exports = { getAll, getByUsername, getByAnimeId, create };
