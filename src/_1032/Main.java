package _1032;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int total = in.nextInt();
		for (int counter = 0; counter < total; counter++) {
			String word = in.next();
			StringBuilder sb = new StringBuilder();
			sb.append('_');
			for (int i = 0; i < word.length(); i++) {
				sb.append(word.charAt(i)).append('_');
			}
			sb.deleteCharAt(sb.length() - 1).append('&');
			calc(sb.toString());
		}
	}

	private static void calc(String word) {
		int maxLength = 1;
		int midPoint = 0;
		int maxMidPoint = 0;
		int rightBorder = 0;
		int[] p = new int[word.length()];

		for (int i = 0; i < word.length() - 1; i++) {
			p[i] = rightBorder > i ? Math.min(p[2 * midPoint - i], rightBorder
					- i) : 1;
			while (/*
					 * i + p[i] < (word.length()) &&
					 */i >= p[i]
					&& word.charAt(i - p[i]) == word.charAt(i + p[i])) {
				p[i]++;
			}
			if (i + p[i] >= rightBorder) {
				midPoint = i;
				rightBorder = i + p[i];
			}
			if (maxLength <= p[i]) {
				maxLength = p[i];
				maxMidPoint = midPoint;
			}
		}
		// System.out.println(word + maxLength + maxMidPoint);
		if ( maxMidPoint + maxLength == word.length() - 1) {
			System.out.println(maxLength);
		} else {
			System.out.println(maxLength - 1);

		}
	}
}
