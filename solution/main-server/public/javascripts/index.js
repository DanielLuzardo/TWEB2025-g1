function handleSearch() {
    const type = document.getElementById('searchType').value;
    const name = document.getElementById('searchInput').value.trim();

    if (!name) return;

    switch (type) {
        case 'character':
            sendCharacterRequest(name);
            break;
        case 'person':
            sendPersonRequest(name);
            break;
        case 'anime':
            sendDetailsRequest(name);
            break;
        case 'user':
            sendUserRequest(name);
            break;
    }
}


function sendCharacterRequest(animeName) {
    axios.post('/characters', { animeName })
        .then(response => {
            document.body.innerHTML = response.data;
        })
        .catch(console.error);
}

function sendPersonRequest(personName) {
    axios.post('/personDetails', { personName })
        .then(response => {
            document.body.innerHTML = response.data;
        })
        .catch(console.error);
}

function sendDetailsRequest(detailsName) {
    axios.post('/details', { detailsName })
        .then(response => {
            document.body.innerHTML = response.data;
        })
        .catch(console.error);
}

/*
 * User search uses direct navigation instead of Axios POST.
 * Unlike anime, character, and person searches that return a list of results
 * to display on the same page, user search looks for an exact username match
 * and navigates directly to that user's profile page.
 * This is a design decision: there's no intermediate results list for users.
 */
function sendUserRequest(username) {
    window.location.href = `/user/${username}`;
}