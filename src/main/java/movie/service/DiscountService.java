package movie.service;

import java.util.List;
import movie.domain.PaymentMethod;
import movie.domain.Reservation;
import movie.policy.DiscountPolicy;
import movie.policy.MovieDayDiscountPolicy;
import movie.policy.TimeDiscountPolicy;
import movie.domain.User;

public class DiscountService {
    private final List<DiscountPolicy> policies;

    public DiscountService() {
        // 순서가 중요: 무비데이(비율) -> 시간(정액)
        this.policies = List.of(new MovieDayDiscountPolicy(), new TimeDiscountPolicy());
    }

    public long calculateFinalPrice(Reservation reservation, User user, long pointsToUse,
            PaymentMethod paymentMethod) {
        long price = reservation.getBasePrice();

        for (DiscountPolicy policy : policies) {
            price = policy.applyDiscount(price, reservation);
        }

        validatePoints(user, pointsToUse, price);

        user.usePoints(pointsToUse);
        price -= pointsToUse;

        return paymentMethod.applyDiscount(price);
    }

    private void validatePoints(User user, long pointsToUse, long currentPrice) {
        if (!user.hasEnoughPoints(pointsToUse)) {
            throw new IllegalArgumentException("User does not have enough points.");
        }
        if (pointsToUse > currentPrice) {
            throw new IllegalArgumentException("Cannot use more points than the total price.");
        }
    }
}
