import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public class BusLines {

	private int width;
	private int length;
	private int maxBusChanges;
	private int startPoint;
	private int endPoint;
	private int c;
	private int temp;
	private String line11;
	private String line2;
	private char[] charList;
	private char[] charList2;
	private BufferedReader in;
	private Graph graph;
	private Stack<GraphNode> aStack = new Stack<GraphNode>();
	private GraphNode stackHelp;
	private Boolean done;
	private GraphNode startGraph;
	private GraphNode endGraph;

	/**
	 * Constructor for building a city map with its bus lines from the input file.
	 * 
	 * @param inputFile
	 * @throws MapException
	 */
	public BusLines(String inputFile) throws MapException {

		try {
			c = 0;// set a counter c to zero
			in = new BufferedReader(new FileReader(inputFile));// try accessing the file
			String line = in.readLine();
			String[] line1 = line.split(" ");
			width = Integer.parseInt(line1[1]);
			length = Integer.parseInt(line1[2]);
			graph = new Graph(width * length);
			maxBusChanges = Integer.parseInt(line1[3]);
			line11 = in.readLine();
			line2 = in.readLine();

			while (line11 != null) {
				charList = line11.toCharArray();
				if (line2 != null) {
					charList2 = line2.toCharArray();
				}

				if (line2 == null && width * length - 1 >= c + 1) {
					temp = c;
					for (int i = 0; i < width * 2 - 1; i++) {
						if (charList[i] == ' ')
							temp++;
						if (charList[i] == 'S') {
							startPoint = temp;
							continue;
						} else if (charList[i] == 'D') {
							endPoint = temp;
							continue;
						}

						if (Character.isDigit(charList[i]) || Character.isLetter(charList[i])) {
							this.graph.insertEdge(graph.getNode(temp), graph.getNode(temp + 1), charList[i]);
							temp++;
						}
					}

				} else {

					for (int i = 0; i < width * 2 - 1; i++) {

						if (charList[i] == 'S') {
							startPoint = c;
						}
						if (charList[i] == 'D') {
							endPoint = c;
						}

						if (i % 2 == 0) {
							if (Character.isDigit(charList2[i]) || Character.isLetter(charList2[i])) {
								this.graph.insertEdge(graph.getNode(c), graph.getNode(c + width), charList2[i]);
							}

						}
						if (i % 2 != 0) {
							if (Character.isDigit(charList[i]) || Character.isLetter(charList[i])) {
								this.graph.insertEdge(graph.getNode(c), graph.getNode(c + 1), charList[i]);
							}
							c++;
						}
					}
				}
				c++;
				line11 = in.readLine();
				line2 = in.readLine();
			}
		} catch (IOException e) {
			System.out.println("Cannot open or read the file!");
		} catch (GraphException e) {
			throw new MapException("Graph cannot be created!");
		}
	}

	/**
	 * Returns the graph representing the map. This method throws a MapException if
	 * the graph could not be created.
	 * 
	 * @return
	 * @throws MapException
	 */
	Graph getGraph() throws MapException {

		// if graph exists, return it
		if (graph != null) {
			return graph;
		} else
			throw new MapException("Graph does not exist!");
	}

	/**
	 * Returns the nodes along the path from the starting point to the destination,
	 * if such a path exists.
	 * 
	 * @param graph
	 * @param startNode
	 * @param endNode
	 * @param busLine
	 * @param maxBusChange
	 * @return
	 * @throws MapException
	 * @throws GraphException
	 */
	private Boolean tripDone(Graph graph, GraphNode startNode, GraphNode endNode, char busLine, int maxBusChange)
			throws MapException, GraphException {
		this.graph = graph;
		Iterator<GraphEdge> edgeIt = null;
		if (graph == null)
			throw new MapException("Graph could not be created!");

		edgeIt = graph.incidentEdges(startNode);
		if (edgeIt == null)
			throw new GraphException("Graph could not be created!");
		startNode.setMark(true);
		aStack.push(startNode);

		if (startNode.getName() == endNode.getName()) {
			return true;
		}

		while (edgeIt.hasNext()) {
			GraphEdge nextEdge = edgeIt.next();
			if (nextEdge.getBusLine() != busLine) {
				if (nextEdge.firstEndpoint().equals(startNode) && nextEdge.secondEndpoint().getMark() == false
						&& maxBusChange > 0) {
					if (tripDone(graph, nextEdge.secondEndpoint(), endNode, nextEdge.getBusLine(), maxBusChange - 1)) {
						return true;
					}
				}
				if (nextEdge.secondEndpoint().equals(startNode) && nextEdge.firstEndpoint().getMark() == false
						&& maxBusChange > 0) {
					if (tripDone(graph, nextEdge.firstEndpoint(), endNode, nextEdge.getBusLine(), maxBusChange - 1))
						return true;
				}
			}
			if (nextEdge.getBusLine() == busLine) {
				if (nextEdge.firstEndpoint().equals(startNode) && nextEdge.secondEndpoint().getMark() == false) {
					if (tripDone(graph, nextEdge.secondEndpoint(), endNode, busLine, maxBusChange) == true) {
						return true;
					}
				}
				if (nextEdge.secondEndpoint().equals(startNode) && nextEdge.firstEndpoint().getMark() == false) {
					if (tripDone(graph, nextEdge.firstEndpoint(), endNode, busLine, maxBusChange) == true) {
						return true;
					}
				}
			}

		}
		stackHelp = aStack.pop();
		stackHelp.setMark(false);
		return false;
	}

	/**
	 * Returns a Java Iterator containing the nodes along the path from the starting
	 * point to the destination, if such a path exists. If the path does not exist,
	 * this method returns the value null.
	 * 
	 * @return
	 * @throws GraphException
	 */
	public Iterator<GraphNode> trip() throws GraphException {

		try {
			startGraph = graph.getNode(startPoint);
			endGraph = graph.getNode(endPoint);
			aStack = new Stack<GraphNode>();
			done = tripDone(graph, startGraph, endGraph, ' ', maxBusChanges + 1);
			if (done == false)
				return null;
		} catch (GraphException e) {
			System.out.println("There is a problem iterating through graph!");
		} catch (MapException e) {
			System.out.println("There is a problem iterating through graph!");
		}
		return aStack.iterator();
	}
}