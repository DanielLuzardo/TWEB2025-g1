const controller = require("../controllers/stats");
const express = require("express");
const router = express.Router();


/**
 * @swagger
 * tags:
 *   - name: Stats
 *     description: The anime statistics managing API
 */

/**
 * @swagger
 * components:
 *   schemas:
 *     Stats:
 *       type: object
 *       required: [mal_id, watching, completed, on_hold, dropped, plan_to_watch, total]
 *       properties:
 *         mal_id:
 *           type: integer
 *           description: The ID from MyAnimeList
 *           example: 1234
 *         watching:
 *           type: integer
 *           description: Number of users currently watching
 *           example: 50000
 *         completed:
 *           type: integer
 *           description: Number of users who completed
 *           example: 100000
 *         on_hold:
 *           type: integer
 *           description: Number of users with anime on hold
 *           example: 5000
 *         dropped:
 *           type: integer
 *           description: Number of users who dropped
 *           example: 3000
 *         plan_to_watch:
 *           type: integer
 *           description: Number of users planning to watch
 *           example: 75000
 *         total:
 *           type: integer
 *           description: Total number of users
 *           example: 233000
 *         score_1_votes:
 *           type: integer
 *           description: Number of votes for score 1
 *           example: 100
 *         score_1_percentage:
 *           type: number
 *           description: Percentage of votes for score 1
 *           example: 0.5
 *         score_2_votes:
 *           type: integer
 *           example: 200
 *         score_2_percentage:
 *           type: number
 *           example: 1.0
 *         score_3_votes:
 *           type: integer
 *           example: 300
 *         score_3_percentage:
 *           type: number
 *           example: 1.5
 *         score_4_votes:
 *           type: integer
 *           example: 500
 *         score_4_percentage:
 *           type: number
 *           example: 2.5
 *         score_5_votes:
 *           type: integer
 *           example: 800
 *         score_5_percentage:
 *           type: number
 *           example: 4.0
 *         score_6_votes:
 *           type: integer
 *           example: 1500
 *         score_6_percentage:
 *           type: number
 *           example: 7.5
 *         score_7_votes:
 *           type: integer
 *           example: 3000
 *         score_7_percentage:
 *           type: number
 *           example: 15.0
 *         score_8_votes:
 *           type: integer
 *           example: 5000
 *         score_8_percentage:
 *           type: number
 *           example: 25.0
 *         score_9_votes:
 *           type: integer
 *           example: 4000
 *         score_9_percentage:
 *           type: number
 *           example: 20.0
 *         score_10_votes:
 *           type: integer
 *           example: 3500
 *         score_10_percentage:
 *           type: number
 *           example: 17.5
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
 * /stats/{mal_id}:
 *   get:
 *     summary: Get stats by Mal ID
 *     description: Retrieve statistics for a specific anime based on the MyAnimeList ID.
 *     tags: [Stats]
 *     parameters:
 *       - in: path
 *         name: mal_id
 *         required: true
 *         schema:
 *           type: integer
 *         description: The numeric MyAnimeList ID
 *     responses:
 *       200:
 *         description: Stats found
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Stats'
 *       404:
 *         description: Stats not found
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Error'
 *       500:
 *         description: Internal Server Error
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Error'
 */
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

module.exports = router;
