package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {
    private LocalDateTime dateTimeStartSleep;
    private LocalDateTime dateTimeFinishSleep;
    private SleepQuality sleepQuality;
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public SleepingSession(
            LocalDateTime dateTimeStartSleep,
            LocalDateTime dateTimeFinishSleep,
            SleepQuality sleepQuality) {
        this.dateTimeStartSleep = dateTimeStartSleep;
        this.dateTimeFinishSleep = dateTimeFinishSleep;
        this.sleepQuality = sleepQuality;
    }

    public static SleepingSession parse(String str) {
        String[] sessionItem = str.split(";");
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime dateTimeStartSleep = LocalDateTime.parse(sessionItem[0], formatter);
        LocalDateTime dateTimeFinishSleep = LocalDateTime.parse(sessionItem[1], formatter);
        SleepQuality sleepQuality;
        sleepQuality = SleepQuality.valueOf(sessionItem[2]);
        return new SleepingSession(dateTimeStartSleep, dateTimeFinishSleep, sleepQuality);
    }

    public LocalDateTime getDateTimeStartSleep() {
        return dateTimeStartSleep;
    }

    public void setDateTimeStartSleep(LocalDateTime dateTimeStartSleep) {
        this.dateTimeStartSleep = dateTimeStartSleep;
    }

    public LocalDateTime getDateTimeFinishSleep() {
        return dateTimeFinishSleep;
    }

    public void setDateTimeFinishSleep(LocalDateTime dateTimeFinishSleep) {
        this.dateTimeFinishSleep = dateTimeFinishSleep;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(SleepQuality sleepQuality) {
        this.sleepQuality = sleepQuality;
    }
}

