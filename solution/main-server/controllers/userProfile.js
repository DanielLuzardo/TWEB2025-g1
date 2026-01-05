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

async function getFavorites(username, limit = 20, offset = 0) {
    try {
        console.time('getFavorites-total');

        console.time('mongo-favorites');
        const res = await axios.get(`${MONGO_SERVER}/favorites/${username}`);
        console.timeEnd('mongo-favorites');

        const allFavorites = res.data;
        const totalFavorites = allFavorites.length;
        const favorites = allFavorites.slice(offset, offset + limit);

        console.time('enrich-favorites');
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
                        const details = await axios.get(`${JAVA_SERVER}/characters/${fav.id}/summary`);
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
        console.timeEnd('enrich-favorites');

        console.timeEnd('getFavorites-total');

        return {
            favorites: enrichedFavorites,
            total: totalFavorites,
            hasMore: offset + limit < totalFavorites
        };
    } catch (error) {
        console.error(`Error fetching favorites for ${username}:`, error.message);
        return { favorites: [], total: 0, hasMore: false };
    }
}

async function getRatings(username, limit = 20, offset = 0) {
    try {
        const res = await axios.get(`${MONGO_SERVER}/ratings/user/${username}`);
        let ratings = res.data;

        const totalRatings = ratings.length;

        //Pagination for improving loading times
        ratings = ratings.slice(offset, offset + limit);

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

        return {
            ratings: enrichedRatings,
            total: totalRatings,
            hasMore: offset + limit < totalRatings
        };
    } catch (error) {
        console.error(`Error fetching ratings for ${username}:`, error.message);
        return { ratings: [], total: 0, hasMore: false };
    }
}

module.exports = { getProfile, getFavorites, getRatings };