package movie.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ScheduleTest {
    @Test
    void 시작_시간이_끝나는_시간보다_늦으면_예외를_던진다() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Schedule(
                LocalTime.of(2, 0),
                LocalTime.of(1, 0)
        ));
    }

    @Test
    void 다른_스케줄과_겹치면_false_리턴한다() {
        var schedule1 = new Schedule(
                LocalTime.of(1, 0),
                LocalTime.of(3, 0)
        );
        var schedule2 = new Schedule(
                LocalTime.of(2, 0),
                LocalTime.of(3, 0)
        );

        assertThat(schedule1.isValidSchedule(schedule2)).isFalse();
    }

    @Test
    void 다른_스케줄과_안_겹치면_true_리턴한다() {
        var schedule1 = new Schedule(
                LocalTime.of(1, 0),
                LocalTime.of(3, 0)
        );
        var schedule2 = new Schedule(
                LocalTime.of(4, 0),
                LocalTime.of(5, 0)
        );

        assertThat(schedule1.isValidSchedule(schedule2)).isTrue();
    }
}
