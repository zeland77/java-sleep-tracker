package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class FunctionAvgSessionDuration implements FunctionSleepTracker {
    private final String description = "Средняя продолжительность сессии (минут)";
    @Override
    public SleepingAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int avg = (int) sleepingSessions.stream()
                .map(s -> Duration.between(s.getDateTimeStartSleep(), s.getDateTimeFinishSleep()))
                .mapToLong(Duration::toMinutes).average().orElse(0.0);
        return new SleepingAnalysisResult(description, avg);
    }

    public String getDescription() {
        return description;
    }
}
