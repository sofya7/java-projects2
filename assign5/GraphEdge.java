
/**
 * This class implements an edge of the graph.
 */
public class GraphEdge {

	private GraphNode end1;
	private GraphNode end2;
	private char bus;

	/**
	 * The constructor for the class. The first two parameters are the endpoints of
	 * the edge. The last parameter is the bus line to which the street represented
	 * by the edge belongs. Each bus line has a name that consists of 2 a single
	 * character: Either a digit or a letter, except ’S’ and ’D’ which are used to
	 * mark the starting point and the destination. For example ’a’, ’I’, and ’2’
	 * might be the names of three bus lines. Note that case matters, so a bus line
	 * might have name ’a’ and another might have name ’A’. There might be bus lines
	 * called ’s’ or ’d’, but no bus line can be called ’S’ or ’D’.
	 * 
	 * @param u
	 * @param v
	 * @param busLine
	 */
	public GraphEdge(GraphNode u, GraphNode v, char busLine) {
		end1 = u;
		end2 = v;
		bus = busLine;
	}

	/**
	 * Returns the first endpoint of the edge.
	 * 
	 * @return end1
	 */
	public GraphNode firstEndpoint() {
		return end1;
	}

	/**
	 * Returns the second endpoint of the edge.
	 * 
	 * @return end2
	 */
	public GraphNode secondEndpoint() {
		return end2;
	}

	/**
	 * Returns the name of the busLine to which the edge belongs.
	 * 
	 * @return bus
	 */
	public char getBusLine() {
		return bus;
	}

}
