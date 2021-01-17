package univ.uwin.grp.cs.ds.aes.aes.core;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LBox {
	private static final List<List<String>> L_BOX = new ArrayList<>();

	private static void reader(List<List<String>> L_BOX) {
		File file = new File("aes.utilities/src/aes/utilities/funcs/lbox.txt");
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String row;
			while ((row = bufferedReader.readLine()) != null) {
				List<String> hexStrings = new ArrayList<>();
				List<String> words = Arrays.stream(row.split(" ")).map(s -> s.trim()).collect(Collectors.toList());
				hexStrings.addAll(words);
				L_BOX.add(hexStrings);
			}
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static String lookup(String s) {
		int[] getRowAndColumnForSub = ByteSubstitution.bsHelper(s);
		String resultString = ByteSubstitution.getSubstitutionString(getRowAndColumnForSub, getLBox());
		return resultString;
	}

	public static List<List<String>> getLBox() {
		reader(L_BOX);
		return L_BOX;
	}

	public static void printPretty() {
		for (List<String> hexStringList : L_BOX) {
			for (String hexString : hexStringList) {
				System.out.print(hexString + " ");
			}
			System.out.println();
		}
	}
}