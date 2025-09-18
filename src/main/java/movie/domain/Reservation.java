package movie.domain;

import java.util.List;

/**
 * 예매 정보를 나타내는 클래스. 예매 성공 시 생성되어 영수증과 같은 역할을 합니다.
 */
public class Reservation {
    private Screening screening;
    private List<Seat> reservedSeats;
    private long totalPrice;

    public Reservation(Screening screening, List<Seat> reservedSeats, long totalPrice) {
        this.screening = screening;
        this.reservedSeats = reservedSeats;
        this.totalPrice = totalPrice;
    }
}
