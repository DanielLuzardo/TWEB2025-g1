const express = require('express');
const router = express.Router();
const { getCharacter } = require('../controllers/character');

router.get('/:id', async (req, res) => {
    const characterId = req.params.id;
    try {
        const characterData = await getCharacter(characterId);
        res.render('character', {
            name: characterData.details.name,
            character: characterData.details,
            animeWorks: characterData.animeWorks
        });
    } catch (err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
});

module.exports = router;
