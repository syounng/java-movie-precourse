package movie.domain;

public class Seat {
    private char row;
    private int col;
    private SeatGrade grade;
    private boolean isReserved;

    public Seat(char row, int col, SeatGrade grade) {
        this.row = row;
        this.col = col;
        this.grade = grade;
        this.isReserved = false;
    }

    public boolean isAvailable() {
        return !isReserved;
    }

    public void reserve() {
        this.isReserved = true;
    }
}
