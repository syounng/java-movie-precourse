package movie.domain;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime;
    private long basePrice;

    public Movie(String title, Duration runningTime, long basePrice) {
        this.title = title;
        this.runningTime = runningTime;
        this.basePrice = basePrice;
    }

    public Duration getRunningTime() {
        return runningTime;
    }
}
