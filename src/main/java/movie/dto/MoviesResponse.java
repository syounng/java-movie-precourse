package movie.dto;

import java.util.List;

public class MoviesResponse {
    List<MovieResponse> movies;

    public List<MovieResponse> getMovies() {
        return movies;
    }

    public MoviesResponse(List<MovieResponse> movies) {
        this.movies = movies;
    }
}
