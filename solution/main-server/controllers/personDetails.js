const axios = require("axios");

async function getPerson(personId){
    try{
        if (!personId) {
            throw new Error("personId is required");
        }

        const personRes = await axios.get(`http://localhost:8082/personDetails/${personId}`);
       const person =  personRes.data;
       if (!person) {
           throw new Error(`${personId} not found`);
       }
    //The birthday was in a strange format, so I decided to reformat it
        if (person.birthday) {
            const date = new Date(person.birthday);
            person.birthdayFormatted = date.toLocaleDateString('en-GB', {
                day: '2-digit',
                month: 'long',
                year: 'numeric'
            });
        } else {
            person.birthdayFormatted = null;
        }

        return person;
    }
    catch (error) {
        console.error('Error in getPerson:', error.message);
        throw error;
    }

}

module.exports = {getPerson};