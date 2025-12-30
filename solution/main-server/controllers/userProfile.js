const axios = require('axios');

const MONGO_SERVER = 'http://localhost:3000';
const JAVA_SERVER = 'http://localhost:8082';

async function getProfile(username) {
    try {
        const res = await axios.get(`${MONGO_SERVER}/profiles/${username}`);
        return res.data;
    } catch (error) {
        console.error(`Error fetching profile for ${username}:`, error.message);
        return null;
    }
}

async function getFavorites(username) {
    try {
        const res = await axios.get(`${MONGO_SERVER}/favorites/${username}`);
        const favorites = res.data;
        //We fetch in the Java Server for each favorites details, (image, name etc) in order to not only show the id
        const enrichedFavorites = await Promise.all(
            favorites.map(async (fav) => {
                try {
                    let name = null;
                    let imageUrl = null;

                    if (fav.fav_type === 'anime') {
                        const details = await axios.get(`${JAVA_SERVER}/details/${fav.id}/summary`);
                        name = details.data.title;
                        imageUrl = details.data.imageUrl;
                    } else if (fav.fav_type === 'character') {
                        const details = await axios.get(`${JAVA_SERVER}/characters/${fav.id}/name`);
                        name = details.data.name;
                        imageUrl = details.data.imageUrl || null;
                    } else if (fav.fav_type === 'people') {
                        const details = await axios.get(`${JAVA_SERVER}/personDetails/${fav.id}/summary`);
                        name = details.data.name;
                        imageUrl = details.data.imageUrl;
                    }

                    return { ...fav, name, imageUrl };
                } catch (error) {
                    console.error(`Error fetching details for ${fav.fav_type} ${fav.id}:`, error.message);
                    return { ...fav, name: `ID: ${fav.id}`, imageUrl: null };
                }
            })
        );

        return enrichedFavorites;
    } catch (error) {
        console.error(`Error fetching favorites for ${username}:`, error.message);
        return [];
    }
}

async function getRatings(username) {
    try {
        const res = await axios.get(`${MONGO_SERVER}/ratings/user/${username}`);
        const ratings = res.data;

        const enrichedRatings = await Promise.all(
            ratings.map(async (rating) => {
                try {
                    const details = await axios.get(`${JAVA_SERVER}/details/${rating.anime_id}/summary`);
                    return {
                        ...rating,
                        animeName: details.data.title,
                        animeImage: details.data.imageUrl
                    };
                } catch (error) {
                    console.error(`Error fetching anime ${rating.anime_id}:`, error.message);
                    return { ...rating, animeName: `Anime #${rating.anime_id}`, animeImage: null };
                }
            })
        );

        return enrichedRatings;
    } catch (error) {
        console.error(`Error fetching ratings for ${username}:`, error.message);
        return [];
    }
}

module.exports = { getProfile, getFavorites, getRatings };