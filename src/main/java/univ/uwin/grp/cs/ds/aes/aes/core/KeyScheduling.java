package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; // takes the key string 

public class KeyScheduling {
	private static final List<String> ADD_ROUND_CONSTANT = new ArrayList<>(
			Arrays.asList(new String[] { "01", "00", "00", "00" }));

	public static String keyScheduling(String keyString) {
		Matrix matrix = MatrixUtility.convertToMatrixForm(keyString);
		List<List<String>> hexaDecimalMatrixForm = MatrixUtility.getHexList(matrix);
		List<String> gofwof3 = ksHelper(keyString, hexaDecimalMatrixForm);
		int index = 0;
		List<List<String>> resultOfKeyScheduling = new ArrayList<>();
		List<String> temp = new ArrayList<>(gofwof3);
		while (index < hexaDecimalMatrixForm.size()) {
			List<String> eachRow = wofI(hexaDecimalMatrixForm, temp, index);
			resultOfKeyScheduling.add(eachRow);
			temp = new ArrayList<>(eachRow);
			index++;
		} // System.out.println(resultOfKeyScheduling);
		return MatrixUtility.constructAHexaDecimalString(resultOfKeyScheduling);
	}

	public static List<String> ksHelper(String keyString, List<List<String>> hexaDecimalMatrixForm) { // get the last
																										// hexa decimal
																										// list from the
																										// above list of
																										// lists //
																										// assumed to be
																										// read as
																										// 'double u of
																										// 3'
		List<String> wof3 = hexaDecimalMatrixForm.get(hexaDecimalMatrixForm.size() - 1);
		List<String> gof3 = MatrixUtility.leftShiftSingleRow(wof3); // perform Byte substitution for gof3
		List<List<String>> SBOX = SBox.getsBox();
		List<String> gof3Substituted = ByteSubstitution.byteSubstitutionOfSingleRow(gof3, SBOX); // add round constant
																									// to it.
		List<Integer> gof3SubstitutedToDecimal = MatrixUtility.hexStringListToDecimalList(gof3Substituted); // System.out.println(ADD_ROUND_CONSTANT);
		List<Integer> addRoundConstToDecimal = MatrixUtility
				.hexStringListToDecimalList(ADD_ROUND_CONSTANT); /*
																	 * System.out.println(gof3Substituted);
																	 * System.out.println(gof3SubstitutedToDecimal);
																	 */
		List<Integer> gof3XoredAddRoundConstantDecimal = MatrixXOR.xorOfTwoRows(gof3SubstitutedToDecimal,
				addRoundConstToDecimal);
		List<String> gofwof3 = MatrixUtility.decimalListToHexStringList(gof3XoredAddRoundConstantDecimal);
		return gofwof3;
	}

	private static List<String> wofI(List<List<String>> hexaDecimalMatrixForm, List<String> gofwof3, int index) {
		List<String> wofAtIndex = hexaDecimalMatrixForm.get(index);
		List<Integer> resultInDecimal = MatrixXOR.xorOfTwoRows(MatrixUtility.hexStringListToDecimalList(wofAtIndex),
				MatrixUtility.hexStringListToDecimalList(gofwof3));
		return MatrixUtility.decimalListToHexStringList(resultInDecimal);
	}
}