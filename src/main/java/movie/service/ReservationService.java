package movie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import movie.domain.Reservation;
import movie.domain.Screening;
import movie.domain.Seat;
import movie.domain.User;

public class ReservationService {

    public List<Reservation> reserve(User user, Map<Screening, List<Seat>> screeningRequests) {
        validateNoOverlap(new ArrayList<>(screeningRequests.keySet()));

        List<Reservation> reservations = new ArrayList<>();
        for (Map.Entry<Screening, List<Seat>> entry : screeningRequests.entrySet()) {
            reservations.add(entry.getKey().reserve(user, entry.getValue()));
        }
        return reservations;
    }

    private void validateNoOverlap(List<Screening> screenings) {
        for (int i = 0; i < screenings.size(); i++) {
            for (int j = i + 1; j < screenings.size(); j++) {
                if (screenings.get(i).overlaps(screenings.get(j))) {
                    throw new IllegalStateException("Screening times overlap.");
                }
            }
        }
    }
}
