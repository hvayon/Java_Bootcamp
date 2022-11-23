import java.util.Scanner;

class Program{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int weeks = 1;
		//final int MAX_NUMBER_OF_WEEKS = 19;
		String word;
		int num;
		//int i = 1;
		for (int i = 1; i <= 18; i++) {
				//System.out.println("Check");
				word = in.nextLine();
				if (!word.equals("Week")) {
					if (word.equals("42"))
						break;
				}
				else if (in.hasNextInt()) {
					num = in.nextInt();
					if (num == weeks) {
						int prog;
						int min = 0;
						for (int j = 0; j < 5; j++) {
							if (in.hasNextInt()) {
								prog = in.nextInt();
								if (!(prog > 1 && prog < 10))
									break;
								if (j == 0)
									min = prog;
								else if (prog < min) {
									min = prog;
									System.out.println(min);
								}
							}
							else
								break;
						}
					}

				}
				weeks++;
		}
		in.close();
	}
}