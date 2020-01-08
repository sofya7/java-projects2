/**
 * Node represents a node in a binary tree with a left and right child.
 */
public class Node {
	private Record record;
	private Node left, right, parent;

	/**
	 * Creates a tree node with the specified data.
	 *
	 * @param obj
	 *            the element that will become a part of the new tree node
	 */
	public Node(Record record) {
		this.record = record;
		this.left = new Node();
		this.right = new Node();
		this.parent = null;
	}

	/**
	 * Creates a tree node without data.
	 *
	 */
	public Node() {
		this.record = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	/**
	 * Returns the node that is left child
	 *
	 * @return left child
	 */

	public Node getLeft() {
		return this.left;
	}

	/**
	 * Returns the node that is right child
	 *
	 * @return right child
	 */
	public Node getRight() {
		return this.right;
	}

	/**
	 * Sets the node that follows this one on the left
	 *
	 * @param node
	 *            to be set to follow the current one
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * Sets the node that follows this one on the right
	 *
	 * @param node
	 *            to be set to follow the current one
	 */
	public void setRight(Node right) {
		this.right = right;
	}

	/**
	 * Returns the node that is parent
	 *
	 * @return parent
	 */
	public Node getParent() {
		return this.parent;
	}

	/**
	 * Sets the node's parent
	 *
	 * @param node
	 *            to be set as parent
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Returns Record stored in this node
	 *
	 * @return Record stored in this node
	 */
	public Record getRecord() {
		return this.record;
	}

	/**
	 * Sets the element stored in this node.
	 *
	 * @param record
	 */
	public void setRecord(Record record) {
		this.record = record;
	}

	/**
	 * Sets the element stored in this node.
	 *
	 * @param record
	 */
	public boolean isLeaf() {
		if (this.getRecord() == null)
			return true;
		else
			return false;
	}
}
