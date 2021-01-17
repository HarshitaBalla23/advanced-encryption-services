package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.ArrayList;
import java.util.List;

public class MatrixXOR {
	public static Matrix matrixXOR(Matrix matrix1, Matrix matrix2) {
		List<List<Integer>> list1 = matrix1.getMatrixAsList();
		List<List<Integer>> result = new Matrix(list1.size()).initialiseTheMatrix(list1.size());
		for (int i = 0; i < list1.size(); i++) {
			List<Integer> row1 = matrix1.getRow(i);
			List<Integer> row2 = matrix2.getRow(i);
			List<Integer> xoredRow = xorOfTwoRows(row1, row2);
			result.set(i, xoredRow);
		}
		Matrix resultMatrix = new Matrix(list1.size());
		return resultMatrix.toMatrix(result);
	}

	public static List<Integer> xorOfTwoRows(List<Integer> row1, List<Integer> row2) {
		List<Integer> xoredRow = new ArrayList<>();
		for (int i = 0; i < row1.size(); i++) {
			xoredRow.add((row1.get(i) ^ row2.get(i)));
		}
		return xoredRow;
	}
}
