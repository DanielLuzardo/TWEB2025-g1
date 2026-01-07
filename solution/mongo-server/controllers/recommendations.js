const Recommendation = require("../models/recommendations");

async function getAll() {
  return Recommendation.find().lean();
}

async function getByMalId(mal_id) {
    return Recommendation.find({ mal_id: Number(mal_id) }).lean();
}
async function create(recommendationData) {
  const recommendation = new Recommendation(recommendationData);
  return recommendation.save();
}
module.exports = { getAll, getByMalId, create};
