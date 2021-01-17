package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtility {
	public static Matrix shift(Matrix matrix) {
		List<List<Integer>> list = matrix.getMatrixAsList();
		List<List<Integer>> result = new ArrayList<>();
		int index = 0;
		while (index < list.size()) {
			List<Integer> row = shiftHelper(list, index);
			result.add(row);
			index++;
		}
		Matrix resultMatrix = new Matrix(list.size()).toMatrix(result);
		return resultMatrix;
	}

	public static Matrix giveMatrixForm(String s) {
		return shift(convertToMatrixForm(s));
	}

	public static Matrix convertToMatrixForm(String s) {
		List fourBitsIntoDecimalList = convertToMatrixFormHelper(s);
		List<List<Integer>> matrixList = new ArrayList<>();
		for (int i = 0, j = 4; i < fourBitsIntoDecimalList.size()
				&& j <= fourBitsIntoDecimalList.size(); i += 4, j += 4) {
			List<Integer> subList = fourBitsIntoDecimalList.subList(i, j);
			matrixList.add(subList);
		}
		Matrix matrix = new Matrix(4).toMatrix(matrixList);
		return matrix;
	}

	private static List<Integer> convertToMatrixFormHelper(String s) {
		String[] bytes = s.split(" ");
		List<Integer> fourBitsIntoDecimalList = new ArrayList<>();
		for (int i = 0; i < bytes.length; i++) { // first four bits
			String fourBits = bytes[i].substring(0, 2);
			int dec = Integer.parseInt(fourBits.trim(), 16);
			fourBitsIntoDecimalList.add(dec); // next four bits
			fourBits = bytes[i].substring(2);
			dec = Integer.parseInt(fourBits.trim(), 16);
			fourBitsIntoDecimalList.add(dec);
		}
		return fourBitsIntoDecimalList;
	} // generic method

	private static <T> List<T> shiftHelper(List<List<T>> list, int index) {
		List<T> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			result.add(list.get(i).get(index));
		}
		return result;
	}

	public static <T> List<T> leftShiftSingleRow(List<T> list) {
		List<T> result = new ArrayList<>(list.subList(1, list.size()));
		result.add(list.get(0));
		return result;
	}

	public static List<String> decimalListToHexStringList(List<Integer> list) {
		List<String> result = new ArrayList<>(list.size());
		for (int i = 0; i < list.size(); i++) {
			String hexStringVal = Integer.toHexString(list.get(i));
			result.add(hexStringVal);
		}
		return result;
	}

	public static List<Integer> hexStringListToDecimalList(List<String> list) {
		List<Integer> decimalList = new ArrayList<>(list.size());
		for (int i = 0; i < list.size(); i++) {
			int decimalVal = Integer.parseInt(list.get(i), 16);
			decimalList.add(decimalVal);
		}
		return decimalList;
	}

	public static Matrix hexStringMatrixToDecimalMatrix(List<List<String>> hexStringMatrix) {
		List<List<Integer>> resultDecimalMatrix = new ArrayList<>(hexStringMatrix.size());
		for (List<String> row : hexStringMatrix) {
			List<Integer> decimalRow = hexStringListToDecimalList(row);
			resultDecimalMatrix.add(decimalRow);
		}
		Matrix result = new Matrix(resultDecimalMatrix.size()).toMatrix(resultDecimalMatrix);
		return result;
	}

	public static List<List<String>> getHexList(Matrix matrix) {
		List<List<Integer>> integerList = matrix.getMatrixAsList();
		List<List<String>> hex = new ArrayList<>(integerList.size());
		for (List<Integer> row : integerList) {
			List<String> hexStringRow = decimalListToHexStringList(row);
			hex.add(hexStringRow);
		}
		return hex;
	}

	public static String constructAHexaDecimalString(List<List<String>> list) {
		StringBuilder stringBuilder = new StringBuilder();
		for (List<String> row : list) {
			int count = 0;
			for (int i = 0; i < row.size(); i++) {
				count++;
				String temp = row.get(i);
				if (temp.length() != 2) {
					temp = ByteSubstitution.getPaddedString(temp);
				}
				stringBuilder.append(temp);
				if (count == 2) {
					stringBuilder.append(" ");
				}
			}
			stringBuilder.append(" ");
		}
		return stringBuilder.toString();
	}
}
