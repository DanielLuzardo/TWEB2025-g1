function sendAnimeRequest() {
    const animeName = document.getElementById('animeName').value.trim();

    axios.post('/characters', { animeName })
        .then(response => {
            document.body.innerHTML = response.data;
        })
        .catch(console.error);
}
