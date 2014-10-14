package father2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	private static class Node {

		private static final byte in = 1;
		private static final byte out = 2;

		private byte val = 0;
		private Node father = null;
		private String name = null;
		private List<Node> children = new ArrayList<Main.Node>();

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

	private static Map<String, Node> nodeMap = new HashMap<String, Node>();

	/**
	 * question a & b will be saved twice (<a,b> <b,a>) for search convenience
	 */
	private static Map<String, Map<String, List<Integer>>> questionMap = new HashMap<String, Map<String, List<Integer>>>();

	private static String[] answer;

	private static Stack<Node> tracingNodeStack = new Stack<Node>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			int total = in.nextInt();
			if (total <= 0)
				return;
			String fatherName = in.next();
			Node root = new Node(fatherName);
			String sonName = in.next();
			Node n = new Node(sonName, root);
			root.children.add(n);
			nodeMap.put(fatherName, root);
			nodeMap.put(sonName, n);

			for (int i = 0; i < total - 1; i++) {
				fatherName = in.next();
				Node father = buildNode(fatherName);
				sonName = in.next();
				Node son = new Node(sonName, father);
				father.children.add(son);
				nodeMap.put(fatherName, father);
				nodeMap.put(sonName, son);
			}
			printTree(root, 0);

			int totalQuestion = in.nextInt();
			answer = new String[totalQuestion];
			for (int i = 0; i < totalQuestion; i++) {
				String name1 = in.next();
				String name2 = in.next();
				if (name1.equals(name2)) {
					answer[i] = name1;
				} else {
					Map<String, List<Integer>> m = questionMap.get(name1);
					if (m == null) {
						m = new HashMap<String, List<Integer>>();
						List<Integer> list = new ArrayList<Integer>();
						list.add(i);
						m.put(name2, list);
					} else {
						List<Integer> list = m.get(name2);
						if (list == null) {
							list = new ArrayList<Integer>();
							m.put(name2, list);
						}
						list.add(i);
					}
					questionMap.put(name1, m);

					m = questionMap.get(name2);
					if (m == null) {
						m = new HashMap<String, List<Integer>>();
						List<Integer> list = new ArrayList<Integer>();
						list.add(i);
						m.put(name1, list);
					} else {
						List<Integer> list = m.get(name1);
						if (list == null) {
							list = new ArrayList<Integer>();
							m.put(name1, list);
						}
						list.add(i);
					}
					questionMap.put(name2, m);
				}
			}

			// dfs tree
			dfs(root);
			for (String name : answer) {
				System.out.println(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void dfs(Node node) {
		tracingNodeStack.add(node);
		node.val++;
		Map<String, List<Integer>> values = questionMap.get(node.name);
		if (values != null) {
			for (Entry<String, List<Integer>> entry : values.entrySet()) {
				String questionPart2 = entry.getKey();
				if (nodeMap.get(questionPart2).val == Node.in) {
					// key1 in, key2 in, then key2 is father
					for (int inx : entry.getValue()) {
						answer[inx] = questionPart2;
					}
				} else if (nodeMap.get(questionPart2).val == Node.out) {
					// key1 in, key2 out
					Node n = nodeMap.get(questionPart2);
					Node father = getLatestInNode(n);
					for (int inx : entry.getValue()) {
						answer[inx] = father.name;
					}
					n.father = father;
				}

			}
		}
		if (!node.children.isEmpty()) {
			for (Node child : node.children) {
				dfs(child);
			}
		}
		// leave this node
		node.val++;
		tracingNodeStack.pop();
	}

	private static Node getLatestInNode(Node n) {
		if (n.val == Node.in) {
			return n;
		}
		if (n.father != null) {
			return getLatestInNode(n.father);
		}
		return n;
	}

	private static Node buildNode(String name) {
		Node node = new Node(name);
		if (nodeMap.get(name) != null) {
			node = nodeMap.get(name);
		}
		return node;
	}

	public static void printTree(Node node, int level) {
		// System.out.print(level + "|");
		for (int i = 0; i < level; i++) {
			System.out.print("\t");
		}
		System.out.println(node.name);
		for (Node child : node.children) {
			printTree(child, level + 1);
		}

	}
}
