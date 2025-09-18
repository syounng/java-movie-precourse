package movie.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Screening {
    private Movie movie;
    private LocalDateTime startTime;
    private List<Seat> seats;

    public Screening(Movie movie, LocalDateTime startTime, List<Seat> seats) {
        this.movie = movie;
        this.startTime = startTime;
        this.seats = seats;
    }

    public Reservation reserve(User user, List<Seat> seatsToReserve) {
        // TODO: 예매 로직 구현
        // 1. 좌석 예매 가능 여부 확인
        // 2. 예매 정보 생성
        // 3. Reservation 객체 반환
        return null;
    }
}
