const Profile = require("../models/profiles");

async function getAll() {
  return Profile.find().lean();
}

async function getByUsername(username) {
  return Profile.findOne({ username: username }).lean();
}

async function create(profileData) {
  const profile = new Profile(profileData);
  return profile.save();
}

module.exports = { getAll, getByUsername, create };
