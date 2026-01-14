const express = require('express');
const router = express.Router();
const { getDetails, getDetailsByName, getAnimeStats, getAnimeRecommendations, getCharactersByAnime } = require('../controllers/details');

/**
 * @swagger
 * tags:
 *   - name: Details
 *     description: HTML pages for anime details
 */

/**
 * @swagger
 * components:
 *   schemas:
 *     DetailsTitleRequest:
 *       type: object
 *       required: [detailsName]
 *       properties:
 *         detailsName:
 *           type: string
 *           description: Anime title to search
 *           example: "Naruto"
 *
 *     ErrorPage:
 *       type: object
 *       properties:
 *         message:
 *           type: string
 *           example: "Server error"
 */

/**
 * @swagger
 * /details/{id}:
 *   get:
 *     summary: Render anime details page by ID
 *     description: Renders the HTML details page using the provided  anime ID.
 *     tags: [Details]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description:  Anime ID
 *     responses:
 *       200:
 *         description: HTML page rendered successfully
 *         content:
 *           text/html:
 *             schema:
 *               type: string
 *       500:
 *         description: Server error while rendering
 *         content:
 *           text/html:
 *             schema:
 *               type: string
 */
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
/**
 * @swagger
 * /details/title:
 *   post:
 *     summary: Render anime details page by title
 *     description: Searches the anime by title to obtain its ID and then renders the HTML details page.
 *     tags: [Details]
 *     requestBody:
 *       required: true
 *       content:
 *         application/x-www-form-urlencoded:
 *           schema:
 *             $ref: '#/components/schemas/DetailsTitleRequest'
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/DetailsTitleRequest'
 *     responses:
 *       200:
 *         description: HTML page rendered successfully (or index page if anime not found)
 *         content:
 *           text/html:
 *             schema:
 *               type: string
 *       500:
 *         description: Server error while rendering
 *         content:
 *           text/html:
 *             schema:
 *               type: string
 */

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
        res.status(500).render('error', { message: 'Anime not found' });
    }
});

module.exports = router;

