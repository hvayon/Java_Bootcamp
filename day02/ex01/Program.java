package ex01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Program {

	static TreeSet<String> dictionary = new TreeSet<>();

	static List<String> fileA = new ArrayList<>();
	static List<String> fileB = new ArrayList<>();

	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("I need 2 arguments");
			System.exit(-1);
		}

		try {

			addToStruct(args[0], fileA, dictionary);
			addToStruct(args[1], fileB, dictionary);

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dictionary.txt"));

			for (String s : dictionary) {
				bufferedWriter.write(s + " ");
			}
			bufferedWriter.write(" ");
			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.printf("Similarity = %.4s\n", getResult());


	}

	static String getResult() {

		int size = dictionary.size();
		int[] freqA = countFrequency(fileA);
		int[] freqB = countFrequency(fileB);

		int numerator = 0;
		for (int i = 0; i < size; ++i) {
			numerator += freqA[i] * freqB[i];
		}

		double denominator = 0;
		double tmpSum = 0;
		for (int i = 0; i < size; ++i) {
			tmpSum += Math.pow(freqA[i], 2);
			denominator += Math.pow(freqB[i], 2);
		}
		denominator = Math.sqrt(denominator) * Math.sqrt(tmpSum);

		return String.format("%.3f", numerator / denominator);
	}

	static int[] countFrequency(List<String> file) {

		int count;
		int i = 0;
		int[] arr = new int[dictionary.size()];

		for (String s : dictionary) {
			count = 0;
			for (String str : file) {
				if (s.equals(str)) {
					count++;
				}
			}
			arr[i] = count;
			++i;
		}
		return arr;
	}


	static void addToStruct(String infile, List<String> file, TreeSet<String> dict) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(infile));
		String line;

		while ((line = bufferedReader.readLine()) != null) {
			String[] wordsArr = line.split(" ");
			file.addAll(Arrays.asList(wordsArr));
			dict.addAll(file);
		}
		bufferedReader.close();
	}

}