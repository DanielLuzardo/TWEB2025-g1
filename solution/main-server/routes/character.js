const express = require('express');
const router = express.Router();
//const { getCharacter } = require('../controllers/character');
const {getCharacterByName} = require('../controllers/character');
const {
    getCharacter,
    getCharacterAnimeWorks,
    getPersonVoiceWorks,
    getCharacterCard
} = require('../controllers/character');

/**
 * @swagger
 * tags:
 *   - name: Character
 *     description: HTML pages for character details
 */

/**
 * @swagger
 * components:
 *   schemas:
 *     CharacterNameRequest:
 *       type: object
 *       required: [animeName]
 *       properties:
 *         animeName:
 *           type: string
 *           description: Character name to search
 *           example: "Naruto Uzumaki"
 */

/**
 * @swagger
 * /character/{id}:
 *   get:
 *     summary: Render character details page by ID
 *     description: Renders the HTML page with character details, anime works and voice actors using the character ID.
 *     tags: [Character]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: Character ID
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
    const characterId = req.params.id;

    try {
        const [character, animeWorks, voiceActors] = await Promise.all([
            getCharacter(characterId),
            getCharacterAnimeWorks(characterId),
            getPersonVoiceWorks(characterId)
        ]);

        res.render('character', {
            name: character.name,
            character,
            animeWorks,
            voiceActors
        });
    } catch (err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
});

/**
 * @swagger
 * /character/name:
 *   post:
 *     summary: Search characters by name
 *     description: Searches characters by name and renders a list of matching results.
 *     tags: [Character]
 *     requestBody:
 *       required: true
 *       content:
 *         application/x-www-form-urlencoded:
 *           schema:
 *             $ref: '#/components/schemas/CharacterNameRequest'
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/CharacterNameRequest'
 *     responses:
 *       200:
 *         description: HTML page rendered successfully (or index page if character not found)
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
router.post('/name', async (req, res) => {

    try{
        const animeName = (req.body.animeName);

        const characters = await getCharacterByName(animeName);
        if (!characters || characters.length === 0) {
            return res.render('index', {error: 'Character not found'});
        }
        console.log('characters returned:', characters);
        console.log('first:', characters?.[0].characterMalId);

        return res.render('characters-list', { characters });

    }
    catch(err){
        console.error(err);
        res.status(404).render('error', { message: 'Character not found' });
    }
})

module.exports = router;
