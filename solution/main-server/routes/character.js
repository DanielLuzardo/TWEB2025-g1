const express = require('express');
const router = express.Router();
//const { getCharacter } = require('../controllers/character');
const {getCharacterByName} = require('../controllers/character');
const {
    getCharacter,
    getCharacterAnimeWorks,
    getPersonVoiceWorks
} = require('../controllers/character');


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

router.post('/', async (req, res) => {

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
        res.status(500).render('error', { message: 'Server error' });
    }
})

module.exports = router;
