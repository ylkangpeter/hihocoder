package util;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

	private static int level = 0;

	public static void printTree(Node node, int level) {
		// System.out.print(level + "|");
		for (int i = 0; i < level; i++) {
			System.out.print("\t");
		}
		System.out.println(node.value);
		for (Node child : node.children) {
			printTree(child, level + 1);
		}

	}

	public static void main(String[] args) {
		Node _1 = new Node("1");
		Node _2 = new Node("2", _1);
		Node _3 = new Node("3", _1);
		Node _4 = new Node("4", _1);
		Node _5 = new Node("5", _3);
		Node _6 = new Node("6", _3);
		Node _7 = new Node("7", _5);
		Node _8 = new Node("8", _5);
		Node _9 = new Node("9", _8);
		Node _10 = new Node("10", _4);
		Node _11 = new Node("11", _10);
		_1.getChildren().add(_2);
		_1.getChildren().add(_3);
		_1.getChildren().add(_4);
		_3.getChildren().add(_5);
		_3.getChildren().add(_6);
		_4.getChildren().add(_10);
		_5.getChildren().add(_7);
		_5.getChildren().add(_8);
		_8.getChildren().add(_9);
		_10.getChildren().add(_11);
		printTree(_1, 0);
	}

	private static class Node {

		private boolean isSet = false;
		private Node father = null;
		private String value = null;
		private List<Node> children = new ArrayList<Node>();

		public Node(String value) {
			this.value = value;
		}

		public Node(String value, Node father) {
			this.value = value;
			this.father = father;
		}

		public boolean isSet() {
			return isSet;
		}

		public void setSet(boolean isSet) {
			this.isSet = isSet;
		}

		public Node getFather() {
			return father;
		}

		public void setFather(Node father) {
			this.father = father;
		}

		public List<Node> getChildren() {
			return children;
		}

		public void setChildren(List<Node> children) {
			this.children = children;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public boolean equals(Object node) {
			if (node == null)
				return false;
			if (this.value.equals(((Node) node).value)) {
				return true;
			}
			return false;
		}
	}
}
