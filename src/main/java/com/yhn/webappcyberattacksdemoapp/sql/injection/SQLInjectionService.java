package com.yhn.webappcyberattacksdemoapp.sql.injection;

import com.yhn.webappcyberattacksdemoapp.sql.injection.model.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SQLInjectionService {
    private final DataSource dataSource;

    public Object getUserInfoByUsernameSqlInjection(String username) {
        List<Map<String, Object>> users = new ArrayList<>();

        try (Connection dbConnection = dataSource.getConnection()) {

            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sql_injection_user_info WHERE username = '" + username + "'");
            while (resultSet.next()) {
                Map<String, Object> user = new HashMap<>();

                Long id = resultSet.getLong("id");
                String uName = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String cardNumber = resultSet.getString("card_number");

                user.put("userId", id);
                user.put("username", uName);
                user.put("firstName", firstName);
                user.put("lastName", lastName);
                user.put("address", address);
                user.put("email", email);
                user.put("phone", phone);
                user.put("cardNumber", cardNumber);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MovieDto> getMoviesByTitle(String title) {
        List<MovieDto> movies = new ArrayList<>();

        try (Connection dbConnection = dataSource.getConnection()) {

            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sql_injection_movies WHERE title = '" + title + "'");
            while (resultSet.next()) {
                movies.add(getMovieDtoFromResultSet(resultSet));
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MovieDto> getAllMovies() {
        List<MovieDto> movies = new ArrayList<>();

        try (Connection dbConnection = dataSource.getConnection()) {

            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sql_injection_movies");
            while (resultSet.next()) {
                movies.add(getMovieDtoFromResultSet(resultSet));
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MovieDto getMovieDtoFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String movieTitle = resultSet.getString("title");
        String genre = resultSet.getString("genre");
        Integer year = resultSet.getInt("year");
        String director = resultSet.getString("director");
        String imageUrl = resultSet.getString("imageUrl");

        MovieDto movieDto = new MovieDto();
        movieDto.setId(id);
        movieDto.setTitle(movieTitle);
        movieDto.setDirector(director);
        movieDto.setYear(year);
        movieDto.setGenre(genre);
        movieDto.setImageUrl(imageUrl);
        return movieDto;
    }


}
