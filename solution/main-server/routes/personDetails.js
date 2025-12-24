const express = require('express');
const router = express.Router();
const { getPerson, getPersonByName} = require('../controllers/personDetails');

router.get('/:id', async (req, res) => {
    try {
        const personData = await getPerson(req.params.id);
        res.render('personDetails', {
            name: personData.personDetails.name,
            person: personData.personDetails,
            animeWorks: personData.animeWorks
        });
    } catch (err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
});
router.post('/', async (req, res) => {

    try{
        const personName = (req.body.personName);

        const person = await getPersonByName(personName);
        if (!person || person.length === 0) {
            return res.status(404).json({ message: 'Person not found' });
        }

        console.log('id_person:', person?.[0].personMalId);

        const personMalId = person[0].personMalId;
        const personData = await getPerson(personMalId);

        res.render('personDetails', {
            name: personData.personDetails.name,
            person: personData.personDetails,
            animeWorks: personData.animeWorks
        });

    }
    catch(err){
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
})
module.exports = router;
