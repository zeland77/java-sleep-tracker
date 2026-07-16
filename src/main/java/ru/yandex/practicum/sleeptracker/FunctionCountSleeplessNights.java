package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FunctionCountSleeplessNights implements FunctionSleepTracker {
    private final String description = "Количество бессонных ночей";

    @Override
    public SleepingAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions.isEmpty()) {
            return new SleepingAnalysisResult(description, 0);
        }
        long countNightsInPeriod = 0;
        if (sleepingSessions.getFirst().getDateTimeStartSleep().toLocalTime().compareTo(LocalTime.of(12, 0)) < 0) {
            countNightsInPeriod = ChronoUnit.DAYS.between(sleepingSessions.getFirst().getDateTimeStartSleep().toLocalDate(),
                    sleepingSessions.getLast().getDateTimeFinishSleep().toLocalDate()) + 1;
        } else {
            countNightsInPeriod = ChronoUnit.DAYS.between(sleepingSessions.getFirst().getDateTimeStartSleep().toLocalDate(),
                    sleepingSessions.getLast().getDateTimeFinishSleep().toLocalDate());
            if (countNightsInPeriod > 0) {
                countNightsInPeriod++;
            }
        }
        long countSleepNights = sleepingSessions.stream().filter(s -> {

            long night = ChronoUnit.DAYS.between(s.getDateTimeStartSleep().toLocalDate(),
                    s.getDateTimeFinishSleep().toLocalDate());
            if (s.getDateTimeStartSleep().toLocalTime().isBefore(LocalTime.of(6, 0))) {
                night = 1;
            }
            return night == 1;
        }).count();

        return new SleepingAnalysisResult(description, countNightsInPeriod - countSleepNights);
    }

    public String getDescription() {
        return description;
    }
}
