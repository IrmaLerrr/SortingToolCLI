import java.util.*;

public class Main {
    private static String sortingType = "natural";
    private static String dataType = "word";
    private static String inputFile = null;
    private static String outputFile = null;
    private static final List<String> correctSortingTypes = List.of("natural", "byCount");
    private static final List<String> correctDataTypes = List.of("long", "line", "word");

    public static <T extends Comparable<T>> void main(final String[] args) {

        if (args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-inputFile" -> {
                        if (i + 1 == args.length) System.out.println("No input file defined!");
                        else {
                            i++;
                            inputFile = args[i];
                        }
                    }
                    case "-outputFile" -> {
                        if (i + 1 == args.length) System.out.println("No output file defined!");
                        else {
                            i++;
                            outputFile = args[i];
                        }
                    }
                    case "-sortingType" -> {
                        if (i + 1 == args.length || !correctSortingTypes.contains(args[i + 1]))
                            System.out.println("No sorting type defined!");
                        else {
                            i++;
                            sortingType = args[i];
                        }
                    }
                    case "-dataType" -> {
                        if (i + 1 == args.length || !correctDataTypes.contains(args[i + 1]))
                            System.out.println("No data type defined!");
                        else {
                            i++;
                            dataType = args[i];
                        }
                    }
                    default -> System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", args[i]);
                }
            }
        }

        List<T> list =
                switch (inputFile) {
                    case null -> InputHandler.getList(dataType);
                    default -> FileManager.getList(inputFile, dataType);
                };

        List<Map.Entry<T, Long>> sortedCollection = SortManager.sortList(sortingType, list);

        switch (outputFile) {
            case null -> InputHandler.printSort(sortingType, dataType, sortedCollection);
            default -> FileManager.printSortToFile(outputFile, sortingType, dataType, sortedCollection);
        }
    }





}
