package movie.dto;

import java.util.List;

public class ReservationRequest {
    private Long screeningId;
    private List<String> seats;

    public ReservationRequest(Long screeningId, List<String> seats) {
        this.screeningId = screeningId;
        this.seats = seats;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public List<String> getSeats() {
        return seats;
    }
}
