
/*
 * User search uses direct navigation instead of Axios POST.
 * Unlike anime, character, and person searches that return a list of results
 * to display on the same page, user search looks for an exact username match
 * and navigates directly to that user's profile page.
 * This is a design decision: there's no intermediate results list for users.
 */
function sendUserRequest() {
    const input = document.getElementById("searchInput");
    const username = input.value.trim();

    if (!username) return;

    window.location.href = `/user/${(username)}`;
}



document.addEventListener("DOMContentLoaded", () => {
    const selector = document.getElementById("searchType");

    const formAnime = document.getElementById("form-anime");
    const formCharacter = document.getElementById("form-character");
    const formPerson = document.getElementById("form-person");
    const formUser = document.getElementById("form-user");

    function updateForms() {
        formAnime.style.display = "none";
        formCharacter.style.display = "none";
        formPerson.style.display = "none";
        formUser.style.display = "none";

        if (selector.value === "anime") formAnime.style.display = "block";
        if (selector.value === "character") formCharacter.style.display = "block";
        if (selector.value === "person") formPerson.style.display = "block";
        if (selector.value === "user") formUser.style.display = "block";
    }

    selector.addEventListener("change", updateForms);
    updateForms();
});
