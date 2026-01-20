import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class FileManager {
    private static final String PATH = "./src/data/";

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> List<T> getList(String fileName, String dataType) {
        Path filePath = Paths.get(PATH + fileName);
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            return switch (dataType) {
                case "line" -> (List<T>) lines;
                case "word" -> (List<T>) getListWord(lines);
                case "long" -> (List<T>) getListLong(getListWord(lines));
                default -> throw new RuntimeException("Unexpected error!");
            };
        } catch (NoSuchFileException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.printf("Read error: %s%n", e.getMessage());
        }
        return null;
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

    public static <T extends Comparable<T>> void printSortToFile(String fileName, String sortingType, String dataType, List<Map.Entry<T, Long>> sortedCollection) {
        Path filePath = Paths.get(PATH + fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE)) {
            switch (sortingType) {
                case "natural" -> printNaturalSort(writer, dataType, sortedCollection);
                case "byCount" -> printCountSort(writer, dataType, sortedCollection);
                default -> throw new RuntimeException("Unexpected error!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static <T extends Comparable<T>> void printCountSort(BufferedWriter writer, String dataType, List<Map.Entry<T, Long>> sortedEntries) throws IOException {
        long total = sortedEntries.stream()
                .mapToLong(Map.Entry::getValue)
                .sum();
        writer.write(String.format("Total %ss: %d.\n", dataType, total));
        for (Map.Entry<T, Long> entry : sortedEntries) {
            T t = entry.getKey();
            Long count = entry.getValue();
            writer.write(String.format("%s: %d time(s), %d%%\n",
                    t,
                    count,
                    count * 100 / total));
        }
    }

    public static <T extends Comparable<T>> void printNaturalSort(BufferedWriter writer, String dataType, List<Map.Entry<T, Long>> sortedEntries) throws IOException {
        writer.write(String.format("Total %ss: %d.\n", dataType, sortedEntries.size()));
        if (Objects.equals(dataType, "line")) {
            writer.write("Sorted data:\n");
            for (Map.Entry<T, Long> sortedEntry : sortedEntries) {
                writer.write(sortedEntry.getKey() + "\n");
            }
        } else {
            writer.write("Sorted data:");
            for (Map.Entry<T, Long> sortedEntry : sortedEntries) {
                writer.write(" " + sortedEntry.getKey());
            }
        }
    }
}
