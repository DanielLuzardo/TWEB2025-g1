const controller = require("../controllers/profiles");
const express = require("express");
const router = express.Router();

/**
 * @swagger
 * tags:
 *   - name: Profiles
 *     description: Profiles endpoints
 */

/**
 * @swagger
 * components:
 *   schemas:
 *     Profile:
 *       type: object
 *       description: A user profile entry.
 *       required: [username]
 *       properties:
 *         username:
 *           type: string
 *           description: The username for this profile
 *           example: "Daniel"
 *         gender:
 *           type: string
 *           example: "Male"
 *         birthday:
 *           type: string
 *           example: "Dec 12"
 *         location:
 *           type: string
 *           example: "Canary Islands"
 *         joined:
 *           type: string
 *           example: Feb 15, 2008
 *         watching:
 *           type: integer
 *           example: 85
 *         completed:
 *           type: integer
 *           example: 35
 *         on_hold:
 *           type: integer
 *           example: 2
 *         dropped:
 *           type: integer
 *           example: 3
 *         plan_to_watch:
 *           type: integer
 *           example: 33
 *
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
 * /profiles/{username}:
 *   get:
 *     summary: Get profile by username
 *     description: Returns a single profile for the given username.
 *     tags: [Profiles]
 *     parameters:
 *       - in: path
 *         name: username
 *         required: true
 *         schema:
 *           type: string
 *         description: The username to search profile for
 *     responses:
 *       200:
 *         description: Profile found
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Profile'
 *       500:
 *         description: Internal Server Error
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Error'
 */
router.get("/:username", async (req, res) => {
  try {
     const profile = await controller.getByUsername(req.params.username);
     res.json(profile);
  } catch (error) {
    res.status(500).json({ error: "Internal Server Error" });
  }
});


module.exports = router;
