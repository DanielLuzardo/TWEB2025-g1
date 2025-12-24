const axios = require('axios');

const STALE_MS = 30 * 1000;

async function getCharacter(characterId) {
    try {
        const characterRes = await axios.get(
            `http://localhost:8082/characters/${characterId}`
        );
        const character = characterRes.data;

        if (!character) {
            throw new Error('Character not found');
        }

        const animeWorksRes = await axios.get(
            `http://localhost:8082/characters/${characterId}/anime-works`
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
                            image: animeRes.data.imageUrl,
                            animeMalId: animeRes.data.malId || animeId
                        }
                    };
                } catch (error) {
                    console.error(`Error fetching anime ${animeId}:`, error.message);
                    return work;
                }
            })
        );

        return {
            details: character,
            animeWorks
        };
    } catch (error) {
        console.error('Error in getCharacter:', error.message);
        throw error;
    }
}


async function getCharacterByName(characterName) {
    const r = await axios.get('http://localhost:8082/characters', { params: { name: characterName } });
    return r.data;
}

module.exports = { getCharacter, getCharacterByName };