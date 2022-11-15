public class Program {
	public static void main(String[] argv) {
		int a = 479598;
		int res = 0;

		res += a % 10;
		a = a / 10;
		res += a % 10;
		a = a / 10;
		res += a % 10;
		a = a / 10;
		res += a % 10;
		a = a / 10;
		res += a % 10;
		a = a / 10;
		res += a % 10;
		a = a / 10;
		System.out.println(res);
	}
}