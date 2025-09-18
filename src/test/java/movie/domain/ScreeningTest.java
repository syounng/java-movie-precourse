package movie.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScreeningTest {

    private Movie movie;
    private User user;

    @BeforeEach
    void setUp() {
        movie = new Movie("Avatar", Duration.ofMinutes(180), 15000L);
        user = new User(1000L);
    }

    @Test
    @DisplayName("영화를 성공적으로 예매한다.")
    void reserve_Success() {
        // given
        Seat seatA1 = new Seat('A', 1, SeatGrade.A);
        Seat seatA2 = new Seat('A', 2, SeatGrade.A);
        Screening screening = new Screening(movie, LocalDateTime.now(), List.of(seatA1, seatA2));
        List<Seat> seatsToReserve = List.of(seatA1, seatA2);

        // when
        Reservation reservation = screening.reserve(user, seatsToReserve);

        // then
        assertThat(reservation).isNotNull();
        assertThat(seatA1.isAvailable()).isFalse();
        assertThat(seatA2.isAvailable()).isFalse();
        assertThat(reservation.getTotalPrice()).isEqualTo(30_000L);
    }

    @Test
    @DisplayName("이미 예약된 좌석을 예매하면 실패한다.")
    void reserve_Fail_WhenSeatAlreadyReserved() {
        // given
        Seat seatA1 = new Seat('A', 1, SeatGrade.A);
        seatA1.reserve();
        Screening screening = new Screening(movie, LocalDateTime.now(), List.of(seatA1));
        List<Seat> seatsToReserve = List.of(seatA1);

        // when & then
        assertThatThrownBy(() -> screening.reserve(user, seatsToReserve))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("already reserved");
    }
}
