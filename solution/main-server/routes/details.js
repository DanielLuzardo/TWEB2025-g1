const express = require('express');
const router = express.Router();
const {getDetails} = require('../controllers/details');


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


module.exports = router;
