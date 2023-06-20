DROP TABLE sql_injection_movies;


CREATE TABLE IF NOT EXISTS sql_injection_movies(
    id bigserial NOT NULL,
    title text NOT NULL,
    director text NOT NULL,
    year integer NOT NULL,
    genre text NOT NULL,
    imageUrl text NOT NULL,
    PRIMARY KEY(id)
    );
ALTER TABLE sql_injection_movies
    OWNER to "cySec";


INSERT INTO sql_injection_movies(title, director, year, genre, imageUrl)
VALUES('The Godfather', 'Francis Ford Coppola', 1972, 'Crime, Drama', 'https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg'),
      ('12 Angry Men', 'Sidney Lumet', 1957, 'Crime, Drama', 'https://upload.wikimedia.org/wikipedia/commons/b/b5/12_Angry_Men_%281957_film_poster%29.jpg'),
      ('The Matrix', 'Lana Wachowski and Lilly Wachowski', 1999, 'Action, Sci-FI', 'https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_.jpg');