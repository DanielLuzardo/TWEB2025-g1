const axios = require('axios');

const JAVA_SERVER = 'http://localhost:8082';
const MONGO_SERVER = 'http://localhost:3000';

async function getDetails(detailsId) {
    const res = await axios.get(`${JAVA_SERVER}/details/${detailsId}`);
    return res.data;
}

async function getDetailsByName(detailsName) {
    const r = await axios.get(`${JAVA_SERVER}/details`, { params: { title: detailsName } });
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
    try {
        const res = await axios.get(`${MONGO_SERVER}/recommendations/${malId}`);
        return res.data;
    } catch (error) {
        console.error(`Error fetching recommendations for anime ${malId}:`, error.message);
        return [];
    }
}

module.exports = { getDetails, getDetailsByName, getAnimeStats, getAnimeRecommendations };