package domain;

import java.util.List;

public class Reservation {
    private Screening screening;
    private List<Seat> seatList;

    public Reservation(Screening screening, List<Seat> seatList) {
        validateSeats(seatList);
        this.screening = screening;
        this.seatList = seatList;
    }

    public long calculatedPrice() {
        return seatList.stream()
                .mapToLong(Seat::getSeatPrice)
                .sum();
    }

    public void validateSeats(List<Seat> seatList) {
        for (int i = 0; i < seatList.size(); i++) {
            for (int j = i + 1; j < seatList.size(); j++) {
                Seat s1 = seatList.get(i);
                Seat s2 = seatList.get(j);
                if (!s1.isValidSeat(s2)) {
                    throw new IllegalArgumentException("예매하려는 좌석이 서로 겹칩니다: "
                            + s1 + " vs " + s2);
                }
            }
        }
    }

}
