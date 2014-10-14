package father2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	static ThreadLocal<T> t = new ThreadLocal<T>();
	
	private static class Node {

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

		public byte getVal() {
			return val;
		}

		public void setVal(byte val) {
			this.val = val;
		}

		public Node getFather() {
			return father;
		}

		public void setFather(Node father) {
			this.father = father;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Node> getChildren() {
			return children;
		}

		public void setChildren(List<Node> children) {
			this.children = children;
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

	static Map<String, Node> nodeMap = new HashMap<String, Node>();

	static Node root;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			int total = in.nextInt();
			if (total <= 0)
				return;
			root = new Node(in.next());
			Node n = new Node(in.next(), root);

			for (int i = 0; i < total - 2; i++) {
				String fatherName = in.next();
				Node father = buildNode(fatherName);
				String sonName = in.next();
				Node son = new Node(sonName, father);
				nodeMap.put(fatherName, father);
				nodeMap.put(sonName, son);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Node buildNode(String name) {
		Node node = new Node(name);
		if (nodeMap.get(name) != null) {
			node = nodeMap.get(name);
		}
		return node;
	}

}
