package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.ArrayList;
import java.util.List;

public class Utilities {

	public static List<List<String>> convertHexStringMatrixToBinaryStringMatrix(List<List<String>> hexStringMatrix) {
		for (int i = 0; i < hexStringMatrix.size(); i++) {
			hexStringMatrix.set(i, convertHexStringMatrixToBinaryStringMatrixHelper(hexStringMatrix.get(i)));
		}
		return hexStringMatrix;
	}

	private static List<String> convertHexStringMatrixToBinaryStringMatrixHelper(List<String> hexStringList) {
		for (int i = 0; i < hexStringList.size(); i++) {
			String binaryString = Integer.toBinaryString(Integer.parseInt(hexStringList.get(i), 16));
			hexStringList.set(i, binaryString);
		}
		return hexStringList;
	}

	public static List<String> getColumn(List<List<String>> list, int columnNumber) {
		if (columnNumber >= list.size()) {
			throw new RuntimeException("Given Column doesn't exist in the Matrix!!!");
		}

		List<List<String>> m = list;
		List<String> columnMatrix = new ArrayList<>();
		for (int row = 0; row < m.size(); row++) {
			for (; columnNumber < m.get(row).size();) {
				columnMatrix.add(m.get(row).get(columnNumber));
				break;
			}
		}
		return columnMatrix;
	}
}
