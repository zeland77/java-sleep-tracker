package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class FunctionMinSessionDuration implements FunctionSleepTracker {
    private final String description = "Минимальная продолжительность сессии (минут)";

    @Override
    public SleepingAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int min = (int) sleepingSessions.stream()
                    .map(s -> Duration.between(s.getDateTimeStartSleep(), s.getDateTimeFinishSleep()))
                    .min(Duration::compareTo).orElse(Duration.ofMinutes(0)).toMinutes();
        return new SleepingAnalysisResult(description, min);
    }

    public String getDescription() {
        return description;
    }
}
