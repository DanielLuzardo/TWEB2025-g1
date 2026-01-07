const controller = require("../controllers/recommendations");
const express = require("express");
const router = express.Router();

router.get("/", async (req, res) => {
  try {
    const recommendations = await controller.getAll();
    res.json(recommendations);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

router.get("/:mal_id", async (req, res) => {
  try {
    const recommendation = await controller.getByMalId(req.params.mal_id);
    res.json(recommendation);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

router.post("/", async (req, res) => {
  try {
    const newRecommendation = await controller.create(req.body);
    res.status(201).json(newRecommendation);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

module.exports = router;
