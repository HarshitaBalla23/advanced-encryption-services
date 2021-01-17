package univ.uwin.grp.cs.ds.aes.aes.core;

import java.util.ArrayList;
import java.util.List; // the output of ByteSubstitution comes here 

public class ShiftRows {
	public static void main(String[] args) {
		String givenText = "0000 0000 0000 0000 0000 0000 0000 ABC8";
		String givenKey = "1A0C 24F2 8754 95BC B708 0E43 920F 5678";
		List<List<String>> S_BOX = SBox.getsBox();
		List<List<String>> outputFromARK = ARK.arkOut(givenText, givenKey);
		System.out.println(S_BOX);
		System.out.println();
		System.out.println(outputFromARK);
		System.out.println();
		List<List<String>> outFromBS = ByteSubstitution.byteSubstitution(S_BOX, outputFromARK);
		System.out.println(outFromBS);
		List<List<String>> outFromSF = shiftRowsToLeft(1, outFromBS);
		System.out.println(outFromSF);
	}

	public static List<List<String>> shiftRowsToLeft(int fromIndex, List<List<String>> list) {
		for (int i = fromIndex; i < list.size(); i++) {
			List<String> temp = list.get(i);
			temp = shiftLeftHelper(fromIndex, temp.size(), temp);
			list.set(i, temp);
			fromIndex++;
		}
		return list;
	}

	private static List<String> shiftLeftHelper(int fromIndex, int toIndex, List<String> list) {
		List<String> result = new ArrayList<>(list.subList(fromIndex, toIndex));
		for (int i = 0; i < fromIndex; i++) {
			result.add(list.get(i));
		}
		return result;
	}
}