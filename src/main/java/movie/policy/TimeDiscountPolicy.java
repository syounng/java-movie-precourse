package movie.policy;

import java.time.LocalDateTime;
import java.time.LocalTime;
import movie.domain.Reservation;

public class TimeDiscountPolicy implements DiscountPolicy {
    private static final LocalTime MORNING_DISCOUNT_END_TIME = LocalTime.of(11, 0);
    private static final LocalTime NIGHT_DISCOUNT_START_TIME = LocalTime.of(20, 0);
    private static final long DISCOUNT_AMOUNT = 2000L;

    @Override
    public long applyDiscount(long price, Reservation reservation) {
        if (isDiscountableTime(reservation.getScreening().getStartTime())) {
            return Math.max(0, price - DISCOUNT_AMOUNT);
        }
        return price;
    }

    private boolean isDiscountableTime(LocalDateTime screeningTime) {
        LocalTime time = screeningTime.toLocalTime();
        return time.isBefore(MORNING_DISCOUNT_END_TIME) || time.isAfter(NIGHT_DISCOUNT_START_TIME);
    }
}
