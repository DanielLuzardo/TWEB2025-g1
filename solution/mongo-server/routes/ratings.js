const controller = require("../controllers/ratings");
const express = require("express");
const router = express.Router();

/**
 * @swagger
 * tags:
 *   - name: Ratings
 *     description: Ratings endpoints
 */

/**
 * @swagger
 * components:
 *   schemas:
 *     Rating:
 *       type: object
 *       description: Anime rating entry.
 *       properties:
 *         username:
 *           type: string
 *           example: "ishikawas"
 *         anime_id:
 *           type: integer
 *           example: 59062
 *         status:
 *           type: string
 *           description: Current watching status
 *           example: "watching"
 *         score:
 *           type: integer
 *           description: User score (0–10, 0 if not rated)
 *           example: 0
 *         is_rewatching:
 *           type: integer
 *           description: 1 if rewatching, 0 otherwise
 *           example: 0
 *         num_watched_episodes:
 *           type: integer
 *           description: Number of episodes watched
 *           example: 4
 *
 *     RatingCreate:
 *       type: object
 *       description: Rating creation payload.
 *       required: [username ,anime_id]
 *       properties:
 *         username:
 *           type: string
 *           example: "ishikawas"
 *         anime_id:
 *           type: integer
 *           example: 59062
 *         status:
 *           type: string
 *           example: "watching"
 *         score:
 *           type: integer
 *           example: 0
 *         is_rewatching:
 *           type: integer
 *           example: 0
 *         num_watched_episodes:
 *           type: integer
 *           example: 4
 *
 *     Error:
 *       type: object
 *       properties:
 *         error:
 *           type: string
 *           example: "Internal Server Error"
 */

/**
 * @swagger
 * /ratings/user/{username}:
 *   get:
 *     summary: Get ratings by username
 *     description: Returns all ratings made by a specific user.
 *     tags: [Ratings]
 *     parameters:
 *       - in: path
 *         name: username
 *         required: true
 *         schema:
 *           type: string
 *         description: Username to retrieve ratings for
 *     responses:
 *       200:
 *         description: List of ratings
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Rating'
 *       500:
 *         description: Internal Server Error
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Error'
 */
router.get("/user/:username", async (req, res) => {
  try {
    const ratings = await controller.getByUsername(req.params.username);
    res.json(ratings);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

/**
 * @swagger
 * /ratings/anime/{anime_id}:
 *   get:
 *     summary: Get ratings by anime ID
 *     description: Returns all ratings for a specific anime.
 *     tags: [Ratings]
 *     parameters:
 *       - in: path
 *         name: anime_id
 *         required: true
 *         schema:
 *           type: integer
 *         description: MyAnimeList anime ID
 *     responses:
 *       200:
 *         description: List of ratings
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Rating'
 *       500:
 *         description: Internal Server Error
 */
router.get("/anime/:anime_id", async (req, res) => {
  try {
    const ratings = await controller.getByAnimeId(req.params.anime_id);
    res.json(ratings);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

/**
 * @swagger
 * /ratings:
 *   post:
 *     summary: Create a new rating
 *     description: Adds or updates a rating entry.
 *     tags: [Ratings]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/RatingCreate'
 *           examples:
 *             example:
 *               value:
 *                 username: "ishikawas"
 *                 anime_id: 59062
 *                 status: "watching"
 *                 score: 0
 *                 is_rewatching: 0
 *                 num_watched_episodes: 4
 *     responses:
 *       201:
 *         description: Rating created successfully
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Rating'
 *       500:
 *         description: Internal Server Error
 */
router.post("/", async (req, res) => {
  try {
    const newRating = await controller.create(req.body);
    res.status(201).json(newRating);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

module.exports = router;
