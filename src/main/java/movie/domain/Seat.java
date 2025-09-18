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
        if (this.isReserved) {
            throw new IllegalStateException("Seat " + this + " is already reserved.");
        }
        this.isReserved = true;
    }

    public long getPrice() {
        return this.grade.getPrice();
    }

    @Override
    public String toString() {
        return "" + row + col;
    }
}
