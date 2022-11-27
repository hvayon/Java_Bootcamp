package ex03;
import java.util.Scanner;

class Program {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int weeks = 1;
		String word;
		int num;
		long data = 0;
		for (int i = 1; i <= 18; i++) {
			if (in.hasNextLine()) {
				word = in.next();
				if (!word.equals("Week")) {
						if (word.equals("42")) {
							break;
					}
					exError();
				} else if (in.hasNextInt()) {
                    num = in.nextInt();
					if (num == weeks) {
						data = data + findMin(weeks, in);
						}
					}
				}
				weeks++;
			}
		printArrow(data, weeks);
		in.close();
	}


	static void exError() {
		System.out.println("Illegal Argument");
		System.exit(1);
	}
	static long findMin(int weeks, Scanner in) {
		int num;
		long min = 0;
		for (int j = 0; j < 5; j++) {
			if (in.hasNextInt()) {
					num = in.nextInt();
				if (!(num > 0 && num < 10)) {
					exError();	
					break;
				}
				if (j == 0) {
					min = num;
				} else if (num < min) {
					min = num;
				}
			}
		}  
		for (int i = 0; i < weeks - 1; i++) {
			min *= 10;
		}
		return min;
	}

		private static void printArrow(long data, int weeks) {
		for (int i = 1; i <= weeks - 1; i++) {
			System.out.print("Week " + i + " ");
			for (int j = 0; j < data % 10; j++) {
				System.out.print("=");
			}
			System.out.println(">");
			data /= 10;
		}
	}
}
