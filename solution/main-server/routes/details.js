const express = require('express');
const router = express.Router();
const { getDetails, getDetailsByName, getAnimeStats, getAnimeRecommendations } = require('../controllers/details');

router.get('/:id', async (req, res) => {
    const detailsId = req.params.id;

    try {
        const [details, stats, recommendations] = await Promise.all([
            getDetails(detailsId),
            getAnimeStats(detailsId),
            getAnimeRecommendations(detailsId)
        ]);

        res.render('details', { details, stats, recommendations });
    } catch (err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
});

router.post('/', async (req, res) => {
    try {
        const detailsName = req.body.detailsName;

        const detailsData = await getDetailsByName(detailsName);
        if (!detailsData || detailsData.length === 0) {
            return res.status(404).json({ message: 'Anime details not found' });
        }

        const detailsMalId = detailsData[0].malId;

        const [details, stats, recommendations] = await Promise.all([
            getDetails(detailsMalId),
            getAnimeStats(detailsMalId),
            getAnimeRecommendations(detailsMalId)
        ]);

        res.render('details', { details, stats, recommendations });
    } catch (err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
});

module.exports = router;