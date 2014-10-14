package _1051;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int total = in.nextInt();

		for (int counter = 0; counter < total; counter++) {
			int totalDay = in.nextInt();
			int cards = in.nextInt();
			if (totalDay == 0) {
				System.out.println(100);
				continue;
			}

			int[] days = new int[totalDay];
			for (int i = 0; i < totalDay; i++) {
				days[i] = in.nextInt();
			}
			if (cards >= totalDay) {
				System.out.println(100);
				continue;
			}

			//
			System.out.println(calc(cards, days));
		}
	}

	private static int calc(int cards, int[] days) {
		int total = 0;
		int[] tmp = new int[days.length - cards];
		int[] tmp1 = new int[tmp.length + 2];

		tmp1[0] = 0;
		tmp1[tmp.length + 1] = 101;

		for (int i = 1; i <= days.length - cards; i++) {
			System.arraycopy(days, 0, tmp1, 1, i);
			System.arraycopy(days, i + cards - 1, tmp1, i, days.length - i + 1
					- cards);
			if (total < tmp1[0]) {
				total = tmp1[0] - 1;
			}
			for (int j = 1; j < tmp1.length; j++) {
				if (total < (tmp1[j] - tmp1[j - 1] - 1)) {
					total = tmp1[j] - tmp1[j - 1] - 1;
				}
			}
		}
		return total;
	}
}
