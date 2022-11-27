package ex04;
import java.util.Scanner;

class Program {
	private static final int MAX_CH = 65535;
	private static final int TOP_CHARS = 10;
	public static void main(String[] args) {
		String word = "";
		Scanner in = new Scanner(System.in);
		//char[] argvChar = 
		if (in.hasNextLine()) {
			word = in.nextLine();
			int[] chars = new int[MAX_CH];
			char[] charArray = word.toCharArray();
			for (int i = 0; i < word.length(); i++) {
				chars[charArray[i]]++;
            	System.out.println(chars[i]); // записываются нули
			}
			//makeTopChars(chars);
 		}
	}

	private static void makeTopChars(int[] chars) {
		int charCount;
		char[] topChars = new char[TOP_CHARS];
		for (int i = 0; i < MAX_CH; i++) {
            charCount = chars[i];
			System.out.println(charCount);
		}
	}
}
