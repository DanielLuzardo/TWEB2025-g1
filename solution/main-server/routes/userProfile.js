const express = require('express');
const router = express.Router();
const { getProfile, getFavorites, getRatings } = require('../controllers/userProfile');

/**
 * @swagger
 * tags:
 *   name: User Profile
 *   description: Endpoints for retrieving user profile information, favorites, and ratings
 */

/**
 * @swagger
 * /user/{username}:
 *   get:
 *     summary: Get user profile page
 *     description: Retrieves complete user profile with initial favorites and ratings (first 20 of each)
 *     tags: [User Profile]
 *     parameters:
 *       - in: path
 *         name: username
 *         required: true
 *         schema:
 *           type: string
 *         description: The username to search for
 *         example: ishikisatoshi7
 *     responses:
 *       200:
 *         description: User profile page rendered successfully
 *       404:
 *         description: Username not found
 *       500:
 *         description: Server error
 */
router.get('/:username', async (req, res) => {
    try {
        const username = req.params.username;

        const [profile, favoritesData, ratingsData] = await Promise.all([
            getProfile(username),
            getFavorites(username, 20, 0),
            getRatings(username, 20, 0)
        ]);

        if (!profile) {
            return res.render('index', {error: 'Username not found'});
        }

        res.render('userProfile', {
            profile,
            favorites: favoritesData.favorites,
            totalFavorites: favoritesData.total,
            hasMoreFavorites: favoritesData.hasMore,
            ratings: ratingsData.ratings,
            totalRatings: ratingsData.total,
            hasMoreRatings: ratingsData.hasMore
        });
    } catch (error) {
        console.error('Error loading user profile:', error.message);
        res.status(500).render('error', { message: 'Error loading profile' });
    }
});

/**
 * @swagger
 * /user:
 *   post:
 *     summary: Search user profile by username
 *     description: Searches for a user by username submitted via form and renders their profile page
 *     tags: [User Profile]
 *     requestBody:
 *       required: true
 *       content:
 *         application/x-www-form-urlencoded:
 *           schema:
 *             type: object
 *             properties:
 *               username:
 *                 type: string
 *                 description: The username to search for
 *                 example: ishikisatoshi7
 *     responses:
 *       200:
 *         description: User profile page rendered successfully
 *       404:
 *         description: User not found
 *       500:
 *         description: Server error
 */
router.post('/', async (req, res) => {
    try {
        const username = req.body.username;

        const [profile, favoritesData, ratingsData] = await Promise.all([
            getProfile(username),
            getFavorites(username, 20, 0),
            getRatings(username, 20, 0)
        ]);

        if (!profile) {
            return res.status(404).render('error', { message: 'User not found' });
        }

        res.render('userProfile', {
            profile,
            favorites: favoritesData.favorites,
            totalFavorites: favoritesData.total,
            hasMoreFavorites: favoritesData.hasMore,
            ratings: ratingsData.ratings,
            totalRatings: ratingsData.total,
            hasMoreRatings: ratingsData.hasMore
        });
    } catch (error) {
        console.error('Error loading user profile:', error.message);
        res.status(500).render('error', { message: 'Error loading profile' });
    }
});

/**
 * @swagger
 * /user/{username}/favorites:
 *   get:
 *     summary: Get paginated user favorites
 *     description: Retrieves a paginated list of user favorites (anime, characters, people) with enriched data
 *     tags: [User Profile]
 *     parameters:
 *       - in: path
 *         name: username
 *         required: true
 *         schema:
 *           type: string
 *         description: The username
 *         example: ishikisatoshi7
 *       - in: query
 *         name: offset
 *         schema:
 *           type: integer
 *           default: 0
 *         description: Number of items to skip
 *       - in: query
 *         name: limit
 *         schema:
 *           type: integer
 *           default: 20
 *         description: Number of items to return
 *     responses:
 *       200:
 *         description: Favorites retrieved successfully
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 favorites:
 *                   type: array
 *                   items:
 *                     type: object
 *                     properties:
 *                       id:
 *                         type: integer
 *                       fav_type:
 *                         type: string
 *                       name:
 *                         type: string
 *                       imageUrl:
 *                         type: string
 *                 total:
 *                   type: integer
 *                 hasMore:
 *                   type: boolean
 *       500:
 *         description: Error loading favorites
 */
router.get('/:username/favorites', async (req, res) => {
    try {
        const username = req.params.username;
        const offset = parseInt(req.query.offset) || 0;
        const limit = parseInt(req.query.limit) || 20;

        const favoritesData = await getFavorites(username, limit, offset);

        res.json(favoritesData);
    } catch (error) {
        console.error('Error loading more favorites:', error.message);
        res.status(500).json({ error: 'Error loading favorites' });
    }
});

/**
 * @swagger
 * /user/{username}/ratings:
 *   get:
 *     summary: Get paginated user ratings
 *     description: Retrieves a paginated list of user anime ratings with enriched anime data
 *     tags: [User Profile]
 *     parameters:
 *       - in: path
 *         name: username
 *         required: true
 *         schema:
 *           type: string
 *         description: The username
 *         example: ishikisatoshi7
 *       - in: query
 *         name: offset
 *         schema:
 *           type: integer
 *           default: 0
 *         description: Number of items to skip
 *       - in: query
 *         name: limit
 *         schema:
 *           type: integer
 *           default: 20
 *         description: Number of items to return
 *     responses:
 *       200:
 *         description: Ratings retrieved successfully
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 ratings:
 *                   type: array
 *                   items:
 *                     type: object
 *                     properties:
 *                       anime_id:
 *                         type: integer
 *                       title:
 *                         type: string
 *                       score:
 *                         type: integer
 *                       status:
 *                         type: string
 *                 total:
 *                   type: integer
 *                 hasMore:
 *                   type: boolean
 *       500:
 *         description: Error loading ratings
 */
router.get('/:username/ratings', async (req, res) => {
    try {
        const username = req.params.username;
        const offset = parseInt(req.query.offset) || 0;
        const limit = parseInt(req.query.limit) || 20;

        const ratingsData = await getRatings(username, limit, offset);

        res.json(ratingsData);
    } catch (error) {
        console.error('Error loading more ratings:', error.message);
        res.status(500).json({ error: 'Error loading ratings' });
    }
});

module.exports = router;