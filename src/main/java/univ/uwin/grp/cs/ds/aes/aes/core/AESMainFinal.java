package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AESMainFinal {
	public static void main(String[] args) {
		String givenText = "0000 0000 0000 0000 0000 0000 0000 ABC8".toLowerCase();
		String givenKey = "1A0C 24F2 8754 95BC B708 0E43 920F 5678".toLowerCase();
		System.out.println("Original plain text and key");
		System.out.println("Input: " + givenText);
		System.out.println("Key: " + givenKey);
		System.out.println("-------------------------------------------");
		System.out.println("Key schedules for Each Round");
		System.out.println("-------------------------------------------");
		final List<List<String>> CONSTANT_HEX_MATRIX = MixColumns.getConstantHexMatrix();
		final List<List<String>> S_BOX = SBox.getsBox();
		List<List<String>> arkOut = null;
		List<List<String>> bsOut = null;
		List<List<String>> srOut = null;
		String mcOut = null;
		String ksOut = null;
		arkOut = arkOut(givenText, givenKey);
		List<String> ksOutStrings = new ArrayList<>();
		List<String> arkOutStrings = new LinkedList<>();
		arkOutStrings.add(MatrixUtility.constructAHexaDecimalString(arkOut));
		int iterationCount = 1;
		while (iterationCount < 10) {
			if (iterationCount == 1) {
				ksOut = ksOut(givenKey);
			} else {
				ksOut = ksOut(ksOut);
			}
			ksOutStrings.add(ksOut);
			bsOut = bsOut(S_BOX, arkOut);
			srOut = srOut(1, bsOut);
			mcOut = mcOut(CONSTANT_HEX_MATRIX, srOut);
			arkOut = arkOut(mcOut, ksOut);
			arkOutStrings.add(MatrixUtility.constructAHexaDecimalString(arkOut));
			iterationCount++;
		} // for last iteration
		ksOut = ksOut(ksOut);
		ksOutStrings.add(ksOut);
		bsOut = bsOut(SBox.getsBox(), arkOut);
		srOut = srOut(1, bsOut);
		String finalIterationShiftRowsOutToString = MatrixUtility.constructAHexaDecimalString(srOut);
		arkOut = arkOut(finalIterationShiftRowsOutToString, ksOut);
		arkOutStrings.add(MatrixUtility.constructAHexaDecimalString(arkOut));
		printPretty(ksOutStrings, null);
		printPretty(arkOutStrings, givenText);
	}

	private static void printPretty(List<String> list, String givenText) {
		if (list instanceof ArrayList) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println("Round " + (i + 1) + ":");
				System.out.println(" Key: " + list.get(i));
			}
			System.out.println("-------------------------------------------");
		} else if (list instanceof LinkedList) {
			System.out.println("Data Results for Each Round:");
			System.out.println("-------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					System.out.println("Round " + i + ":");
					System.out.println("Start: " + givenText);
				} else {
					System.out.println("Round " + i + ":");
				}
				System.out.println("----Output: " + list.get(i));
			}
			System.out.println("----------------------------------------------");
		}
	}

	public static List<List<String>> arkOut(String text, String key) {
		return ARK.arkOut(text, key);
	}

	public static List<List<String>> bsOut(List<List<String>> SBOX, List<List<String>> arkOut) {
		return ByteSubstitution.byteSubstitution(SBOX, arkOut);
	}

	public static List<List<String>> srOut(int fromIndex, List<List<String>> bsOut) {
		return ShiftRows.shiftRowsToLeft(fromIndex, bsOut);
	}

	public static String mcOut(List<List<String>> constantHexMatrix, List<List<String>> srOut) {
		return MixColumns.mc(constantHexMatrix, srOut);
	}

	public static String ksOut(String keyString) {
		return KeyScheduling.keyScheduling(keyString);
	}
}
