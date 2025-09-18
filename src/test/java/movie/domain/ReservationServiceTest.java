package movie.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservationServiceTest {

    private ReservationService reservationService;
    private Movie avatar;
    private Movie titanic;
    private User user;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService();
        avatar = new Movie("Avatar", Duration.ofMinutes(180), 15000L);
        titanic = new Movie("Titanic", Duration.ofMinutes(195), 15000L);
        user = new User(0L);
    }

    @Test
    @DisplayName("시간이 겹치지 않는 여러 영화를 성공적으로 예매한다.")
    void reserve_MultipleScreenings_Success_WhenNoOverlap() {
        // given
        LocalDateTime avatarStartTime = LocalDateTime.of(2025, 9, 18, 14, 0);
        Screening avatarScreening = new Screening(avatar, avatarStartTime, List.of(new Seat('A', 1, SeatGrade.A)));

        LocalDateTime titanicStartTime = LocalDateTime.of(2025, 9, 18, 18, 0);
        Screening titanicScreening = new Screening(titanic, titanicStartTime, List.of(new Seat('B', 1, SeatGrade.S)));

        Map<Screening, List<Seat>> requests = Map.of(
                avatarScreening, List.of(new Seat('A', 1, SeatGrade.A)),
                titanicScreening, List.of(new Seat('B', 1, SeatGrade.S))
        );

        // when
        List<Reservation> reservations = reservationService.reserve(user, requests);

        // then
        assertThat(reservations).hasSize(2);
    }

    @Test
    @DisplayName("시간이 겹치는 여러 영화를 예매하면 실패한다.")
    void reserve_MultipleScreenings_Fail_WhenOverlapExists() {
        // given
        LocalDateTime avatarStartTime = LocalDateTime.of(2025, 9, 18, 14, 0); // Ends at 17:00
        Screening avatarScreening = new Screening(avatar, avatarStartTime, List.of(new Seat('A', 1, SeatGrade.A)));

        LocalDateTime titanicStartTime = LocalDateTime.of(2025, 9, 18, 16, 30); // Starts before Avatar ends
        Screening titanicScreening = new Screening(titanic, titanicStartTime, List.of(new Seat('B', 1, SeatGrade.S)));

        Map<Screening, List<Seat>> requests = Map.of(
                avatarScreening, List.of(new Seat('A', 1, SeatGrade.A)),
                titanicScreening, List.of(new Seat('B', 1, SeatGrade.S))
        );

        // when & then
        assertThatThrownBy(() -> reservationService.reserve(user, requests))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Screening times overlap.");
    }
}
