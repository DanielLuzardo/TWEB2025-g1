const Rating = require("../models/ratings");


/**
 * Get all rating entries for the specified username
 * @param {string} username - The username to filter ratings by.
 * @returns {Promise<Array<Object>>} Ratings of the user.
 */
async function getByUsername(username) {
  return Rating.find({ username: username }).lean();
}

/**
 * Get all rating entries for the specified anime ID
 * @param {number} anime_id - MyAnimeList anime ID.
 * @returns {Promise<Array<Object>>} Ratings for the anime.
 */
async function getByAnimeId(anime_id) {
  return Rating.find({ anime_id: anime_id }).lean();
}

/**
 * Create a new rating entry.
 * @param {Object} ratingData - Rating data to store.
 * @returns {Promise<Object>} The saved rating document.
 */
async function create(ratingData) {
  const rating = new Rating(ratingData);
  return rating.save();
}

module.exports = {getByUsername, getByAnimeId, create };
