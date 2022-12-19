package ex05;
import java.util.Scanner;

class Program {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] names = new String[10];
		addStudent(in, names);
		System.out.println(names[0]);
		in.close();
	}

	private static void errorEnd() {
		System.out.println("Illigal argument");
		System.exit(-1);
	}

	private static int addStudent(Scanner in, String[] names) {
		int i = 0;
		String tmp;
		while (i < 10) {
			if (in.hasNextLine() && !in.hasNextInt()) {
				tmp = in.next();
				if (tmp.length() > 10) {
                	errorEnd();
				} else if (tmp.equals(".")) {
					break ;
				}
				names[i] = tmp;
				i++;
			} else {
				errorEnd();
			}
		}
		return (i);
	}
}