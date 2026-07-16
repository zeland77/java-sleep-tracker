package ru.yandex.practicum.sleeptracker;

import java.util.Objects;

public class SleepingAnalysisResult<T> {
    private String description;
    private T result;

    public SleepingAnalysisResult(String description, T result) {
        this.description = description;
        this.result = result;
    }

    @Override
    public String toString() {
        return description + ": " + result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepingAnalysisResult<?> that = (SleepingAnalysisResult<?>) o;
        return Objects.equals(description, that.description) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, result);
    }
}
