import java.util.*;
import java.util.stream.Collectors;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> List<T> getList(String dataType) {
        return switch (dataType) {
            case "line" -> (List<T>) getListLine();
            case "word" -> (List<T>) getListWord(getListLine());
            case "long" -> (List<T>) getListLong(getListWord(getListLine()));
            default -> throw new RuntimeException("Unexpected error!");
        };
    }

    public static List<String> getListLine() {
        return scanner.useDelimiter("\\R")
                .tokens()
                .collect(Collectors.toList());
    }

    public static List<String> getListWord(List<String> lines) {
        return Arrays.asList(String.join(" ", lines).split("\\s+"));
    }

    public static List<Long> getListLong(List<String> words) {
        List<Long> longs = new ArrayList<>();
        for (String word : words) {
            if (word.matches("-?\\d+")) longs.add(Long.parseLong(word));
            else System.out.printf("\"%s\" is not a long. It will be skipped.\n", word);
        }
        return longs;
    }

    public static <T extends Comparable<T>> void printSort(String sortingType, String dataType, List<Map.Entry<T, Long>> sortedCollection) {
        switch (sortingType) {
            case "natural" -> printNaturalSort(dataType, sortedCollection);
            case "byCount" -> printCountSort(dataType, sortedCollection);
            default -> throw new RuntimeException("Unexpected error!");
        }
    }

    public static <T extends Comparable<T>> void printCountSort(String dataType, List<Map.Entry<T, Long>> sortedEntries) {
        long total = sortedEntries.stream()
                .mapToLong(Map.Entry::getValue)
                .sum();
        System.out.printf("Total %ss: %d.\n", dataType, total);
        for (Map.Entry<T, Long> entry : sortedEntries) {
            T t = entry.getKey();
            Long count = entry.getValue();
            System.out.printf("%s: %d time(s), %d%%\n",
                    t,
                    count,
                    count * 100 / total);
        }
    }

    public static <T extends Comparable<T>> void printNaturalSort(String dataType, List<Map.Entry<T, Long>> sortedEntries) {
        System.out.printf("Total %ss: %d.\n", dataType, sortedEntries.size());
        if (Objects.equals(dataType, "line")) {
            System.out.println("Sorted data:");
            for (Map.Entry<T, Long> sortedEntry : sortedEntries) {
                System.out.println(sortedEntry.getKey());
            }
        } else {
            System.out.print("Sorted data:");
            for (Map.Entry<T, Long> sortedEntry : sortedEntries) {
                System.out.print(" " + sortedEntry.getKey());
            }
        }
    }
}
