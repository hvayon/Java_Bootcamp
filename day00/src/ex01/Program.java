package ex01;
import java.util.Scanner;

public class Program {
	public static void main(String args[])
	{
		
       Scanner scanner = new Scanner(System.in);
	   int exit = -1;
	   int i = 1;
	   boolean res = true;
	   if (scanner.hasNextInt()) {
			int num;
       		if ((num = scanner.nextInt()) > 1) {
				exit = 0;
				if (num > 2) {
					while (num > i * i++) {
						if (num % i == 0) {
                            res = false;
                            break;
                        }
					}
					i--;
				}
			}
	   }
	    if (exit != 0) {
            System.err.println("Illegal Argument");
        }
		else {
            System.out.println(res + " " + i);
        }
        scanner.close();
		System.exit(exit);		
	}
}