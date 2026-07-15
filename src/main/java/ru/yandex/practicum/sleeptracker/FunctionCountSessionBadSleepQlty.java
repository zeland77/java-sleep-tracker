package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class FunctionCountSessionBadSleepQlty implements FunctionSleepTracker {
    private final String description = "Количество сессий с плохим качеством сна";
    @Override
    public SleepingAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        int countBadSession = (int) sleepingSessions.stream()
                .filter(s -> s.getSleepQuality() == SleepQuality.BAD)
                .count();
        return new SleepingAnalysisResult(description, countBadSession);
    }

    public String getDescription() {
        return description;
    }
}
