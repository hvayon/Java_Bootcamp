package ex01;

public class Printer implements Runnable {
    private final String msg;
    private final int count;
    private static Printer print = null;

    public Printer(String msg, int count) {
        this.msg = msg;
        this.count = count;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < count) {
            if (print != this) {
                print = this;
                System.out.println(msg);
                i++;
            }
        }
    }
}
