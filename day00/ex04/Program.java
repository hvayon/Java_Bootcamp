package ex04;
import java.util.Scanner;

class Program {
	private static final int MAX_CH = 65535;
	private static final int TOP_CHARS = 10;
	public static void main(String[] args) {
		String word = "";
		Scanner in = new Scanner(System.in);
		if (in.hasNextLine()) {
			word = in.nextLine();
			int[] chars = new int[MAX_CH];
			char[] charArray = word.toCharArray();
			for (int i = 0; i < word.length(); i++) {
				chars[charArray[i]]++;
			}
			makeTopChars(chars);
 		}
		in.close();
	}

	private static void makeTopChars(int[] chars) {
		int charCount;
		char[] topChars = new char[TOP_CHARS];
		for (int i = 0; i < MAX_CH; i++) {
            charCount = chars[i];
			if (charCount > 0) {
				for (int j = 0; j < TOP_CHARS; j++)
				{
					if (chars[topChars[j]] < charCount) {
						topChars = insertChars(topChars, j, (char) i);
						break ;
					}
				}
			}
		}
		printTopChar(topChars, chars);
	}

	private static char[] insertChars(char[] topChars, int position, char c) {
		char[] newArray = new char[TOP_CHARS];
		for (int i = 0; i < position; i++) {
			newArray[i] = topChars[i];
		}
		newArray[position] = c;
		for (int i = position + 1; i < TOP_CHARS; i++) {
            newArray[i] = topChars[i - 1];
        }
		return newArray;
	}

	private static void printTopChar(char[] topChars, int[] chars) {
        int max = chars[topChars[0]];
        int height = max <= 10 ? max : 10;
        int[] scaledArray = new int[TOP_CHARS];
        for (int i = 0; i < TOP_CHARS; i++) {
            if (max <= 10) {
                scaledArray[i] = chars[topChars[i]];
            } else {
                scaledArray[i] = chars[topChars[i]] * 10 / max;
            }
        }
        System.out.println();
        for (int i = 0; i < height + 2; i++) {
            for (int j = 0; j < TOP_CHARS; j++) {
                if (topChars[j] != 0) {
                    if (i + scaledArray[j] + 2 == height + 2) {
                        System.out.printf("%3d", chars[topChars[j]]);
                    } else if (i == height + 1) {
                        System.out.printf("%3c", topChars[j]);
                    } else if (i + scaledArray[j] >= height) {
                        System.out.printf("%3c", '#');
                    }
                    if (j != TOP_CHARS - 1 && topChars[j + 1] != 0 && i + scaledArray[j + 1] >= height) {
                        System.out.printf("%c", ' ');
                    }
                }
            }
            System.out.println();
        }
    }
}

