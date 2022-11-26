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
			//System.out.println(i);
			if (in.hasNextLine()) {
				word = in.nextLine();
				//System.out.println(weeks);
				if (!word.equals("Week " + weeks)) { //если равно, то не печатает
					System.out.println("Начало " + word);
					if (word.equals("42")) {
						break;
					}
				exError();
			} else if (in.hasNextInt()) {
					// if (num == weeks) {
					int prog;
					//num = in.nextInt();
					//System.out.println(num);
						int min = 0;
						for (int j = 0; j < 5; j++) {
							System.out.println("Check");
							if (in.hasNextInt()) {
								prog = in.nextInt();
								if (!(prog > 0 && prog < 10)) {
									exError();	
									break;
								}
								if (j == 0)
									min = prog;
								else if (prog < min) {
									min = prog;
									//System.out.println("Min = " + min);
								}
							}
							//else
							//	break;
						}
					//}

				}
				weeks++;
		}
	}
		in.close();
}

		static void exError() {
			System.out.println("Illegal Argument");
			System.exit(1);
	}
}


// import java.util.Scanner;

// public class Program {
//     public static void main(String[] args){
//         Scanner input = new Scanner(System.in);
//         String  word;
//         long    data = 0;
//         int     week = 0;

//         for (int num = 1; num < 19; ++num) {
//             if (input.hasNextLine()) {
//                 word = input.next();
//                 if (!word.equals("Week")) {
//                     if (word.equals("42")) {
//                        break;
//                     }
//                     errorEnd();
//                 } else if (input.hasNextInt()) {
//                     week = input.nextInt();
//                     if (week == num) {
//                         data = data + findMin(week, input);
//                     } else {
//                         errorEnd();
//                     }
//                 } else {
//                     errorEnd();
//                 }
//             } else {
//                 errorEnd();
//             }
//         }
//         printArrow(data, week);
//         input.close();
//         System.exit(0);
//     }

//     private static void errorEnd() {
//         System.err.println("Illegal Argument");
//         System.exit(-1);
//     }

//     private static long findMin(int week, Scanner input) {
//         long   tmp;
//         long   minScore = 10;

//         for (int i = 0; i < 5; i++) {
//             if (input.hasNextInt()) {
//                 tmp = input.nextInt();
//                 if (tmp > 0 && tmp < 10) {
//                     if (minScore > tmp) {
//                         minScore = tmp;
//                     }
//                 } else {
//                     errorEnd();
//                 }
//             } else {
//                 errorEnd();
//             }
//         }
//         for (int i = 0; i < week - 1; i++) {
//             minScore *= 10;
//         }
//         return (minScore);
//     }

//     private static void printArrow(long data, int week) {
//         for (int i = 1; i <= week; i++) {
//             System.out.print("Week " + i + " ");
//             for (int j = 0; j < data % 10; j++) {
//                 System.out.print("=");
//             }
//             System.out.println(">");
//             data /= 10;
//         }
//     }
// }

// import java.util.Scanner;

// public class Program {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         final int MAX_NUMBER_OF_WEEKS = 19;
//         final String DATA_LIMIT = "42";
//         long storage = 0;
//         int currentWeek = 1;
//         String input = scanner.nextLine();

//         while (currentWeek != MAX_NUMBER_OF_WEEKS && !input.equals(DATA_LIMIT)) {
//             checkString(input, currentWeek);
//             storage = saveGrade(storage, scanner);
//             input = scanner.nextLine();
//             currentWeek++;
//         }
//         processGrade(storage, currentWeek);

//     }

//     private static void checkString(String check, int weekNumber) {
//         final String WEEK_CHECK = "Week ";

//         if (!check.equals(WEEK_CHECK + weekNumber)) {
//             System.err.println("Illegal Argument");
//             System.exit(-1);
//         }
//     }

//     private static long saveGrade(long storage, Scanner scanner) {
//         final int NUMBER_OF_TESTS = 5;
//         int minGrade = 10;
//         int grade;

//         for (int i = 0; i < NUMBER_OF_TESTS; i++) {
//             grade = scanner.nextInt();
//             if (grade < 1 || grade > 9) {
//                 System.err.println("Illegal Argument");
//                 System.exit(-1);
//             }
//             if (grade < minGrade) {
//                 minGrade = grade;
//             }
//         }
//         storage = storage * 10 + minGrade;
//         scanner.nextLine();

//         return storage;
//     }

//     private static void processGrade(long storage, int numberOfWeeks) {
//         long grade;
//         int tmp = numberOfWeeks;

//         while (tmp > 1) {
//             grade = getGrade(storage, tmp);
//             printGrade(grade, numberOfWeeks - tmp);
//             tmp--;
//         }
//     }

//     private static long getGrade(long storage, int week) {
//         long grade;
//         for (int i = 2; i < week; i++) {
//             storage /= 10;
//         }
//         grade = storage % 10;
//         return grade;
//     }

//     private static void printGrade (long grade, int week) {
//         System.out.print("Week " + (week + 1));
//         for (int j = 0; j < grade; j++) {
//             System.out.print('=');
//         }
//         System.out.println('>');
//     }
// }