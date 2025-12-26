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