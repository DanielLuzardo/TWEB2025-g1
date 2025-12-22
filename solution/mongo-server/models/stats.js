const mongoose = require("mongoose");

// score votes and percentage could be simplified into an array but keeping it this way for clarity
const statsSchema = new mongoose.Schema({
  mal_id: { type: Number, required: true, unique: true },
  watching: { type: Number, required: true },
  completed: { type: Number, required: true },
  on_hold: { type: Number, required: true },
  dropped: { type: Number, required: true },
  plan_to_watch: { type: Number, required: true },
  total: { type: Number, required: true },
  score_1_votes: { type: Number },
  score_1_percentage: { type: Number },
  score_2_votes: { type: Number },
  score_2_percentage: { type: Number },
  score_3_votes: { type: Number },
  score_3_percentage: { type: Number },
  score_4_votes: { type: Number },
  score_4_percentage: { type: Number },
  score_5_votes: { type: Number },
  score_5_percentage: { type: Number },
  score_6_votes: { type: Number },
  score_6_percentage: { type: Number },
  score_7_votes: { type: Number },
  score_7_percentage: { type: Number },
  score_8_votes: { type: Number },
  score_8_percentage: { type: Number },
  score_9_votes: { type: Number },
  score_9_percentage: { type: Number },
  score_10_votes: { type: Number },
  score_10_percentage: { type: Number },
});

//Create a mongoose model using the schema and export its
module.exports = mongoose.model("Stats", statsSchema);
