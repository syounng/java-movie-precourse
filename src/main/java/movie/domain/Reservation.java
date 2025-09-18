package movie.domain;

import java.util.List;

/**
 * 예매 정보를 나타내는 클래스. 예매 성공 시 생성되어 영수증과 같은 역할을 합니다.
 */
public class Reservation {
    private Screening screening;
    private List<Seat> reservedSeats;
    private long basePrice;

    public Reservation(Screening screening, List<Seat> reservedSeats, long basePrice) {
        this.screening = screening;
        this.reservedSeats = reservedSeats;
        this.basePrice = basePrice;
    }

    public long getBasePrice() {
        return basePrice;
    }

    public Screening getScreening() {
        return screening;
    }
}
