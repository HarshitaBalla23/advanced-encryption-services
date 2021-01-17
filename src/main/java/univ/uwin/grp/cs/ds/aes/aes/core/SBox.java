package univ.uwin.grp.cs.ds.aes.aes.core;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SBox {
	private static final List<List<String>> S_BOX = new ArrayList<>();

	private static void reader(List<List<String>> S_BOX) {
		
		File file = new File("src/main/resources/sbox.txt");
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
			String row = null;
			while ((row = bufferedReader.readLine()) != null) {
				List<String> hexStrings = new ArrayList<>();
				List<String> words = Arrays.stream(row.split(", ")).map(string -> parseToHexString(string))
						.collect(Collectors.toList());
				hexStrings.addAll(words);
				S_BOX.add(hexStrings);
			}
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private static List<List<String>> swapRows(int row1, int row2) {
		List<String> rowToBeSwapped = S_BOX.get(row1);
		List<String> rowToBeSwappedWith = S_BOX.get(row2);
		S_BOX.set(row1, rowToBeSwappedWith);
		S_BOX.set(row2, rowToBeSwapped);
		return S_BOX;
	}

	public static String lookup(String s) {
		int[] getRowAndColumnForSub = ByteSubstitution.bsHelper(s);
		String resultString = ByteSubstitution.getSubstitutionString(getRowAndColumnForSub, getsBox());
		return resultString;
	}

	public static void printPretty() {
		for (List<String> hexStringList : S_BOX) {
			for (String hexString : hexStringList) {
				System.out.print(hexString + " ");
			}
			System.out.println();
		}
	}

	public static List<List<String>> getsBox() {
		reader(S_BOX);
		swapRows(1, 8);
		return S_BOX;
	}

	private static String parseToHexString(String string) {
		return string.substring(2).toLowerCase();
	}
}