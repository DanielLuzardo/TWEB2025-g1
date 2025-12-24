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
        const animeWorksRes = await axios.get(
            `http://localhost:8082/personDetails/${personId}/anime-works`
        );

        const animeWorks = await Promise.all(
            (animeWorksRes.data || []).map(async (work) => {
                const animeId = work.id?.animeMalId;
                if (!animeId) return work;

                try {
                    const animeRes = await axios.get(
                        `http://localhost:8082/details/${animeId}`
                    );
                    return {
                        ...work,
                        anime: {
                            title: animeRes.data.title,
                            image: animeRes.data.imageUrl

                        }
                    };
                } catch (error) {
                    console.error(`Error fetching anime ${animeId}:`, error.message);
                    return work;
                }
            })
        );
        

        return {
            personDetails: person,
            animeWorks
        };
    }
    catch (error) {
        console.error('Error in getPerson:', error.message);
        throw error;
    }

}

module.exports = {getPerson};