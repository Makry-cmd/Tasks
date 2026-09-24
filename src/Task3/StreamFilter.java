package Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamFilter {
    enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    static class WorkSheet {
        String name;
        List<DayOfWeek> workDays;

        public WorkSheet(String name, List<DayOfWeek> workDays) {
            this.name = name;
            this.workDays = workDays;
        }

        public String getName() {
            return name;
        }

        public List<DayOfWeek> getWorkDays() {
            return workDays;
        }

        @Override
        public String toString() {
            return name + workDays;
        }
    }

    void test() {
        var denSheet = new WorkSheet("Денис", List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY));
        var benSheet = new WorkSheet("Бен", List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY));
        var lisSheet = new WorkSheet("Лиза", List.of(DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        var sheets = List.of(denSheet, benSheet, lisSheet);
        // TODO: Необходимо вывести на экран дни в которые работало более 1 человека
        Map<DayOfWeek, Long> hashmap = new HashMap<>();
        hashmap = sheets.stream()
                .flatMap(sheet -> sheet.getWorkDays().stream())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        hashmap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

    }
}
