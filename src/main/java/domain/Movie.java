package domain;

public class Movie {
    private final int runningTime;
    private final String title;

    public Movie(int runningTime, String title) {
        if(runningTime <= 0)
            throw new IllegalArgumentException();

        if(title.isBlank())
            throw new IllegalArgumentException();

        this.runningTime = runningTime;
        this.title = title;
    }
}
