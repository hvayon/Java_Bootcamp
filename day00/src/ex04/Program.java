package ex04;
import java.util.Scanner;

public class Program {
	private static final short CH = 10;
	private static final int MAX_SH = 65535;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String  word = "";
		short[] asc_num = new short[CH];
		short[] asc_key = new short[CH];
		if (input.hasNextLine()) {
			word = input.nextLine();
			char[]  sym = word.toCharArray();
			short[] key = new short[MAX_SH];
			if (word.length() < 1000 && word.length() > 0) {
				for (int i = 0; i < word.length(); i++) {
					key[(short)sym[i]]++;
				}
				findTenSym(asc_num, asc_key, key);
			}
		} else {
			errorEnd();
		}
		input.close();
		System.exit(0);
	}

	private static void errorEnd() {
		System.err.println("Illegal Argument");
		System.exit(-1);
	}

	private static void sortBub(short[] num, short[] key, short len) {
		boolean isSorted = false;
		short   buf;

		while(!isSorted) {
			isSorted = true;
			for (int i = len - 1; i > 0; i--) {
				if(num[i] > num[i - 1]){
					isSorted = false;
					buf = num[i];
					num[i] = num[i - 1];
					num[i - 1] = buf;
					buf = key[i];
					key[i] = key[i - 1];
					key[i - 1] = buf;
				}
			}
		}
	}

	private static void printer(short counter, short[] asc_num, short[] asc_key) {
		short   max_num = asc_num[0];
		short   c = 0;
		short[] per_num = new short[counter];
		short   flag = 0;
		for (short i = 0; i < counter; i++) {
			per_num[i] = (short)((asc_num[i] * CH) / max_num);
		}
		while (c < CH + 1) {
			for(int j = 0; j < counter; j++) {
				if (j < flag){
					System.out.print("  #");
				} else if (per_num[j] == CH - c) {
					System.out.printf("%3d", asc_num[j]);
					flag++;
				} else if (per_num[j] > CH - c) {
					System.out.print("  #");
				}

			}
			System.out.println();
			c++;
		}
		for(int j = 0; j < counter; j++) {
			System.out.printf("%3c", (char)asc_key[j]);
		}
		System.out.println();
	}

	private static void findTenSym(short[] asc_num, short[] asc_key, short[] key) {
		short   counter = 0;

		for (int i = 0; i < MAX_SH; i++) {
			if (key[i] != 0) {
				counter++;
			}
		}
		short[] tmp_num = new short[counter];
		short[] tmp_key = new short[counter];
		counter = 0;
		for (int i = 0; i < MAX_SH; i++) {
			if (key[i] != 0) {
				tmp_num[counter] = key[i];
				tmp_key[counter] = (short)i;
				counter++;
			}
		}
		sortBub(tmp_num, tmp_key, counter);
		if (counter > CH){
			counter = CH;
		}
		asc_num = new short[counter];
		asc_key = new short[counter];
		for (int i = 0; i < counter; i++) {
			asc_key[i] = tmp_key[i];
			asc_num[i] = tmp_num[i];
		}
		printer(counter, asc_num, asc_key);
	}
}