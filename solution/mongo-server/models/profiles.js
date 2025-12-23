const mongoose = require("mongoose");

const profileSchema = new mongoose.Schema({
  username: { type: String, required: true, unique: true },
  gender: { type: String },
  birthday: { type: String },
  location: { type: String },
  joined: { type: String },
  watching: { type: Number },
  completed: { type: Number },
  on_hold: { type: Number },
  dropped: { type: Number },
  plan_to_watch: { type: Number },
});

module.exports = mongoose.model("Profile", profileSchema);
