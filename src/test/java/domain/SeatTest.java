package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeatTest {

    @Test
    @DisplayName("행 혹은 열이 다르면 좌석이 중복되지 않는다")
    void method1() {
        // given
        var seat1 = new Seat("A", 1, SeatGrade.S);
        var seat2 = new Seat("C", 3, SeatGrade.A);

        // when
        var expected = true;
        var actual = seat1.isValidSeat(seat2);

        // then
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    @DisplayName("행 혹은 열이 같으면 좌석이 중복된다")
    void method2() {
        // given
        var seat1 = new Seat("A", 1, SeatGrade.S);
        var seat2 = new Seat("A", 1, SeatGrade.A);

        // when
        var expected = false;
        var actual = seat1.isValidSeat(seat2);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}