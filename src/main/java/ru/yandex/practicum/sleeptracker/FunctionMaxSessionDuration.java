package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class FunctionMaxSessionDuration implements FunctionSleepTracker {
    private final String description = "Максимальная продолжительность сессии (минут)";

    @Override
    public SleepingAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int max = (int) sleepingSessions.stream()
                .map(s -> Duration.between(s.getDateTimeStartSleep(), s.getDateTimeFinishSleep()))
                .max(Duration::compareTo).orElse(Duration.ofMinutes(0)).toMinutes();
        return new SleepingAnalysisResult(description, max);
    }

    public String getDescription() {
        return description;
    }
}
