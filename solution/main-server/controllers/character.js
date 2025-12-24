const axios = require('axios');

const STALE_MS = 30 * 1000;

async function getCharacter(characterId) {
    const res = await axios.get(
        `http://localhost:8082/characters/${characterId}`
    );
    return res.data;
}

async function getCharacterAnimeWorks(characterId) {
    const animeWorksRes = await axios.get(
        `http://localhost:8082/characters/${characterId}/anime-works`
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
                console.error(`Error fetching anime ${animeId}`, error.message);
                return work;
            }
        })
    );

    return animeWorks;
}

async function getPersonVoiceWorks(characterId) {
    const voiceActorsRes = await axios.get(
        `http://localhost:8082/characters/${characterId}/voice-actors`
    );

    const voiceActors = await Promise.all(
        (voiceActorsRes.data || []).map(async (work) => {
            const personId = work.id?.personMalId;
            if (!personId) return work;

            try {
                const personRes = await axios.get(
                    `http://localhost:8082/personDetails/${personId}/summary`
                );

                return {
                    ...work,
                    person: personRes.data
                };
            } catch (error) {
                console.error(`Error fetching person ${personId}`, error.message);
                return work;
            }
        })
    );

    return voiceActors;
}



async function getCharacterByName(characterName) {
    const r = await axios.get('http://localhost:8082/characters', { params: { name: characterName } });
    return r.data;
}

module.exports = { getCharacter, getCharacterAnimeWorks, getPersonVoiceWorks, getCharacterByName};