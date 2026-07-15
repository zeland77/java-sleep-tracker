package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public interface FunctionSleepTracker extends Function<List<SleepingSession>, SleepingAnalysisResult> {
    @Override
    SleepingAnalysisResult apply(List<SleepingSession> sleepingSessions);
}
