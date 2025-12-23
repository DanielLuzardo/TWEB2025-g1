const axios = require('axios');

const STALE_MS = 30 * 1000;

async function getCharacter(characterId) {
    try {
        const detailsRes = await axios.get(`http://localhost:8082/characters/${characterId}`);
        const character = detailsRes.data;

        if (!character) {
            throw new Error('Character not found');
        }

        if (character.animeWorks && character.animeWorks.length > 0) {
            const animeDetailsPromises = character.animeWorks.map(async (work) => {
                const animeId = work.id?.animeMalId;

                if (!animeId) {
                    console.warn('AnimeWork sin animeMalId:', work);
                    return work;
                }

                try {
                    const animeRes = await axios.get(`http://localhost:8082/details/${animeId}`);

                    return {
                        ...work,
                        anime: {
                            title: animeRes.data.title,
                            image: animeRes.data.imageUrl ,
                            animeMalId: animeRes.data.malId || animeId
                        }
                    };
                } catch (error) {
                    console.error(`Error fetching anime ${animeId}:`, error.message);
                    return work;
                }
            });

            character.animeWorks = await Promise.all(animeDetailsPromises);
        }

        return {
            details: character,
            animeWorks: character.animeWorks || []
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