package _1014;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Node root = new Node(' ', 1);

	private static class Node implements Comparable<Node> {

		public int counter = 0;

		public char value;

		public Node(char n, int counter) {
			value = n;
			this.counter = counter;
		}

		private List<Node> next = new ArrayList<Node>();

		@Override
		public int compareTo(Node o) {
			return value - o.value;
		}

		@Override
		public String toString() {
			return value + ":" + counter;
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int total = in.nextInt();
		for (int counter = 0; counter < total; counter++) {
			String word = in.next();
			insertNode(root, word);
		}
		int ask = in.nextInt();
		for (int counter = 0; counter < ask; counter++) {
			String word = in.next();
			System.out.println(find(root, word));
		}
	}

	private static int find(Node node, String prefix) {
		char[] chars = prefix.toCharArray();
		for (char c : chars) {
			int inx = Collections.binarySearch(node.next, new Node(c, 1));
			if (inx < 0) {
				return 0;
			}
			node = node.next.get(inx);
		}
		return node.counter;
	}

	private static void insertNode(Node node, String word) {
		char[] chars = word.toCharArray();
		int i = 0;
		Node fatherNode = node;
		for (; i < chars.length; i++) {
			int inx = Collections.binarySearch(fatherNode.next, new Node(
					chars[i], 1));
			fatherNode.counter++;
			if (inx < 0) {
				Node newNode = new Node(chars[i], 1);
				fatherNode.next.add(-inx - 1, newNode);
				fatherNode = fatherNode.next.get(-inx - 1);
				break;
			}
			fatherNode = fatherNode.next.get(inx);
		}
		if (i == chars.length) {
			fatherNode.counter++;
		}
		i++;

		for (; i < chars.length; i++) {
			Node newNode = new Node(chars[i], 1);
			fatherNode.next.add(newNode);
			fatherNode = fatherNode.next.get(0);
		}
	}

}
