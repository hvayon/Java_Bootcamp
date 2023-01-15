package ex01;

public class Program {
    private final static String COUNT_FLAG = "--count=";
    private final static String HEN = "Hen";
    private final static String EGG = "Egg";

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith(COUNT_FLAG)) {
            System.err.println("Wrong arguments");
            return ;
        }
        int count = Integer.parseInt(args[0].substring(COUNT_FLAG.length()));

        Thread t1 = new Thread(new Printer(EGG, count));
        Thread t2 = new Thread(new Printer(HEN, count));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}