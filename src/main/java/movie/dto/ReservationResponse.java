package movie.dto;

import java.util.List;

public class ReservationResponse {
    Long reservationId;
    Long screeningId;
    List<String> seats;
    Long totalPrice;

    public ReservationResponse(Long reservationId, Long screeningId, List<String> seats, Long totalPrice) {
        this.reservationId = reservationId;
        this.screeningId = screeningId;
        this.seats = seats;
        this.totalPrice = totalPrice;
    }
}
