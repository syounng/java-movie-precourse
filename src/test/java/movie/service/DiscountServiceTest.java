package movie.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import movie.domain.Movie;
import movie.domain.PaymentMethod;
import movie.domain.Reservation;
import movie.domain.Screening;
import movie.domain.Seat;
import movie.domain.SeatGrade;
import movie.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountServiceTest {

    private DiscountService discountService;
    private Movie movie;
    private Seat seat;
    private Screening screening;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService();
        movie = new Movie("Test Movie", Duration.ofMinutes(120), 0L);
        seat = new Seat('A', 1, SeatGrade.S); // 18,000 KRW
        // MovieDay + TimeDiscount applicable screening
        screening = new Screening(movie, LocalDateTime.of(2025, 9, 10, 10, 0), List.of(seat));
        reservation = new Reservation(screening, List.of(seat), seat.getPrice());
    }

    @Test
    @DisplayName("모든 할인이 적용되고, 사용자의 포인트가 정상적으로 차감된다.")
    void calculateFinalPrice_SuccessAndPointsDeducted() {
        // given
        User user = new User(10000L);
        long pointsToUse = 1000L;
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;

        // when
        long finalPrice = discountService.calculateFinalPrice(reservation, user, pointsToUse, paymentMethod);

        // then
        // Base price: 18,000
        // After Movie Day (10%): 16,200
        // After Time (2000): 14,200
        // After Points (1000): 13,200
        // After Credit Card (5%): 12,540
        assertThat(finalPrice).isEqualTo(12540L);
        assertThat(user.getPoints()).isEqualTo(9000L);
    }

    @Test
    @DisplayName("보유한 포인트보다 많은 포인트를 사용하면 예외가 발생한다.")
    void calculateFinalPrice_Fail_WhenNotEnoughPoints() {
        // given
        User user = new User(500L);
        long pointsToUse = 1000L;

        // when & then
        assertThatThrownBy(
                () -> discountService.calculateFinalPrice(reservation, user, pointsToUse, PaymentMethod.CASH))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User does not have enough points.");
    }

    @Test
    @DisplayName("결제할 금액보다 많은 포인트를 사용하면 예외가 발생한다.")
    void calculateFinalPrice_Fail_WhenPointsExceedPrice() {
        // given
        User user = new User(20000L);
        // Discounted price is 14,200
        long pointsToUse = 15000L;

        // when & then
        assertThatThrownBy(
                () -> discountService.calculateFinalPrice(reservation, user, pointsToUse, PaymentMethod.CASH))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot use more points than the total price.");
    }
}
