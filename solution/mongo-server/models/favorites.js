const mongoose = require("mongoose");

const favoriteSchema = new mongoose.Schema({
  username: { type: String, required: true },
  fav_type: { type: String, required: true },
  id: { type: Number, required: true },
});
// index for speed searching my username
favoriteSchema.index({ username: 1 });


module.exports = mongoose.model("Favorite", favoriteSchema);
