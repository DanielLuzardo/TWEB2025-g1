const Favorite = require("../models/favorites");

async function getAll() {
  return Favorite.find().lean();
}

async function getByUsername(username) {
  return Favorite.find({ username: username }).lean();
}

async function create(favoriteData) {
  const favorite = new Favorite(favoriteData);
  return favorite.save();
}
module.exports = { getAll, getByUsername, create };
