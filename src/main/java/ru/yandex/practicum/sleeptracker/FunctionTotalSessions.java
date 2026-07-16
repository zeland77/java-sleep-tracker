package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class FunctionTotalSessions implements FunctionSleepTracker {
    private final String description = "Всего сессий сна";

    @Override
    public SleepingAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        return new SleepingAnalysisResult(description, sleepingSessions.size());
    }

    public String getDescription() {
        return description;
    }
}
