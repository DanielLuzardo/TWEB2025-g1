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

async function getPersonAnimeWorks(personId){
    const animeWorksRes = await axios.get(
        `http://localhost:8082/personDetails/${personId}/anime-works`
    );

    const animeWorks = await Promise.all(
        (animeWorksRes.data || []).map(async (work) => {
            const animeId = work.id?.animeMalId;
            if (!animeId) return work;

            try {
                const animeRes = await axios.get(
                    `http://localhost:8082/details/${animeId}/summary`
                );
                return {
                    ...work,
                    anime: animeRes.data
                };
            } catch (error) {
                console.error(`Error fetching anime ${animeId}:`, error.message);
                return work;
            }
        })
    );
    return animeWorks;
}

async function getPersonVoiceWorks(personId){
    const voiceWorksRes = await axios.get(
        `http://localhost:8082/personDetails/${personId}/voice-works`
    );

    const voiceWorks = await Promise.all(
        (voiceWorksRes.data || []).map(async (work) => {
            const voiceId = work.id?.animeMalId;
            const characterId = work.id?.characterMalId;

            let voice = null;
            let characterName = null;

            if (voiceId) {
                try {
                    const voiceRes = await axios.get(
                        `http://localhost:8082/details/${voiceId}/summary`
                    );
                    voice = voiceRes.data;
                } catch (error) {
                    console.error(`Error fetching voice ${voiceId}:`, error.message);
                }
            }

            if (characterId) {
                try {
                    const characterRes = await axios.get(
                        `http://localhost:8082/characters/${characterId}/name`
                    );
                    characterName = characterRes.data.name;
                } catch (error) {
                    console.error(`Error fetching character ${characterId}:`, error.message);
                }
            }

            return {
                ...work,
                voice,
                characterName
            };
        })
    );

    return voiceWorks;
}

async function getPersonByName(personName) {
    const r = await axios.get(`http://localhost:8082/personDetails/name/${personName}`);
    return r.data;
}

module.exports = {getPerson, getPersonByName, getPersonAnimeWorks, getPersonVoiceWorks};