package movie.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Screening {
    private Movie movie;
    private LocalDateTime startTime;
    private List<Seat> seats;

    public Screening(Movie movie, LocalDateTime startTime, List<Seat> seats) {
        this.movie = movie;
        this.startTime = startTime;
        this.seats = seats;
    }

    public Reservation reserve(User user, List<Seat> seatsToReserve) {
        validateSeatsAreAvailable(seatsToReserve);
        reserveSeats(seatsToReserve);
        long totalPrice = calculateTotalPrice(seatsToReserve);
        return new Reservation(this, seatsToReserve, totalPrice);
    }

    private void validateSeatsAreAvailable(List<Seat> seats) {
        for (Seat seat : seats) {
            if (!seat.isAvailable()) {
                throw new IllegalStateException("Seat " + seat + " is already reserved.");
            }
        }
    }

    private void reserveSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            seat.reserve();
        }
    }

    private long calculateTotalPrice(List<Seat> seats) {
        return seats.stream()
                .mapToLong(Seat::getPrice)
                .sum();
    }
}
