package movie.dto;

import java.util.List;

public class MovieResponse {
    Long id;
    String title;
    String runningTimeMinutes;
    List<ScreeningResponse> screenings;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRunningTimeMinutes() {
        return runningTimeMinutes;
    }

    public List<ScreeningResponse> getScreenings() {
        return screenings;
    }

    public MovieResponse(Long id, String title, String runningTimeMinutes, List<ScreeningResponse> screenings) {
        this.id = id;
        this.title = title;
        this.runningTimeMinutes = runningTimeMinutes;
        this.screenings = screenings;
    }
}
