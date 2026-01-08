const express = require('express');
const router = express.Router();
const { getPerson, getPersonByName} = require('../controllers/personDetails');
const {getPersonAnimeWorks, getPersonVoiceWorks} = require('../controllers/personDetails');

router.get('/:id', async (req, res) => {
        const personId = req.params.id;
    try{
        const [person, animeWorks, voiceWorks] = await Promise.all(
            [

                getPerson(personId),
                getPersonAnimeWorks(personId),
                getPersonVoiceWorks(personId)
            ]
        );



        res.render('personDetails', {
            person,
            animeWorks,
            voiceWorks
        });
    } catch (err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
});
router.post('/name', async (req, res) => {
    try{
        const personName = (req.body.personName);

        const personData = await getPersonByName(personName);
        if (!personData || personData.length === 0) {
            return res.render('index', {error: 'Person not found'});
        }

        console.log('id_person:', personData?.[0].personMalId);

        const personMalId = personData[0].personMalId;

        const [person, animeWorks, voiceWorks] = await Promise.all([
            getPerson(personMalId),
            getPersonAnimeWorks(personMalId),
            getPersonVoiceWorks(personMalId)
        ]);

        res.render('personDetails', { person, animeWorks, voiceWorks });

    }
    catch(err){
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
})
module.exports = router;
