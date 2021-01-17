package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.List;

public class AESMain {
	public static void main(String[] args) {
		String givenText = "0000 0000 0000 0000 0000 0000 0000 ABC8".toLowerCase();
		String givenKey = "1A0C 24F2 8754 95BC B708 0E43 920F 5678".toLowerCase();
		System.out.println("*********************Given Text***********************");
		System.out.println(givenText);
		System.out.println();
		System.out.println("********************Given Key*************************");
		System.out.println(givenKey);
		System.out.println();
		final List<List<String>> CONSTANT_HEX_MATRIX = MixColumns.getConstantHexMatrix();
		final List<List<String>> S_BOX = SBox.getsBox();
		List<List<String>> arkOut = null;
		List<List<String>> bsOut = null;
		List<List<String>> srOut = null;
		String mcOut = null;
		String ksOut = null; // for iteration 0
		System.out.println("******************************ROUND-0************************************");
		arkOut = arkOut(givenText, givenKey);
		System.out.println();
		System.out.println("arkOut: " + 0 + ":" + MatrixUtility.constructAHexaDecimalString(arkOut));
		System.out.println();
		int iterationCount = 1;
		while (iterationCount < 10) {
			System.out.println("**************************************ROUND-" + iterationCount
					+ "************************************");
			if (iterationCount == 1) {
				ksOut = ksOut(givenKey);
			} else {
				ksOut = ksOut(ksOut);
			}
			System.out.println("ksOut: " + iterationCount + ":" + ksOut);
			System.out.println();
			bsOut = bsOut(S_BOX, arkOut); // System.out.println("bsOut: " + iterationCount + ":" + bsOut);
			System.out.println("bsOut: " + iterationCount + ":" + MatrixUtility.constructAHexaDecimalString(bsOut));
			System.out.println();
			srOut = srOut(1, bsOut); // System.out.println("srOut: " + iterationCount + ":" + srOut);
			System.out.println("srOut: " + iterationCount + ":" + MatrixUtility.constructAHexaDecimalString(srOut));
			System.out.println();
			mcOut = mcOut(CONSTANT_HEX_MATRIX, srOut);
			System.out.println("mcOut: " + iterationCount + ":" + mcOut);
			System.out.println();
			arkOut = arkOut(mcOut, ksOut); // System.out.println("arkOut: " + iterationCount + ":" + arkOut);
			System.out.println("arkOut: " + iterationCount + ":" + MatrixUtility.constructAHexaDecimalString(arkOut));
			System.out.println();
			iterationCount++;
		} // for last iteration
			// System.out.println("*********************************ROUND-" + iterationCount
			// + "*****************************************");
		ksOut = ksOut(ksOut);
		System.out.println("ksOut: " + iterationCount + ":" + ksOut);
		System.out.println();
		bsOut = bsOut(SBox.getsBox(), arkOut);
		System.out.println("bsOut: " + iterationCount + ":" + MatrixUtility.constructAHexaDecimalString(bsOut));
		System.out.println();
		srOut = srOut(1, bsOut);
		System.out.println("srOut: " + iterationCount + ":" + MatrixUtility.constructAHexaDecimalString(srOut));
		System.out.println();
		String finalIterationShiftRowsOutToString = MatrixUtility.constructAHexaDecimalString(srOut);
		arkOut = arkOut(finalIterationShiftRowsOutToString, ksOut); // System.out.println("arkOut: " + iterationCount +
																	// ":" + arkOut);
		System.out.println("arkOut: " + iterationCount + ":" + MatrixUtility.constructAHexaDecimalString(arkOut));
		System.out.println("*********************************Final Cipher text*********************************");
		System.out.println(MatrixUtility.constructAHexaDecimalString(arkOut));
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
