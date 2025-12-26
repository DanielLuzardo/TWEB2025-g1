function sendCharacterRequest() {
    const animeName = document.getElementById('animeName').value.trim();

    axios.post('/characters', { animeName })
        .then(response => {
            document.body.innerHTML = response.data;
        })
        .catch(console.error);
}

function sendPersonRequest() {
    const personName = document.getElementById('personName').value.trim();

    axios.post('/personDetails', { personName })
        .then(response => {
            document.body.innerHTML = response.data;
        })
        .catch(console.error);
}

function sendDetailsRequest() {
    const detailsName = document.getElementById('detailsName').value.trim();

    axios.post('/details', { detailsName })
        .then(response => {
            document.body.innerHTML = response.data;
        })
        .catch(console.error);
}