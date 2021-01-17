package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.ArrayList;
import java.util.List;

public class MixColumns {
	private static final String[][] mat = new String[][] { new String[] { "02", "03", "01", "01" },
			new String[] { "01", "02", "03", "01" }, new String[] { "01", "01", "02", "03" },
			new String[] { "03", "01", "01", "02" } };
	private static final List<List<String>> CONSTANT_HEX_MATRIX = new ArrayList<>();

	public static List<List<String>> getConstantHexMatrix() {
		for (int i = 0; i < mat.length; i++) {
			List<String> temp = new ArrayList<>();
			for (int j = 0; j < mat[i].length; j++) {
				temp.add(mat[i][j]);
			}
			CONSTANT_HEX_MATRIX.add(temp);
		}
		return CONSTANT_HEX_MATRIX;
	}

	public static String mc(List<List<String>> constantHexMatrix, List<List<String>> shiftRowsOutput) {
		List<List<String>> resultHex = new ArrayList<>();
		for (int i = 0; i < constantHexMatrix.size(); i++) {
			List<String> row = new ArrayList<>();
			for (int j = 0; j < shiftRowsOutput.size(); j++) {
				List<String> binaryStringList = mcHelper(constantHexMatrix.get(i),
						Utilities.getColumn(shiftRowsOutput, j)); // System.out.println(binaryStringList);
				int xorOfListElements = xor(binaryStringList);
				String hexString = Integer.toHexString(xorOfListElements);
				row.add(hexString);
			}
			resultHex.add(row);
		}
		return MatrixUtility.constructAHexaDecimalString(resultHex);
	}

	private static List<String> mcHelper(List<String> rowFromConstantHexMatrix,
			List<String> columnFromShiftRowsOutput) { // look up in L_BOX
		List<String> binaryStringList = new ArrayList<>();
		for (int i = 0; i < rowFromConstantHexMatrix.size(); i++) {
			String rowElement = rowFromConstantHexMatrix.get(i).trim();
			String columnElement = columnFromShiftRowsOutput.get(i).trim();
			String rowElementFromLBox = LBox.lookup(rowElement);
			String columnElementFromLBox = LBox.lookup(columnElement);
			int additionResultAfterLBox = Integer.parseInt(rowElementFromLBox, 16)
					+ Integer.parseInt(columnElementFromLBox, 16);
			if (additionResultAfterLBox > 255) {
				additionResultAfterLBox -= 255;
			}
			String additionResultAfterLBoxInToHexString = Integer.toHexString(additionResultAfterLBox);
			String resultFromEBox = EBox.lookup(additionResultAfterLBoxInToHexString);
			binaryStringList.add(Integer.toBinaryString(Integer.parseInt(resultFromEBox, 16)));
		}
		return binaryStringList;
	}

	private static int xor(int number1, int number2) {
		return number1 ^ number2;
	}

	private static int xor(List<String> binaryStringList) {
		int result = 0;
		for (int i = 0; i < binaryStringList.size(); i++) {
			result ^= Integer.parseInt(binaryStringList.get(i), 2);
		}
		return result;
	}
}
