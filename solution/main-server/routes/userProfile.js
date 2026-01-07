const express = require('express');
const router = express.Router();
const { getProfile, getFavorites, getRatings } = require('../controllers/userProfile');

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

// Endpoint for loading more favorites
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

// Endpoint for loading more ratings
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