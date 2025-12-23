const mongoose = require("mongoose");

const favoriteSchema = new mongoose.Schema({
  username: { type: String, required: true },
  fav_type: { type: String, required: true },
  id: { type: Number, required: true },
});

module.exports = mongoose.model("Favorite", favoriteSchema);
