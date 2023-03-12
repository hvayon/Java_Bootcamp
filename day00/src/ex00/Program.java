package ex00;

public class Program {
	public static void main(String[] argv) {
		int number = 479598;
		int res = 0;

		res += number % 10;
		number = number / 10;
		res += number % 10;
		number = number / 10;
		res += number % 10;
		number = number / 10;
		res += number % 10;
		number = number / 10;
		res += number % 10;
		number = number / 10;
		res += number % 10;
		number = number / 10;
		System.out.println(res);
	}
}