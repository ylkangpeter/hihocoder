package _1052;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int total = in.nextInt();

		for (int counter = 0; counter < total; counter++) {
			String str = in.next();
			int same = in.nextInt();
			if (same == str.length()) {
				System.out.println(0);
				continue;
			}
			List<Byte> list = new ArrayList<Byte>();
			for (Byte b : str.getBytes()) {
				list.add(b);
			}

			int v = 0;

			if (2 * same <= list.size()) {
				for (int i = 0; i < same; i++) {
					if (list.get(list.size() - same + i) == list.get(i)) {
						continue;
					} else {
						v += 1;
						list.set(list.size() - same + i, list.get(i));
					}
				}
				System.out.println(v);
			} else {
				int max = 0;
				for (int i = 0; i < list.size() - same; i++) {

					// ATCG
					Pair _ANo = new Main.Pair((byte) 65);// 65
					Pair _TNo = new Main.Pair((byte) 84);// 84
					Pair _CNo = new Main.Pair((byte) 67);// 67
					Pair _GNo = new Main.Pair((byte) 71);// 71

					int round = 0;
					int inx = 0;
					while ((inx = (list.size() - same) * round + i) < list
							.size()) {
						switch (list.get(inx)) {
						case 65: {
							_ANo.counter++;
							break;
						}
						case 67: {
							_CNo.counter++;
							break;
						}
						case 71: {
							_GNo.counter++;
							break;
						}
						case 84: {
							_TNo.counter++;
							break;
						}
						default: {
						}
						}
						round++;
					}
					Pair result = _ANo;
					if (result.counter < _TNo.counter) {
						result = _TNo;
					}
					if (result.counter < _GNo.counter) {
						result = _GNo;
					}
					if (result.counter < _CNo.counter) {
						result = _CNo;
					}
					max = max + _TNo.counter + _GNo.counter + _ANo.counter
							+ _CNo.counter - result.counter;
					// round = 0;
					// while ((inx = (list.size() - same) * round + i) < same) {
					// round++;
					// if (list.get(inx) == result.value) {
					// continue;
					// } else {
					// list.set(inx, result.value);
					// v++;
					// }
					// }

				}
				System.out.println(max);
			}

		}
	}

	public static int distance(String s, int n) {
		int length = s.length();
		if (n == length) {
			return 0;
		}
		if (n == 1) {
			if (s.charAt(0) == s.charAt(length - 1)) {
				return 0;
			}
			return 1;
		}

		int distance = 0;
		if (n <= length / 2) {
			for (int i = 0; i < n; i++) {
				if (s.charAt(i) != s.charAt(i + length - n)) {
					distance++;
				}
			}
			return distance;
		}

		int overlap = length - n;
		int sum = 0;
		for (int i = 0; i < overlap; i++) {
			int[] flag = new int[4];
			int x = i;
			while (x < length) {
				if (s.charAt(x) == 'A') {
					flag[0] = flag[0] + 1;
				} else if (s.charAt(x) == 'C') {
					flag[1] = flag[1] + 1;
				} else if (s.charAt(x) == 'G') {
					flag[2] = flag[2] + 1;
				} else if (s.charAt(x) == 'T') {
					flag[3] = flag[3] + 1;
				}
				x = x + overlap;
			}
			int max = 0;
			for (int j = 0; j < 4; j++) {
				if (flag[j] > max) {
					max = flag[j];
				}
				sum = sum + flag[j];
			}
			sum = sum - max;

		}
		return sum;
	}

	private static class Pair {
		int counter = 0;
		byte value;

		public Pair(byte b) {
			this.value = b;
		}
	}
}
