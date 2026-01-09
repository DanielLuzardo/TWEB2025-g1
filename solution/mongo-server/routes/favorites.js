const controller = require("../controllers/favorites");
const express = require("express");
const router = express.Router();



router.get("/:username", async (req, res) => {
  try {
    const favorites = await controller.getByUsername(req.params.username);
    res.json(favorites);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});
router.post("/", async (req, res) => {
  try {
    const newFavorite = await controller.create(req.body);
    res.status(201).json(newFavorite);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

module.exports = router;
