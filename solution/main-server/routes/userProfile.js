const express = require('express');
const router = express.Router();
const { getProfile, getFavorites, getRatings } = require('../controllers/userProfile');

router.get('/:username', async (req, res) => {
    try {
        const username = req.params.username;
        /* We use Promise.all in orther to get the all the data in parallel and not doing it one after another*/
        const [profile, favorites, ratings] = await Promise.all([
            getProfile(username),
            getFavorites(username),
            getRatings(username)
        ]);

        if (!profile) {
            return res.status(404).render('error', { message: 'User not found' });
        }

        res.render('userProfile', {
            profile,
            favorites,
            ratings
        });
    } catch (error) {
        console.error('Error loading user profile:', error.message);
        res.status(500).render('error', { message: 'Error loading profile' });
    }
});

module.exports = router;