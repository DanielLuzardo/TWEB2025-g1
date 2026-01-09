const Profile = require("../models/profiles");


/**
 * Get the profile  for the specified username
 * @param {string} username - The username to search for.
 * @returns {Promise<Object|null>} The profile if found, otherwise null.
 */
async function getByUsername(username) {
  return Profile.findOne({ username: username }).lean();
}

/**
 * Create a new user profile.
 * @param {Object} profileData - Profile data to store.
 * @returns {Promise<Object>} The saved profile document.
 */
async function create(profileData) {
  const profile = new Profile(profileData);
  return profile.save();
}

module.exports = { getByUsername, create };
