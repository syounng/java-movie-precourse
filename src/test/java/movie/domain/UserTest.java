package movie.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {

    @Test
    @DisplayName("사용자의 초기 포인트가 음수이면 예외가 발생한다.")
    void create_Fail_WithNegativeInitialPoints() {
        assertThatThrownBy(() -> new User(-100L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("포인트가 충분하면 정상적으로 사용된다.")
    void usePoints_Success() {
        // given
        User user = new User(5000L);

        // when
        user.usePoints(3000L);

        // then
        assertThat(user.getPoints()).isEqualTo(2000L);
    }

    @Test
    @DisplayName("보유한 포인트보다 많이 사용하면 예외가 발생한다.")
    void usePoints_Fail_WhenNotEnoughPoints() {
        // given
        User user = new User(1000L);

        // when & then
        assertThatThrownBy(() -> user.usePoints(1001L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not enough points.");
    }

    @ParameterizedTest
    @ValueSource(longs = {1000L, 1001L})
    @DisplayName("보유 포인트가 사용하려는 포인트보다 적으면 false를 반환한다.")
    void hasEnoughPoints(long pointsToUse) {
        // given
        User user = new User(1000L);

        // when
        boolean result = user.hasEnoughPoints(pointsToUse);

        // then
        if (pointsToUse <= 1000L) {
            assertThat(result).isTrue();
        }
        if (pointsToUse > 1000L) {
            assertThat(result).isFalse();
        }
    }
}
