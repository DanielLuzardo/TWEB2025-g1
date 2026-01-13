const controller = require("../controllers/recommendations");
const express = require("express");
const router = express.Router();

/**
 * @swagger
 * tags:
 *   - name: Recommendations
 *     description: The recommendations managing API
 */

/**
 * @swagger
 * components:
 *   schemas:
 *     Recommendation:
 *       type: object
 *       required: [mal_id ,recommendation_mal_id]
 *       properties:
 *         mal_id:
 *           type: integer
 *           description: The ID from MyAnimeList
 *           example: 1234
 *         recommendation_mal_id:
 *           type: integer
 *           description: The ID of the recommended anime
 *           example: 5678
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
 * /recommendations/{mal_id}:
 *   get:
 *     summary: Get recommendations by Mal ID
 *     description: Retrieve specific recommendations based on the MyAnimeList ID.
 *     tags: [Recommendations]
 *     parameters:
 *       - in: path
 *         name: mal_id
 *         required: true
 *         schema:
 *           type: integer
 *         description: The numeric MyAnimeList ID
 *     responses:
 *       200:
 *         description: Recommendations found
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Recommendation'
 *       500:
 *         description: Internal Server Error
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Error'
 */
router.get("/:mal_id", async (req, res) => {
  try {
    const recommendation = await controller.getByMalId(req.params.mal_id);
    res.json(recommendation);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

module.exports = router;
