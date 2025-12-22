const Stats = require("../models/stats");
//Get all stats in the collection
async function getAll() {
  //lean() to return plain JS objects instead of Mongoose documents
  return Stats.find().lean();
}
//Get stats by mal_id
async function getByMalId(mal_id) {
  return Stats.findOne({ mal_id: mal_id }).lean();
}

//Create a new stat entry
async function create(statData) {
  const stat = new Stats(statData);
  return stat.save();
}
module.exports = { getAll, getByMalId, create };
