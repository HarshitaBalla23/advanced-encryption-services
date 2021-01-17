package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.List; // the output of ARK is sent here 

public class ByteSubstitution {
	public static List<List<String>> byteSubstitution(List<List<String>> SBOX, List<List<String>> arkOut) {
		for (int i = 0; i < arkOut.size(); i++) {
			for (int j = 0; j < arkOut.get(i).size(); j++) {
				String toBeSubstituted = arkOut.get(i).get(j);
				int[] getRowAndColForSub = null;
				getRowAndColForSub = bsHelper(toBeSubstituted);
				String substitutionStringFromSBOX = getSubstitutionString(getRowAndColForSub, SBOX);
				arkOut.get(i).set(j, substitutionStringFromSBOX);
			}
		}
		return arkOut;
	}

	public static int[] bsHelper(String toBeSubstituted) {
		char charAtZero, charAtOne;
		int[] getRowAndColForSub = null;
		if (toBeSubstituted.length() != 2) {
			toBeSubstituted = getPaddedString(toBeSubstituted);
		}
		charAtZero = toBeSubstituted.charAt(0);
		charAtOne = toBeSubstituted.charAt(1);
		getRowAndColForSub = getRowAndColumnForSubstitution(charAtZero, charAtOne);
		return getRowAndColForSub;
	}

	public static String getPaddedString(String toBeSubstituted) { // pad it with a '0' at the beginning
		String temp = "0";
		toBeSubstituted = temp.concat(toBeSubstituted);
		return toBeSubstituted;
	}

	public static String getSubstitutionString(int[] getRowAndColForSub, List<List<String>> sbox) {
		int row = getRowAndColForSub[0];
		int column = getRowAndColForSub[1];
		return sbox.get(row).get(column);
	}

	private static int[] getRowAndColumnForSubstitution(char charAtZero, char charAtOne) {
		int rowToBeUsedInSubstitution = -1, columnToBeUsedInSubstitution = -1;
		if (checkIfCharacter(charAtZero)) {
			rowToBeUsedInSubstitution = switchCase(Character.toLowerCase(charAtZero));
		} else if (checkIfNumber(charAtZero)) {
			rowToBeUsedInSubstitution = parseCharToInteger(charAtZero);
		}
		if (checkIfCharacter(charAtOne)) {
			columnToBeUsedInSubstitution = switchCase(Character.toLowerCase(charAtOne));
		} else if (checkIfNumber(charAtOne)) {
			columnToBeUsedInSubstitution = parseCharToInteger(charAtOne);
		}
		if (rowToBeUsedInSubstitution == -1 || columnToBeUsedInSubstitution == -1) {
			throw new RuntimeException("Your code in getRowAndColumnForSubstitution() is causing this!!");
		}
		return new int[] { rowToBeUsedInSubstitution, columnToBeUsedInSubstitution };
	}

	private static boolean checkIfCharacter(char ch) {
		return Character.isLetter(ch);
	}

	private static boolean checkIfNumber(char ch) {
		return Character.isDigit(ch);
	}

	private static int switchCase(char ch) {
		switch (ch) {
		case 'a':
			return 10;
		case 'b':
			return 11;
		case 'c':
			return 12;
		case 'd':
			return 13;
		case 'e':
			return 14;
		case 'f':
			return 15;
		default:
			return -1;
		}
	}

	private static int parseCharToInteger(char ch) {
		return Integer.parseInt(Character.toString(ch));
	}

	public static List<String> byteSubstitutionOfSingleRow(List<String> list, List<List<String>> SBOX) {
		for (int i = 0; i < list.size(); i++) {
			String toBeSubstituted = list.get(i);
			int[] getRowAndColumn = bsHelper(toBeSubstituted);
			String substitutionStringFromSBOX = getSubstitutionString(getRowAndColumn, SBOX);
			list.set(i, substitutionStringFromSBOX);
		}
		return list;
	}
}
