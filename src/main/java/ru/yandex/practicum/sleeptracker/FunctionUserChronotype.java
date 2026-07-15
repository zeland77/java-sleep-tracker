package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FunctionUserChronotype implements FunctionSleepTracker {
    private final String description = "Хронотип пользователя";
    @Override
    public SleepingAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        List<SleepingSession> sleepNights = sleepingSessions.stream().filter(s -> {
            long night = ChronoUnit.DAYS.between(s.getDateTimeStartSleep().toLocalDate(),
                    s.getDateTimeFinishSleep().toLocalDate());
            if (s.getDateTimeStartSleep().toLocalTime().isBefore(LocalTime.of(6, 0))) {
                night = 1;
            }
            return night == 1;
        }).toList();

        long countOwl = sleepNights.stream().filter(s -> {
            return s.getDateTimeStartSleep().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                   s.getDateTimeFinishSleep().toLocalTime().isAfter(LocalTime.of(6, 0));
        }).count();

        long countEarlyBird = sleepNights.stream().filter(s -> {
            return s.getDateTimeStartSleep().toLocalTime().isBefore(LocalTime.of(22, 0)) &&
                   s.getDateTimeFinishSleep().toLocalTime().isBefore(LocalTime.of(7, 0));
        }).count();

        long countPigeon = sleepingSessions.stream().filter(s -> {
            long night = ChronoUnit.DAYS.between(s.getDateTimeStartSleep().toLocalDate(),
                    s.getDateTimeFinishSleep().toLocalDate());
            if (s.getDateTimeStartSleep().toLocalTime().isBefore(LocalTime.of(6, 0))) {
                night = 1;
            }
            return night == 1;
        }).count() - countOwl - countEarlyBird;

        String result = "Голубь";
        if ((countOwl > countEarlyBird) && (countOwl > countPigeon)) {
            result = "Сова";
        } else if ((countEarlyBird > countOwl) && (countEarlyBird > countPigeon)) {
            result = "Жаворонок";
        }

        return new SleepingAnalysisResult(description, result);
    }

    public String getDescription() {
        return description;
    }
}
