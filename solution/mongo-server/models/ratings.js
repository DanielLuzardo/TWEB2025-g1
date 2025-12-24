const mongoose = require("mongoose");

const ratingSchema = new mongoose.Schema({
  username: { type: String, required: true },
  anime_id: { type: Number, required: true },
  status: { type: String },
  score: { type: Number },
  is_rewatching: { type: Number },
  num_watched_episodes: { type: Number },
});

module.exports = mongoose.model("Rating", ratingSchema);
