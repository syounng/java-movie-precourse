package domain;

import java.util.Objects;

public class Seat {
    private final String row;
    private final int col;
    private final SeatGrade seatGrade;

    public Seat(String row, int col, SeatGrade seatGrade) {
        this.row = row;
        this.col = col;
        this.seatGrade = seatGrade;
    }

    public boolean isValidSeat(Seat seat) {
        return !(Objects.equals(row, seat.row) && col == seat.col);
    }

    public long getSeatPrice() {
        return seatGrade.getPrice();
    }

}
