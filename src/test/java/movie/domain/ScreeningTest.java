package movie.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ScreeningTest {

    @Test
    void 영화_상영의_스케줄_중에_겹치는_스케줄이_있다면_예외를_던진다() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Screening(
                    List.of(
                            new Schedule(
                                    LocalTime.of(1, 0),
                                    LocalTime.of(3, 0)
                            ),
                            new Schedule(
                                    LocalTime.of(1, 0),
                                    LocalTime.of(2, 0)
                            ),
                            new Schedule(
                                    LocalTime.of(1, 0),
                                    LocalTime.of(1, 0)
                            ),
                            new Schedule(
                                    LocalTime.of(1, 0),
                                    LocalTime.of(1, 0)
                            )
                    )
            );
        });
    }

    @Test
    void 영화_상영의_스케줄_중에_겹치는_스케줄이_없다면_예외를_던지지_않는다() {
        assertThatNoException().isThrownBy(() -> {
            new Screening(
                    List.of(
                            new Schedule(
                                    LocalTime.of(1, 0),
                                    LocalTime.of(3, 0)
                            ),
                            new Schedule(
                                    LocalTime.of(3, 1),
                                    LocalTime.of(4, 0)
                            )
                    )
            );
        });
    }


}