let favoritesOffset = 0;
let ratingsOffset = 0;
const limit = 20;
let username = '';

function initUserProfile(user, initialFavorites, initialRatings) {
    username = user;
    favoritesOffset = initialFavorites;
    ratingsOffset = initialRatings;
}

async function loadMoreFavorites() {
    const btn = document.getElementById('loadMoreFavoritesBtn');
    btn.textContent = 'Loading...';
    btn.disabled = true;

    try {
        const response = await axios.get(`/user/${username}/favorites?offset=${favoritesOffset}&limit=${limit}`);
        const data = response.data;

        const grid = document.getElementById('favoritesGrid');

        data.favorites.forEach(fav => {
            const div = document.createElement('div');
            div.className = 'favorite-item';

            let link = '#';
            if (fav.fav_type === 'anime') link = `/details/${fav.id}`;
            else if (fav.fav_type === 'character') link = `/characters/${fav.id}`;
            else if (fav.fav_type === 'people') link = `/personDetails/${fav.id}`;

            div.innerHTML = `
                <span class="fav-type">${fav.fav_type}</span>
                ${fav.imageUrl ? `<img src="${fav.imageUrl}" alt="${fav.name}" class="fav-image">` : ''}
                <a href="${link}" class="fav-link">${fav.name}</a>
            `;
            grid.appendChild(div);
        });

        favoritesOffset += data.favorites.length;

        document.getElementById('favoritesCount').textContent = favoritesOffset;

        if (!data.hasMore) {
            btn.style.display = 'none';
        } else {
            btn.textContent = 'Load More Favorites';
            btn.disabled = false;
        }
    } catch (error) {
        console.error('Error loading more favorites:', error);
        btn.textContent = 'Error - Try Again';
        btn.disabled = false;
    }
}

async function loadMoreRatings() {
    const btn = document.getElementById('loadMoreRatingsBtn');
    btn.textContent = 'Loading...';
    btn.disabled = true;

    try {
        const response = await axios.get(`/user/${username}/ratings?offset=${ratingsOffset}&limit=${limit}`);
        const data = response.data;

        const tbody = document.getElementById('ratingsBody');

        data.ratings.forEach(rating => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td><a href="/details/${rating.anime_id}">${rating.animeName}</a></td>
                <td><span class="status-badge status-${rating.status}">${rating.status}</span></td>
                <td>${rating.score}</td>
                <td>${rating.num_watched_episodes}</td>
            `;
            tbody.appendChild(tr);
        });

        ratingsOffset += data.ratings.length;

        document.getElementById('ratingsCount').textContent = ratingsOffset;

        if (!data.hasMore) {
            btn.style.display = 'none';
        } else {
            btn.textContent = 'Load More Ratings';
            btn.disabled = false;
        }
    } catch (error) {
        console.error('Error loading more ratings:', error);
        btn.textContent = 'Error - Try Again';
        btn.disabled = false;
    }
}