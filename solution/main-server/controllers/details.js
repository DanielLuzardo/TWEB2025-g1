const axios = require('axios');

const JAVA_SERVER = 'http://localhost:8082';
const MONGO_SERVER = 'http://localhost:3000';

async function getDetails(detailsId) {
    const res = await axios.get(`${JAVA_SERVER}/details/${detailsId}`);
    return res.data;
}

async function getDetailsByName(detailsName) {
    const r = await axios.get(`${JAVA_SERVER}/details/title/${detailsName}`);
    return r.data;
}

async function getAnimeStats(malId) {
    try {
        const res = await axios.get(`${MONGO_SERVER}/stats/${malId}`);
        return res.data;
    } catch (error) {
        console.error(`Error fetching stats for anime ${malId}:`, error.message);
        return null;
    }
}

async function getAnimeRecommendations(malId) {
    const res = await axios.get(`${MONGO_SERVER}/recommendations/${malId}`);
    const animes = await Promise.all(
        (res.data || []).map(async (anime) => {
            const animeId = anime.recommendation_mal_id;
            if (!animeId) {
                return anime;
            }

            try {
                const animeRes = await axios.get(
                    `http://localhost:8082/details/${animeId}/summary`
                );

                return {
                    ...anime,
                    anime: animeRes.data
                };
            } catch (error) {
                console.error(`Error fetching anime ${animeId}`, error.message);
                return anime;
            }
        })
    );

    return animes;
}

async function getCharactersByAnime(detailsId){
    const charactersAnimeRes = await axios.get(`http://localhost:8082/details/${detailsId}/characters`);
    console.log(charactersAnimeRes.data[0]);

    const characterAnime = await Promise.all(
        (charactersAnimeRes.data || []).map(async (character) => {
            const characterId = character.id?.characterMalId;
            if (!characterId) return character;

            try {
                const characterRes = await axios.get(
                    `http://localhost:8082/characters/${characterId}`
                );
                return {
                    ...character,
                    characterDetails: characterRes.data
                };
            } catch (error) {
                console.error(`Error fetching anime ${characterId}:`, error.message);
                return character;
            }
        })
    );
    return characterAnime;

}

module.exports = { getDetails, getDetailsByName, getAnimeStats, getAnimeRecommendations, getCharactersByAnime };