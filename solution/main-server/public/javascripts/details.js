
const btnRecs = document.getElementById("btn-recs");
const btnChars = document.getElementById("btn-chars");
const recs = document.getElementById("recs");
const chars = document.getElementById("chars");


if (btnRecs && btnChars && recs && chars) {
    btnRecs.addEventListener("click", () => {
        recs.style.display = "block";
        chars.style.display = "none";
    });

    btnChars.addEventListener("click", () => {
        chars.style.display = "block";
        recs.style.display = "none";
    });
}
