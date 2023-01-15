package ex00;
public class Program {
    private final static String COUNT_FLAG = "--count=";

    public static void main(String[] args) {
        if (args[0] == null) {
            System.err.println("Void input");
            return ;
        }
        if (!args[0].startsWith(COUNT_FLAG)) {
            System.err.println("Wrong arguments");
            return ;
        }

        int count = Integer.parseInt(args[0].substring(COUNT_FLAG.length()));

        Egg egg = new Egg(count);
        Hen hen = new Hen(count);

        egg.start();
        hen.start();
        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            System.err.println("Thread error");
        }
        int i = 0;
        while (i < count) {
            System.out.println("Human");
            i++;
        }
    }
}