const express = require('express');
const router = express.Router();
const {getDetails} = require('../controllers/details');
const {getDetailsByName} = require("../controllers/details");
const {getPersonByName, getPerson, getPersonAnimeWorks, getPersonVoiceWorks} = require("../controllers/personDetails");


router.get('/:id', async (req, res) => {
    const detailsId = req.params.id;

    try{
        const details = await getDetails(detailsId);
        res.render('details', {details});
    } catch(err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });

    }
});

router.post('/', async (req, res) => {

    try{
        const detailsName = (req.body.detailsName);

        const detailsData = await getDetailsByName(detailsName);
        if (!detailsData || detailsData.length === 0) {
            return res.status(404).json({ message: 'Anime details not found' });
        }

        console.log('id_person:', detailsData?.[0].malId);
        console.log("detailsData[0] =", detailsData[0]);

        const detailsMalId = detailsData[0].malId;

        const details = await getDetails(detailsMalId);

        res.render('details', { details });

    }
    catch(err){
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
})
module.exports = router;
