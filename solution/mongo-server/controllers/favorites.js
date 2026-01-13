const Favorite = require("../models/favorites");

/**
 * Get all favorites entries from the database.
 * @returns {Promise<Array<Object>>} All favorites documents.
 */
async function getAll() {
  return Favorite.find().lean();
}

/**
 * Get all favorites entries for the specified username.
 * @param {string} username - The username to search for.
 * @returns {Promise<Array<Object>>} Favorites for the user.
 */
async function getByUsername(username) {
  return Favorite.find({ username: username }).lean();
}

module.exports = { getAll, getByUsername};
