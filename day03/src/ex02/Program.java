package ex02;

import java.util.Arrays;
import java.util.Random;

public class Program {

    private static final String ARRAY_FLAG = "--arraySize=";
    private static final String THREAD_FLAG = "--threadsCount=";
    private static final int MAX_NUMBER_OF_ELEMENTS = 2000000;
    private static final int MAX_VALUE = 1000;

    private static void countSum(int[] array) {
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        System.out.println("Sum: " + sum);
    }

    public static void main(String[] args) {
        if (args.length != 2 || !args[0].startsWith(ARRAY_FLAG) ||
                !args[1].startsWith(THREAD_FLAG)) {
            System.err.println("Wrong arguments");
            System.exit(0);
        }
        int arraySize = Integer.parseInt(args[0].substring(ARRAY_FLAG.length()));
        int threadCount = Integer.parseInt(args[1].substring(THREAD_FLAG.length()));
        final int threadArraySize = arraySize / threadCount;
        int[] array = new int[arraySize];
        int sum = 0;
        Random generator = new Random();
        Array[] arrays = new Array[threadCount];

        if (arraySize > MAX_NUMBER_OF_ELEMENTS || threadCount > arraySize) {
            System.err.println("Wrong input");
            System.exit(0);
        }

        for (int i = 0; i < arraySize; i++) {
            array[i] = generator.nextInt(MAX_VALUE);
        }
        countSum(array);
        for (int i = 0; i < threadCount; i++) {
            int from = i * threadArraySize;
            int to = i != threadCount - 1 ? (i + 1) * threadArraySize : arraySize;
            arrays[i] = new Array(Arrays.copyOfRange(array, from, to), i + 1, from, to);
        }
        for (Array thread : arrays) {
            thread.start();
        }
        for (Array thread : arrays) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        for (Array thread : arrays) {
            sum += thread.getSum();
        }
        System.out.println("Sum by threads: " + sum);

    }
}
