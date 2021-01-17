package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.List;

public class ARK {
	public static List<List<String>> arkOut(String text, String key) {
		Matrix textMatrix = MatrixUtility.giveMatrixForm(text);
		Matrix keyMatrix = MatrixUtility.giveMatrixForm(key);
		Matrix result = MatrixXOR.matrixXOR(textMatrix, keyMatrix);
		List<List<String>> hexMatrix = MatrixUtility.getHexList(result);
		return hexMatrix;
	}

	public static void printPretty(List<List<String>> list) {
		for (List<String> hexStringList : list) {
			for (String hexString : hexStringList) {
				System.out.print(hexString + " ");
			}
			System.out.println();
		}
	}
}