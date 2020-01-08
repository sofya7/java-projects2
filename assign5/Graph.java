import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class implements an undirected graph.
 *
 */
public class Graph implements GraphADT {

	private int numNodes;
	private GraphEdge[][] adjMatrix;
	private GraphNode[] list;

	/**
	 * Creates a graph with n nodes and no edges. This is the constructor for the
	 * class. The names of the nodes are 0, 1, . . . , n-1.
	 * 
	 * @param n
	 */
	public Graph(int n) {

		numNodes = n;
		adjMatrix = new GraphEdge[n][n];
		list = new GraphNode[n]; // to store nodes
		for (int i = 0; i < n; i++) { // to put each node in the list of nodes
			list[i] = new GraphNode(i);
		}
	}

	/**
	 * Adds an edge connecting u and v and belonging to the specified bus line. This
	 * method throws a GraphException if either node does not exist or if in the
	 * graph there is already an edge connecting the given nodes.
	 * 
	 * @param u
	 * @param v
	 * @param busLine
	 * @throws GraphException
	 */
	public void insertEdge(GraphNode u, GraphNode v, char busLine) throws GraphException {

		// if one of the nodes or its name does not exist, throw an error msg
		if (u == null || v == null || u.getName() >= numNodes || v.getName() >= numNodes) {
			throw new GraphException("Node does not exist!");
		}

		// if nodes with such names already have connected edge, throw an error msg
		if (adjMatrix[u.getName()][v.getName()] != null) {
			throw new GraphException("There is already an edge connecting the given nodes!");
		}
		// to add the edges to the adjacency matrix.
		adjMatrix[u.getName()][v.getName()] = new GraphEdge(u, v, busLine);
		adjMatrix[v.getName()][u.getName()] = new GraphEdge(v, u, busLine);
	}

	/**
	 * Returns the node with the specified name. If no node with this name exists,
	 * the method should throw a GraphException.
	 * 
	 * @param name
	 * @return GraphNode
	 * @throws GraphException
	 */
	public GraphNode getNode(int name) throws GraphException {

		// Checks if a Node of the name u exist in the graph
		if (name > numNodes - 1 || list[name] == null)
			throw new GraphException("No node with this name exists!");
		return list[name]; // returns the node's name.

	}

	/**
	 * Returns a Java Iterator storing all the edges incident on node u. It returns
	 * null if u does not have any edges incident on it.
	 * 
	 * @param u
	 * @return Iterator<GraphEdge>
	 * @throws GraphException
	 */
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {

		// make a list that stores all the edges that specified node can have
		List<GraphEdge> listEdges = new ArrayList<GraphEdge>();

		// to make sure the node exists in the graph
		if (u.getName() > numNodes - 1)
			throw new GraphException("This node is not in the graph!");

		// check each position of adjacency matrix to see if specified node already has
		// incident edges
		for (int i = 0; i < numNodes; i++) {
			if (adjMatrix[u.getName()][i] != null)
				listEdges.add(adjMatrix[u.getName()][i]);
		}
		if (listEdges.isEmpty())
			return null;
		// shows what is in the list of graph edges
		return listEdges.iterator();
	}

	/**
	 * Returns the edge connecting nodes u and v. This method throws a
	 * GraphException if there is no edge between u and v.
	 * 
	 * @param u
	 * @param v
	 * @return GraphEdge
	 * @throws GraphException
	 */
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {

		// if either of nodes don't exist or adj matrix does not store any edge between
		// two nodes, throw exception
		if (u.getName() >= numNodes || v.getName() >= numNodes || list[u.getName()] == null || list[v.getName()] == null
				|| adjMatrix[u.getName()][v.getName()] == null)
			throw new GraphException("There is no edge between the two nodes!");

		return adjMatrix[u.getName()][v.getName()];

		/**
		 * //otherwise, as long as there is a bus line in the matrix return that spot if
		 * (adjMatrix[u.getName()][v.getName()].getBusLine() != null ||
		 * adjMatrix[v.getName()][u.getName()].getBusLine() != null) { return
		 * adjMatrix[u.getName()][v.getName()]; }
		 * 
		 * return null;
		 */

	}

	/**
	 * Returns true if nodes u and v are adjacent; it returns false otherwise.
	 * 
	 * @param u
	 * @param v
	 * @return true or false
	 * @throws GraphException
	 */
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {

		/**
		 * Creates and initializes the values of uName and vName. int uName=u.getName(),
		 * vName=v.getName();
		 */

		// checks if nodes u and v exist in the graph
		if (u.getName() > numNodes - 1 || v.getName() > numNodes - 1 || list[u.getName()] == null
				|| list[v.getName()] == null)
			throw new GraphException("This(These) node(s) is(are) not in the graph!");
		// if there is an edge at that spot in the matrix, return true
		if (adjMatrix[u.getName()][v.getName()] == null)
			return false;
		else
			return true;

	}
}
