
/**
 * This class implements a node of the graph.
 */
public class GraphNode {

	private int name;
	private boolean mark;

	/**
	 * This is the constructor for the class and it creates an unmarked node (see
	 * below) with the given name. The name of a node is an integer value between 0
	 * and n-1, where n is the number of nodes in the graph.
	 */
	public GraphNode(int name) {
		this.name = name;
		mark = false;
	}

	/**
	 * Marks the node with the specified value.
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	}

	/**
	 * Returns the value with which the node has been marked.
	 * 
	 * @return mark
	 */
	public boolean getMark() {
		return mark;
	}

	/**
	 * Returns the name of the node.
	 * 
	 * @return name
	 */
	public int getName() {
		return name;
	}
}
