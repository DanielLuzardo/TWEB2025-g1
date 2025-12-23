const controller = require("../controllers/profiles");
const express = require("express");
const router = express.Router();

//TODO: check error handling in all routers
router.get("/", async (req, res) => {
  try {
    const profiles = await controller.getAll();
    res.json(profiles);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

router.get("/:username", async (req, res) => {
  try {
    const profile = await controller.getByUsername(req.params.username);
    res.json(profile);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

router.post("/", async (req, res) => {
  try {
    const newProfile = await controller.create(req.body);
    res.status(201).json(newProfile);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

module.exports = router;
