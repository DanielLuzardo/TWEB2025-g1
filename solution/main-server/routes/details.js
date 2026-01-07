const express = require('express');
const router = express.Router();
const { getDetails, getDetailsByName, getAnimeStats, getAnimeRecommendations, getCharactersByAnime } = require('../controllers/details');

router.get('/:id', async (req, res) => {
    const detailsId = req.params.id;

    try {
        const [details, stats, recommendations, charactersAnime] = await Promise.all([
            getDetails(detailsId),
            getAnimeStats(detailsId),
            getAnimeRecommendations(detailsId),
            getCharactersByAnime(detailsId)

        ]);

        res.render('details', { details, stats, recommendations, charactersAnime });
    } catch (err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
});

router.post('/title', async (req, res) => {
    try {
        const detailsName = req.body.detailsName;

        const detailsData = await getDetailsByName(detailsName);
        if (!detailsData || detailsData.length === 0) {
            return res.render('index', {error: 'Anime not found'});
        }

        const detailsId = detailsData[0].malId;

        const [details, stats, recommendations, charactersAnime] = await Promise.all([
            getDetails(detailsId),
            getAnimeStats(detailsId),
            getAnimeRecommendations(detailsId),
            getCharactersByAnime(detailsId)

        ]);

        res.render('details', { details, stats, recommendations, charactersAnime });
    } catch (err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
});

module.exports = router;