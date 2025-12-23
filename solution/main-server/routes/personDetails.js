const express = require('express');
const router = express.Router();
const { getPerson } = require('../controllers/personDetails');

router.get('/:id', async (req, res) => {
    try {
        const personData = await getPerson(req.params.id);
        res.render('personDetails', { person: personData });
    } catch (err) {
        console.error(err);
        res.status(500).render('error', { message: 'Server error' });
    }
});

module.exports = router;
