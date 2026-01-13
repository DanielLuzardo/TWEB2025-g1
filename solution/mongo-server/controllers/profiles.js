const Profile = require("../models/profiles");


/**
 * Get the profile  for the specified username
 * @param {string} username - The username to search for.
 * @returns {Promise<Object|null>} The profile if found, otherwise null.
 */
async function getByUsername(username) {
  return Profile.findOne({ username: username }).lean();
}


module.exports = { getByUsername };
