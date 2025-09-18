package movie.domain;

public class User {
    private long points;

    public User(long points) {
        validateInitialPoints(points);
        this.points = points;
    }

    public void usePoints(long pointsToUse) {
        if (pointsToUse < 0) {
            throw new IllegalArgumentException("Points to use cannot be negative.");
        }
        if (!hasEnoughPoints(pointsToUse)) {
            throw new IllegalArgumentException("Not enough points.");
        }
        this.points -= pointsToUse;
    }

    public boolean hasEnoughPoints(long pointsToUse) {
        return this.points >= pointsToUse;
    }

    private void validateInitialPoints(long points) {
        if (points < 0) {
            throw new IllegalArgumentException("Initial points cannot be negative.");
        }
    }

    public long getPoints() {
        return points;
    }
}
