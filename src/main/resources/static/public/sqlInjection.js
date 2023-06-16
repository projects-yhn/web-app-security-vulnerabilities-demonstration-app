const APP_URL = 'http://localhost:8080'
let movies = null;
const container = document.getElementById("sql-injection-container");
const searchButton = document.getElementById("search-button");
const hintButton = document.getElementById("sqli-hints");
const hintText = document.getElementById("hint-text");
const sqliHint= "The+Matrix'+UNION+select+id,username,password,0,email+from+sql_injection_user_info--";

hintText.textContent = sqliHint;
searchButton.addEventListener("click", () => {
    const inputField = document.getElementById("search-input");
    const searchValue = inputField.value;
    console.log(searchValue);
    if (searchValue) {
        searchMovie(searchValue);
    } else {
        getAllMovies();
    }
});

const getAllMovies = () => {
    fetch(APP_URL + "/sql-injection/movies")
        .then(response => response.json())
        .then(moviesJson => movies = moviesJson)
        .then(voidPromise => displayMovies(movies, container))
        .then(p => console.log(movies))
        .catch(console.log);
}

const searchMovie  = (movieTitle) => {
    fetch(APP_URL + `/sql-injection/union-attack/movie?title=${movieTitle}`, {method: 'GET', redirect: 'follow'})
        .then(resp => {
            if (resp.redirected) {
                window.location.href = resp.url;
            }
            return resp.json()
        })
        .then(moviesJson => movies = moviesJson)
        .then(voidPromise => displayMovies(movies, container))
        .then(p => console.log(movies))
        .catch(console.log);
}

const displayMovies = (movies, container) => {
    container.innerHTML = "";
    const moviesContainer = document.createElement("div");
    for (const movie of movies) {
        const movieContainer = document.createElement("div")
        movieContainer.className = "movie";
        for (const movieKey in movie) {
            const movieDataSpan = document.createElement("span");
            movieDataSpan.textContent = movie[movieKey];
            movieDataSpan.className = "movieData";
            movieContainer.appendChild(movieDataSpan);
        }
        moviesContainer.appendChild(movieContainer);
    }
    container.appendChild(moviesContainer);
}


getAllMovies();
