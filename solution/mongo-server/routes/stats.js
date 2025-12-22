const controller = require("../controllers/stats");
const express = require("express");
const router = express.Router();

router.get("/", async (req, res) => {
  try {
    const stats = await controller.getAll();
    res.json(stats);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

router.get("/:mal_id", async (req, res) => {
  try {
    const stat = await controller.getByMalId(req.params.mal_id);
    if (!stat) {
      return res.status(404).json({ error: "Stat not found" });
    }
    res.json(stat);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

router.post("/", async (req, res) => {
  try {
    const newStat = await controller.create(req.body);
    res.status(201).json(newStat);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

module.exports = router;
