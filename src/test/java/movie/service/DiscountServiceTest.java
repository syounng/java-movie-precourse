package movie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import movie.domain.Movie;
import movie.domain.PaymentMethod;
import movie.domain.Reservation;
import movie.domain.Screening;
import movie.domain.Seat;
import movie.domain.SeatGrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountServiceTest {

    private DiscountService discountService;
    private Movie movie;
    private Seat seat;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService();
        movie = new Movie("Test Movie", Duration.ofMinutes(120), 0L);
        seat = new Seat('A', 1, SeatGrade.S); // 18,000 KRW
    }

    @Test
    @DisplayName("모든 할인(무비데이, 시간)과 포인트, 결제수단(신용카드) 할인이 적용된다.")
    void calculateFinalPrice_AllDiscountsApplied() {
        // given: 10th day, 10:00 AM screening
        LocalDateTime screeningTime = LocalDateTime.of(2025, 9, 10, 10, 0);
        Screening screening = new Screening(movie, screeningTime, List.of(seat));
        Reservation reservation = new Reservation(screening, List.of(seat), seat.getPrice());
        long pointsToUse = 1000L;
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;

        // when
        long finalPrice = discountService.calculateFinalPrice(reservation, pointsToUse, paymentMethod);

        // then
        // Base price: 18,000
        // After Movie Day (10%): 18000 * 0.9 = 16,200
        // After Time (2000): 16200 - 2000 = 14,200
        // After Points (1000): 14200 - 1000 = 13,200
        // After Credit Card (5%): 13200 * 0.95 = 12,540
        assertThat(finalPrice).isEqualTo(12540L);
    }

    @Test
    @DisplayName("아무 할인도 적용되지 않는다.")
    void calculateFinalPrice_NoDiscountsApplied() {
        // given: 11th day, 14:00 PM screening
        LocalDateTime screeningTime = LocalDateTime.of(2025, 9, 11, 14, 0);
        Screening screening = new Screening(movie, screeningTime, List.of(seat));
        Reservation reservation = new Reservation(screening, List.of(seat), seat.getPrice());
        long pointsToUse = 0L;
        PaymentMethod paymentMethod = PaymentMethod.CASH;

        // when
        long finalPrice = discountService.calculateFinalPrice(reservation, pointsToUse, paymentMethod);

        // then
        // Base price: 18,000
        // After Cash (2%): 18000 * 0.98 = 17,640
        assertThat(finalPrice).isEqualTo(17640L);
    }
}
