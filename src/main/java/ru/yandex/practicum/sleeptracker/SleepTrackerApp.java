package ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Ошибка: Укажите путь к файлу с логом в аргументах командной строки.");
            return;
        }

        String filePathString = args[0];
        Path path = Paths.get(filePathString);

        List<String> sessions = null;

        try {
            sessions = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Не удалось прочитать файл: " + e.getMessage());
        }

        if (sessions.isEmpty()) {
            System.out.println("Файл с логом сна пуст");
            return;
        }
        List<SleepingSession> sleepingSessions = sessions.stream().map(SleepingSession::parse).toList();

        List<FunctionSleepTracker> functions = new ArrayList<>();
        functions.add(new FunctionTotalSessions());
        functions.add(new FunctionMinSessionDuration());
        functions.add(new FunctionMaxSessionDuration());
        functions.add(new FunctionAvgSessionDuration());
        functions.add(new FunctionCountSessionBadSleepQlty());
        functions.add(new FunctionCountSleeplessNights());
        functions.add(new FunctionUserChronotype());

        functions.stream().map(n -> n.apply(sleepingSessions))
                          .forEach(System.out::println);

    }
}