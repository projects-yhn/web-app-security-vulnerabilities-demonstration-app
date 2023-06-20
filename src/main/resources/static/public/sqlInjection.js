const APP_URL = 'http://localhost:8080'
let movies = null;
const container = document.getElementById("sql-injection-container");
const searchButton = document.getElementById("search-button");
const hintButton = document.getElementById("sqli-hints");
const hintText = document.getElementById("hint-text");
const sqliHint = "The+Matrix'+UNION+select+id,username,password,0,email,phone+from+sql_injection_user_info--";

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

const searchMovie = (movieTitle) => {
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
    moviesContainer.className = "movies";
    for (const movie of movies) {
        const movieContainer = prepareMovieContainer(movie);
        moviesContainer.appendChild(movieContainer);
    }
    container.appendChild(moviesContainer);
}

const prepareMovieContainer = movie => {
    const movieContainer = document.createElement("div");
    movieContainer.className = "movie";

    const imageContainer = document.createElement("img");
    imageContainer.className = 'movieImage'
    imageContainer.src = movie.imageUrl;

    const movieText = document.createElement('div');
    movieText.className = 'movieText';

    const movieTitle = document.createElement('h2');
    movieTitle.className = 'movieTitle';
    movieTitle.innerText = movie.title;
    movieText.appendChild(movieTitle);

    const movieDirector = document.createElement('h4');
    movieDirector.className = 'movieDirector';
    movieDirector.innerText = movie.director;
    movieText.appendChild(movieDirector);

    const movieYear = document.createElement('h5');
    movieYear.className = 'movieYear';
    movieYear.innerText = movie.year;
    movieText.appendChild(movieYear);

    const movieGenre = document.createElement('h5');
    movieGenre.className = 'movieGenre';
    movieGenre.innerText = movie.genre;
    movieText.appendChild(movieGenre);

    movieContainer.appendChild(imageContainer);
    movieContainer.appendChild(movieText);
    return movieContainer;
}


getAllMovies();
