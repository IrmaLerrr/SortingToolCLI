import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortManager {

    public static <T extends Comparable<T>> List<Map.Entry<T, Long>> sortList(String sortingType, List<T> list) {
        return switch (sortingType) {
            case "byCount" -> SortManager.sortByCount(list);
            case "natural" -> SortManager.sortNatural(list);
            default -> throw new RuntimeException("Unexpected error!");
        };
    }

    public static <T extends Comparable<T>> List<Map.Entry<T, Long>> sortByCount(List<T> list) {
        Map<T, Long> frequencyMap = list.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        return frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<T, Long>comparingByValue()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());
    }

    public static <T extends Comparable<T>> List<Map.Entry<T, Long>> sortNatural(List<T> list) {
        return list.stream()
                .sorted()
                .map(item -> Map.entry(item, 1L))
                .collect(Collectors.toList());
    }
}
