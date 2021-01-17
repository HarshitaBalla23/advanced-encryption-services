package univ.uwin.grp.cs.ds.aes.aes.core;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EBox {
	private static final List<List<String>> E_BOX = new ArrayList<>();

	private static void reader(List<List<String>> E_BOX) {
		File file = new File("aes.utilities/src/aes/utilities/funcs/ebox.txt");
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String row = null;
			while ((row = bufferedReader.readLine()) != null) {
				List<String> hexStrings = new ArrayList<>();
				List<String> words = Arrays.stream(row.split(" ")).map(s -> s.trim()).collect(Collectors.toList());
				hexStrings.addAll(words);
				E_BOX.add(hexStrings);
			}
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static String lookup(String s) {
		int[] getRowAndColumnForSub = ByteSubstitution.bsHelper(s);
		String resultString = ByteSubstitution.getSubstitutionString(getRowAndColumnForSub, getEBox());
		return resultString;
	}

	public static List<List<String>> getEBox() {
		reader(E_BOX);
		return E_BOX;
	}

	public static void printPretty() {
		for (List<String> hexStringList : E_BOX) {
			for (String hexString : hexStringList) {
				System.out.print(hexString + " ");
			}
			System.out.println();
		}
	}
}