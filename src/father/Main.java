package father;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static class Node {
		public boolean isSet = false;
		public Node father = null;
		public String name = null;

		public Node(String name) {
			this.name = name;
		}

		public Node(String name, Node father) {
			this.name = name;
			this.father = father;
		}

		@Override
		public String toString() {
			return name;
		}

		@Override
		public boolean equals(Object node) {
			if (node == null)
				return false;
			if (this.name.equals(((Node) node).name)) {
				return true;
			}
			return false;
		}
	}

	static List<Node> nodeList = new ArrayList<Node>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {

			int total = in.nextInt();
			if (total <= 0)
				return;
			for (int i = 0; i < total; i++) {
				Node[] tmps = new Node[2];
				tmps[0] = new Node(in.next());
				Node father = getFatherNode(tmps[0]);
				nodeList.remove(father);
				nodeList.add(father);
				tmps[1] = new Node(in.next(), father);
				fill(tmps[1]);
			}

			int totalQuestion = in.nextInt();
			for (int i = 0; i < totalQuestion; i++) {
				Node[] tmps = new Node[2];
				tmps[0] = new Node(in.next());
				tmps[1] = new Node(in.next());
				if (tmps[0].equals(tmps[1])) {
					System.out.println(tmps[0]);
					continue;
				}
				if (!nodeList.contains(tmps[0]) || !nodeList.contains(tmps[1])) {
					System.out.println(-1);
					continue;
				}

				Node node1 = nodeList.get(nodeList.indexOf(tmps[0]));
				markNode(node1, true);
				Node node2 = nodeList.get(nodeList.indexOf(tmps[1]));
				System.out.println(trace(node2));
				unmarkNode(node1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Node getFatherNode(Node input) {
		if (nodeList.contains(input)) {
			return nodeList.get(nodeList.indexOf(input));
		} else {
			for (Node node : nodeList) {
				Node n = searchNode(node.father, input);
				if (n != null) {
					return n;
				}
			}
		}
		return input;
	}

	private static Node searchNode(Node father, Node input) {
		if (father != null) {
			if (father.equals(input)) {
				return father;
			} else {
				searchNode(father.father, input);
			}
		}
		return null;
	}

	private static void fill(Node child) {
		boolean contains = false;
		if (!nodeList.contains(child)) {
			// for (Node node : nodeList) {
			// if (node.father != null) {
			// contains |= search(child, node.father);
			// }
			// }
			// if (!contains) {
			nodeList.add(child);
			// }
		}

	}

	private static boolean search(Node newNode, Node curNode) {
		if (newNode.equals(curNode)) {
			curNode.father = newNode.father;
			return true;
		} else if (curNode.father != null) {
			return search(newNode, curNode.father);
		} else {
			return false;
		}
	}

	private static void unmarkNode(Node node1) {
		node1.isSet = false;
		if (node1.father != null) {
			markNode(node1.father, false);
		}

	}

	private static String trace(Node node2) {
		if (node2.isSet) {
			return node2.name;
		} else {
			if (node2.father == null) {
				return "-1";
			}
			return trace(node2.father);
		}
	}

	private static void markNode(Node node1, boolean isSet) {
		node1.isSet = isSet;
		if (node1.father != null) {
			markNode(node1.father, isSet);
		}

	}

}
