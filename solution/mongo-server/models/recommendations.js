const mongoose = require("mongoose");

const recommendationSchema = new mongoose.Schema({
  mal_id: { type: Number, required: true },
  recommendation_mal_id: { type: Number, required: true },
});

module.exports = mongoose.model("Recommendation", recommendationSchema);
