const Stats = require("../models/stats");

/**
 * Get all stats entries from the database.
 * @returns {Promise<Array<Object>>} All stats documents.
 */
async function getAll() {
  //lean() to return plain JS objects instead of Mongoose documents
  return Stats.find().lean();
}

/**
 * Get stats entry for the specified anime ID.
 * @param {number|string} mal_id - MyAnimeList anime ID.
 * @returns {Promise<Object|null>} Stats document or null if not found.
 */
async function getByMalId(mal_id) {
  return Stats.findOne({ mal_id: mal_id }).lean();
}

module.exports = { getAll, getByMalId};
