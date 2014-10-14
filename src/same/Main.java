package same;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private static class Unit {
		private static int counter;
		// int inx;
		int a;
		private Unit next;

		public Unit() {
			a = counter++;
		}
	}

	private static int inx = 0;

	private static Map<String, Unit> indexMap = new HashMap<String, Unit>();

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int total = in.nextInt();
		for (int i = 0; i < total; i++) {
			int aOrQ = in.nextInt();
			String name1 = in.next();
			String name2 = in.next();
			if (aOrQ == 0) {
				if (!indexMap.containsKey(name1)
						&& !indexMap.containsKey(name2)) {
					Unit unit = new Unit();
					// unit.inx = inx;
					Unit unit1 = new Unit();
					// unit1.inx = inx;
					unit1.next = unit;
					if (name1.compareTo(name2) > 0) {
						indexMap.put(name1, unit);
						indexMap.put(name2, unit1);
					} else {
						indexMap.put(name2, unit);
						indexMap.put(name1, unit1);
					}

					// inx++;
				} else if (indexMap.containsKey(name1)
						&& indexMap.containsKey(name2)) {
					if (name1.compareTo(name2) > 0) {
						Unit u = detail(indexMap.get(name2));
						Unit u1 = detail(indexMap.get(name1));
						if (u1.a != u.a) {
							u.next = u1;
						}
					} else {
						Unit u = detail(indexMap.get(name1));
						Unit u1 = detail(indexMap.get(name2));
						if (u1.a != u.a) {
							u.next = u1;
						}
					}
				} else {
					if (indexMap.containsKey(name1)) {
						Unit unit = new Unit();
						if (name1.compareTo(name2) > 0) {
							unit.next = detail(indexMap.get(name1));
							indexMap.put(name2, unit);
						} else {
							Unit u1 = detail(indexMap.get(name1));
							u1.next = unit;
							// unit.inx = inx;
							inx++;
							indexMap.put(name2, unit);
						}
					} else {
						Unit unit = new Unit();
						if (name1.compareTo(name2) < 0) {
							unit.next = detail(indexMap.get(name2));
							indexMap.put(name1, unit);
						} else {
							Unit u1 = detail(indexMap.get(name2));
							u1.next = unit;
							// unit.inx = inx;
							inx++;
							indexMap.put(name1, unit);
						}
					}
				}
			} else {
				if (name1.equals(name2)) {
					System.out.println("yes");
				} else if (!indexMap.containsKey(name1)
						|| !indexMap.containsKey(name2)) {
					System.out.println("no");
				} else {
					Unit u1 = detail(indexMap.get(name1));
					Unit u2 = detail(indexMap.get(name2));

					System.out.println((u1.a == u2.a) ? "yes" : "no");
				}
			}
		}
	}

	private static Unit detail(Unit unit) {
		if (unit.next != null)
			return detail(unit.next);
		return unit;
	}
}
