package movie.service;

import java.util.List;
import movie.domain.PaymentMethod;
import movie.domain.Reservation;
import movie.policy.DiscountPolicy;
import movie.policy.MovieDayDiscountPolicy;
import movie.policy.TimeDiscountPolicy;

public class DiscountService {
    private final List<DiscountPolicy> policies;

    public DiscountService() {
        // 순서가 중요: 무비데이(비율) -> 시간(정액)
        this.policies = List.of(new MovieDayDiscountPolicy(), new TimeDiscountPolicy());
    }

    public long calculateFinalPrice(Reservation reservation, long pointsToUse, PaymentMethod paymentMethod) {
        long price = reservation.getBasePrice();

        for (DiscountPolicy policy : policies) {
            price = policy.applyDiscount(price, reservation);
        }

        price -= pointsToUse;
        if (price < 0) {
            price = 0;
        }

        return paymentMethod.applyDiscount(price);
    }
}
