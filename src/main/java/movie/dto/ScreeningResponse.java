package movie.dto;

import java.time.LocalDateTime;

public class ScreeningResponse {
    Long id;
    LocalDateTime startAt;
    LocalDateTime endAt;

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public ScreeningResponse(Long id, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
