package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	private List<List<Integer>> matrix;

	public List<List<Integer>> initialiseTheMatrix(int n) {
		matrix = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			List<Integer> sub = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				sub.add(0);
			}
			matrix.add(sub);
		}
		return matrix;
	}

	private List<List<Integer>> fill(List<List<Integer>> m) {
		matrix = new ArrayList<>();
		for (int i = 0; i < m.size(); i++) {
			List<Integer> sub = new ArrayList<>();
			for (int j = 0; j < m.size(); j++) {
				sub.add(m.get(i).get(j));
			}
			matrix.add(sub);
		}
		return matrix;
	}

	public Matrix(int n) {
		matrix = initialiseTheMatrix(n);
	}

	public Matrix(List<List<Integer>> givenMatrix) {
		matrix = new ArrayList<>();
		for (int i = 0; i < givenMatrix.size(); i++) {
			List<Integer> sub = new ArrayList<>();
			for (int j = 0; j < givenMatrix.size(); j++) {
				sub.add(givenMatrix.get(i).get(j));
			}
			matrix.add(sub);
		}
	}

	public List<List<Integer>> getMatrixAsList() {
		return matrix;
	}

	public void setMatrix(List<List<Integer>> matrix) {
		this.matrix = matrix;
	} // columnNumber starts with 0 and ends with matrix.size() - 1

	public List<Integer> getColumn(int columnNumber) {
		if (columnNumber >= matrix.size()) {
			throw new RuntimeException("Given Column doesn't exist in the Matrix!!!");
		}
		List<List<Integer>> m = matrix;
		List<Integer> columnMatrix = new ArrayList<>();
		int column = columnNumber;
		for (int row = 0; row < m.size(); row++) {
			for (; column < m.get(row).size();) {
				columnMatrix.add(m.get(row).get(column));
				break;
			}
		}
		return columnMatrix;
	}

	public Matrix toMatrix(List<List<Integer>> in) {
		Matrix m = new Matrix(in);
		return m;
	}

	@Override
	public String toString() {
		return "" + matrix + "";
	}

	public List<Integer> getRow(int i) {
		return matrix.get(i);
	}
}
