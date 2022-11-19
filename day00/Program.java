import java.util.Scanner;

public class Program {
	public static void main(String argv[]) {
		Scanner in = new Scanner(System.in);
		int num;
		while (in.hasNextInt()) {
			if ((num = in.nextInt()) == 42)
				break;
		}

	}
}