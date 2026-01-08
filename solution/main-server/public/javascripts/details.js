
const btnChars = document.getElementById('btn-chars');
const btnRecs = document.getElementById('btn-recs');
const charsSection = document.getElementById('chars');
const recsSection = document.getElementById('recs');


if (btnChars && btnRecs && charsSection && recsSection) {
    btnChars.addEventListener('click', () => {
        charsSection.style.display = 'block';
        recsSection.style.display = 'none';

        btnChars.classList.add('active');
        btnRecs.classList.remove('active');
    });

    btnRecs.addEventListener('click', () => {
        recsSection.style.display = 'block';
        charsSection.style.display = 'none';

        btnRecs.classList.add('active');
        btnChars.classList.remove('active');
    });
}