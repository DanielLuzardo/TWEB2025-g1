const axios = require('axios');

const JAVA_SERVER = 'http://localhost:8082';
const MONGO_SERVER = 'http://localhost:3000';

/**
 * Get full anime details by an anime ID from the Java server.
 * @param {number|string} detailsId - Anime ID.
 * @returns {Promise<Object>} Anime details object.
 */
async function getDetails(detailsId) {
    const res = await axios.get(`${JAVA_SERVER}/details/${detailsId}`);
    return res.data;
}

/**
 * Get anime details by anime title from the Java server.
 * @param {string} detailsName - Anime title.
 * @returns {Promise<Array<Object>>} List of matching anime entries.
 */
async function getDetailsByName(detailsName) {
    const r = await axios.get(`${JAVA_SERVER}/details/title/${detailsName}`);
    return r.data;
}

/**
 * Get anime statistics by MyAnimeList ID from the Mongo server.
 * Returns null if the stats cannot be retrieved.
 *
 * @param {number|string} malId - MyAnimeList anime ID.
 * @returns {Promise<Object|null>} Anime statistics or null on error.
 */
async function getAnimeStats(malId) {
    try {
        const res = await axios.get(`${MONGO_SERVER}/stats/${malId}`);
        return res.data;
    } catch (error) {
        console.error(`Error fetching stats for anime ${malId}:`, error.message);
        return null;
    }
}

/**
 * Get anime recommendations and enrich them with anime summary details.
 *
 * @param {number|string} malId - Anime ID.
 * @returns {Promise<Array<Object>>} List of enriched anime recommendations.
 */
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


/**
 * Get characters associated with an anime and enrich them with character details.
 * Each character entry is augmented with detailed character information.
 *
 * @param {number|string} detailsId -  Anime ID.
 * @returns {Promise<Array<Object>>} List of characters with detailed information.
 */

async function getCharactersByAnime(detailsId){
    const charactersAnimeRes = await axios.get(`http://localhost:8082/details/${detailsId}/characters`);

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
                console.error(`Error fetching character ${characterId}:`, error.message);
                return character;
            }
        })
    );
    return characterAnime;

}

module.exports = { getDetails, getDetailsByName, getAnimeStats, getAnimeRecommendations, getCharactersByAnime };