package ex01;
import java.util.Scanner;

public class Program {
	public static void main(String args[])
	{
		
       Scanner scanner = new Scanner(System.in);
	   int exit = -1;
	   int check = 2;
	   boolean res = true;
	   int num;
	   if (scanner.hasNextInt()) {
       		if ((num = scanner.nextInt()) > 1) {
				   exit = 0;
				if (num > 2) {
					while (check * check <= num && num % check != 0) {
						check++;
					}
					res = check * check > num;
				}
			}
	   }
	    if (exit != 0) {
            System.err.println("Illegal Argument");
        }
		else {
            System.out.println(res + " " + (check - 1));
        }
        scanner.close();
		System.exit(exit);		
	}
}