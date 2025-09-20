package movie.domain;

import java.util.List;

public class Screening {
    private final List<Schedule> scheduleList;

    public Screening(List<Schedule> scheduleList) {
        validateSchedules(scheduleList);
        this.scheduleList = scheduleList;
    }

    private void validateSchedules(List<Schedule> scheduleList) {
        for (int i = 0; i < scheduleList.size(); i++) {
            for (int j = i + 1; j < scheduleList.size(); j++) {
                Schedule s1 = scheduleList.get(i);
                Schedule s2 = scheduleList.get(j);
                if (!s1.isValidSchedule(s2)) {
                    throw new IllegalArgumentException("스케줄이 서로 겹칩니다: " + s1 + " vs " + s2);
                }
            }
        }
    }

}
