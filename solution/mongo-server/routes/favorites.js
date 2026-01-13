const controller = require("../controllers/favorites");
const express = require("express");
const router = express.Router();

/**
 * @swagger
 * tags:
 *   - name: Favorites
 *     description: The favorites managing API
 */

/**
 * @swagger
 * components:
 *   schemas:
 *     Favorite:
 *       type: object
 *       required: [username, fav_type, id]
 *       properties:
 *         username:
 *           type: string
 *           description: The username of the owner
 *           example: "Daniel"
 *         fav_type:
 *           type: string
 *           description: The type of favorite (anime, character, people)
 *           example: "anime"
 *         id:
 *           type: integer
 *           description: The ID of the favorite item
 *           example: 21
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
 * /favorites/{username}:
 *   get:
 *     summary: Get favorites by username
 *     description: Retrieve all favorite items for a specific user.
 *     tags: [Favorites]
 *     parameters:
 *       - in: path
 *         name: username
 *         required: true
 *         schema:
 *           type: string
 *         description: The username to retrieve favorites for
 *     responses:
 *       200:
 *         description: Favorites found
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Favorite'
 *       500:
 *         description: Internal Server Error
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Error'
 */
router.get("/:username", async (req, res) => {
  try {
    const favorites = await controller.getByUsername(req.params.username);
    res.json(favorites);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});

module.exports = router;
