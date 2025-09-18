package movie.policy;

import java.time.LocalDateTime;
import java.util.List;
import movie.domain.Reservation;

public class MovieDayDiscountPolicy implements DiscountPolicy {
    private static final List<Integer> MOVIE_DAYS = List.of(10, 20, 30);

    @Override
    public long applyDiscount(long price, Reservation reservation) {
        if (isMovieDay(reservation.getScreening().getStartTime())) {
            return price * 90 / 100; // 10% discount
        }
        return price;
    }

    private boolean isMovieDay(LocalDateTime screeningTime) {
        return MOVIE_DAYS.contains(screeningTime.getDayOfMonth());
    }
}
