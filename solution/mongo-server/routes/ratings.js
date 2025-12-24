const controller = require("../controllers/ratings");
const express = require("express");
const router = express.Router();

router.get("/", async (req, res) => {
  try {
    const ratings = await controller.getAll();
    res.json(ratings);
  } catch (error) {
    res.status(500).json({ error: "Failed to fetch ratings" });
  }
});

router.get("/user/:username", async (req, res) => {
  try {
    const ratings = await controller.getByUsername(req.params.username);
    res.json(ratings);
  } catch (error) {
    res.status(500).json({ error: "Failed to fetch ratings for user" });
  }
});

router.get("/anime/:anime_id", async (req, res) => {
  try {
    const ratings = await controller.getByAnimeId(Number(req.params.anime_id));
    res.json(ratings);
  } catch (error) {
    res.status(500).json({ error: "Failed to fetch ratings for anime" });
  }
});

router.post("/", async (req, res) => {
  try {
    const newRating = await controller.create(req.body);
    res.status(201).json(newRating);
  } catch (error) {
    res.status(500).json({ error: "Failed to create rating" });
  }
});

module.exports = router;
