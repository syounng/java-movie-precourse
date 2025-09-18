package movie.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeatTest {

    private Seat seat;

    @BeforeEach
    void setUp() {
        seat = new Seat('A', 1, SeatGrade.S); // 18,000 KRW
    }

    @Test
    @DisplayName("좌석의 등급에 맞는 가격을 올바르게 반환한다.")
    void getPrice() {
        assertThat(seat.getPrice()).isEqualTo(18000L);
    }

    @Test
    @DisplayName("예약되지 않은 좌석은 예약이 가능하다.")
    void reserve_Success_OnAvailableSeat() {
        // when
        seat.reserve();

        // then
        assertThat(seat.isAvailable()).isFalse();
    }

    @Test
    @DisplayName("이미 예약된 좌석을 다시 예약하면 예외가 발생한다.")
    void reserve_Fail_OnReservedSeat() {
        // given
        seat.reserve();

        // when & then
        assertThatThrownBy(() -> seat.reserve())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("already reserved");
    }
}
