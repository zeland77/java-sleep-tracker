package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {

    SleepingSession ss1 = SleepingSession.parse("01.10.25 23:15;02.10.25 07:30;GOOD");
    SleepingSession ss2 = SleepingSession.parse("02.10.25 23:50;03.10.25 06:40;NORMAL");
    SleepingSession ss3 = SleepingSession.parse("03.10.25 14:10;03.10.25 15:00;NORMAL");
    SleepingSession ss4 = SleepingSession.parse("03.10.25 23:40;04.10.25 08:00;BAD");
    SleepingSession ss5 = SleepingSession.parse("05.10.25 00:10;05.10.25 06:20;GOOD");
    SleepingSession ss6 = SleepingSession.parse("05.10.25 21:30;06.10.25 06:15;NORMAL");
    SleepingSession ss7 = SleepingSession.parse("06.10.25 20:00;07.10.25 05:50;GOOD");
    SleepingSession ss8 = SleepingSession.parse("07.10.25 23:45;08.10.25 06:30;GOOD");
    SleepingSession ss9 = SleepingSession.parse("08.10.25 23:50;09.10.25 09:10;GOOD");
    SleepingSession ss10 = SleepingSession.parse("10.10.25 13:00;10.10.25 14:30;NORMAL");
    SleepingSession ss11 = SleepingSession.parse("10.10.25 23:55;11.10.25 10:10;GOOD");
    SleepingSession ss12 = SleepingSession.parse("11.10.25 23:10;12.10.25 07:00;BAD");
    SleepingSession ss13 = SleepingSession.parse("30.10.25 23:50;31.10.25 06:30;GOOD");
    SleepingSession ss14 = SleepingSession.parse("31.10.25 23:40;01.11.25 05:30;GOOD");
    SleepingSession ss15 = SleepingSession.parse("01.11.25 19:40;01.11.25 22:30;GOOD");
    SleepingSession ss16 = SleepingSession.parse("02.11.25 07:40;02.11.25 11:30;GOOD");
    List<SleepingSession> sleepingSessions = new ArrayList<>();

    @Test
    void functionTotalSessionsTestNull() {
        sleepingSessions.clear();
        FunctionTotalSessions f = new FunctionTotalSessions();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 0), f.apply(sleepingSessions));
    }

    @Test
    void functionTotalSessionsTest() {
        sleepingSessions.clear();
        sleepingSessions.add(ss1);
        sleepingSessions.add(ss2);
        FunctionTotalSessions f = new FunctionTotalSessions();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 2), f.apply(sleepingSessions));
    }

    @Test
    void functionMaxSessionDurationTestNull() {
        sleepingSessions.clear();
        FunctionMaxSessionDuration f = new FunctionMaxSessionDuration();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 0), f.apply(sleepingSessions));
    }

    @Test
    void functionMaxSessionDurationTest() {
        sleepingSessions.clear();
        sleepingSessions.add(ss1);
        sleepingSessions.add(ss2);
        FunctionMaxSessionDuration f = new FunctionMaxSessionDuration();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 495), f.apply(sleepingSessions));
    }

    @Test
    void functionAvgSessionDurationTestNull() {
        sleepingSessions.clear();
        FunctionAvgSessionDuration f = new FunctionAvgSessionDuration();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 0), f.apply(sleepingSessions));
    }

    @Test
    void functionAvgSessionDurationTest() {
        sleepingSessions.clear();
        sleepingSessions.add(ss3);
        sleepingSessions.add(ss3);
        FunctionAvgSessionDuration f = new FunctionAvgSessionDuration();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 50), f.apply(sleepingSessions));
    }

    @Test
    void functionMinSessionDurationTestNull() {
        sleepingSessions.clear();
        FunctionMinSessionDuration f = new FunctionMinSessionDuration();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 0), f.apply(sleepingSessions));
    }

    @Test
    void functionMinSessionDurationTest() {
        sleepingSessions.clear();
        sleepingSessions.add(ss1);
        sleepingSessions.add(ss2);
        FunctionMinSessionDuration f = new FunctionMinSessionDuration();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 410), f.apply(sleepingSessions));
    }

    @Test
    void functionCountSessionBadSleepQltyTestNull() {
        sleepingSessions.clear();
        FunctionCountSessionBadSleepQlty f = new FunctionCountSessionBadSleepQlty();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 0), f.apply(sleepingSessions));
    }

    @Test
    void functionCountSessionBadSleepQltyTest() {
        sleepingSessions.clear();
        sleepingSessions.add(ss3);
        sleepingSessions.add(ss4);
        FunctionCountSessionBadSleepQlty f = new FunctionCountSessionBadSleepQlty();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 1), f.apply(sleepingSessions));
    }

    @Test
    void functionCountSleeplessNightsTestNull() {
        sleepingSessions.clear();
        FunctionCountSleeplessNights f = new FunctionCountSleeplessNights();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), 0), f.apply(sleepingSessions));
    }

    @Test
    void functionCountSleeplessNightsTest() {
        sleepingSessions.clear();
        sleepingSessions.add(ss1);
        sleepingSessions.add(ss4);
        FunctionCountSleeplessNights f = new FunctionCountSleeplessNights();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), (long) 2), f.apply(sleepingSessions));
    }

    @Test
    void functionCountSleeplessNightsTestMonthCrossover() {
        sleepingSessions.clear();
        sleepingSessions.add(ss13);
        sleepingSessions.add(ss14);
        FunctionCountSleeplessNights f = new FunctionCountSleeplessNights();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), (long) 1), f.apply(sleepingSessions));
    }

    @Test
    void functionCountSleeplessNightsTestTimeOverNight() {
        sleepingSessions.clear();
        sleepingSessions.add(ss15);
        sleepingSessions.add(ss16);
        FunctionCountSleeplessNights f = new FunctionCountSleeplessNights();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), (long) 2), f.apply(sleepingSessions));
    }

    @Test
    void functionUserChronotypeTestNull() {
        sleepingSessions.clear();
        FunctionUserChronotype f = new FunctionUserChronotype();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), "Голубь"), f.apply(sleepingSessions));
    }

    @Test
    void functionUserChronotypeTestOwl() {
        sleepingSessions.clear();
        sleepingSessions.add(ss9);
        sleepingSessions.add(ss10);
        sleepingSessions.add(ss11);
        FunctionUserChronotype f = new FunctionUserChronotype();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), "Сова"), f.apply(sleepingSessions));
    }

    @Test
    void functionUserChronotypeTestEarlyBird() {
        sleepingSessions.clear();
        sleepingSessions.add(ss6);
        sleepingSessions.add(ss7);
        sleepingSessions.add(ss8);
        FunctionUserChronotype f = new FunctionUserChronotype();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), "Жаворонок"), f.apply(sleepingSessions));
    }

    @Test
    void functionUserChronotypeTestPigeon() {
        sleepingSessions.clear();
        sleepingSessions.add(ss1);
        sleepingSessions.add(ss6);
        FunctionUserChronotype f = new FunctionUserChronotype();
        Assertions.assertEquals(new SleepingAnalysisResult(f.getDescription(), "Голубь"), f.apply(sleepingSessions));
    }


}