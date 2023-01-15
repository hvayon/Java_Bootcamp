package ex02;

public class Array extends Thread {

    private final int[] array;
    private final int   id;
    private final int begin;
    private final int end;

    private int sum = 0;

    public Array(int[] array, int id, int begin, int end) {
        this.array = array;
        this.id = id;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        for (int j : array) {
            sum += j;
        }
    }

    public int getSum() {
        printSum();
        return sum;
    }

    private void printSum() {
        System.out.println("Thread " + id + ": from " + begin + " to " + end + " sum is " + sum);
    }
}

