package ex02;
import java.util.Scanner;

/* Нет обработки некорректного ввода */

public class Program {
	public static void main(String argv[]) {
		Scanner in = new Scanner(System.in);
		int num;
		int total;
		int coffeeRq = 0;
		while (in.hasNextInt()) {
			num = in.nextInt();
			total = sum(num);
			if (simpleNumber(total)) {
				coffeeRq++;
			}
			if (num == 42)
				break;
		}
		System.out.println("Count of coffee-request – " + coffeeRq);
		in.close();
	}

	public static int sum(int num) {
		int res = 0;
		while (num != 0) {
			res += num % 10;
			num = num / 10;
		}
		return(res);
	}

	public static boolean simpleNumber(int num) {
		int i = 1;
		boolean res = true;
		if (num > 2) {
			while (num > i * i++) {
				if (num % i == 0) {
					res = false;
					break;
				}
			}
		}
		return res;
	}
}
