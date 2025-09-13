package engine;

import java.util.Random;
import java.util.Scanner;
import java.util.function.IntConsumer;

public class Helpers {
    private static final Random rng = new Random();
    private static Scanner scanner = null;

    public static void cleanupStdInForKoan() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }

    public static String readLine() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner.nextLine();
    }

    static void setupRandomForKoan(final long seed) {
        rng.setSeed(seed);
    }

    public static double random() {
        return rng.nextDouble();
    }

    public static IntConsumer createNumberPrinter(String name) {
        return num -> System.out.println(name + " " + num + " - " + ((num + 1) * num));
    }
}
