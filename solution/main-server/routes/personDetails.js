const express = require('express');
const router = express.Router();
const { getPerson, getPersonByName} = require('../controllers/personDetails');
const {getPersonAnimeWorks, getPersonVoiceWorks} = require('../controllers/personDetails');


/**
 * @swagger
 * tags:
 *   - name: Person
 *     description: HTML pages for person details
 */

/**
 * @swagger
 * components:
 *   schemas:
 *     PersonNameRequest:
 *       type: object
 *       required: [personName]
 *       properties:
 *         personName:
 *           type: string
 *           description: Person name to search
 *           example: "Hayao Miyazaki"
 */

/**
 * @swagger
 * /personDetails/{id}:
 *   get:
 *     summary: Render person details page by ID
 *     description: Renders the HTML page with person details, anime works and voice works using the person ID.
 *     tags: [Person]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: person ID
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
/**
 * @swagger
 * /personDetails/name:
 *   post:
 *     summary: Render person details page by name
 *     description: Searches a person by name to obtain the ID and then renders the HTML details page.
 *     tags: [Person]
 *     requestBody:
 *       required: true
 *       content:
 *         application/x-www-form-urlencoded:
 *           schema:
 *             $ref: '#/components/schemas/PersonNameRequest'
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/PersonNameRequest'
 *     responses:
 *       200:
 *         description: HTML page rendered successfully (or index page if person not found)
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
