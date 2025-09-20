package domain;

import java.time.LocalTime;

public class Schedule {
    private final LocalTime startTime;
    private final LocalTime endTime;

    public Schedule(LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) throw new IllegalArgumentException();

        if (startTime.equals(endTime)) throw new IllegalArgumentException();

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isValidSchedule(Schedule schedule) {
        return startTime.isAfter(schedule.endTime) || endTime.isBefore(schedule.startTime);
    }
}
