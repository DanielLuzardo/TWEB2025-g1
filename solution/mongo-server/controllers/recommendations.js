const Recommendation = require("../models/recommendations");


/**
 * Get all recommendations entries for the specified anime ID
 * @param {number|string} mal_id - MyAnimeList anime ID.
 * @returns {Promise<Array<Object>>} Recommendations for the anime.
 */
async function getByMalId(mal_id) {
    return Recommendation.find({ mal_id: Number(mal_id) }).lean();
}

/**
 * Create a new recommendation entry.
 * @param {Object} recommendationData - Recommendation data to store.
 * @returns {Promise<Object>} The saved recommendation document.
 */
async function create(recommendationData) {
  const recommendation = new Recommendation(recommendationData);
  return recommendation.save();
}

module.exports = {getByMalId, create };
