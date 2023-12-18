import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> verticesList;
	private ArrayList<Edge> edgeList;

	public Graph(Vertex[] newVertices, Edge[] newEdges) {
		edgeList = new ArrayList<Edge>();
		for (Edge edgeList : newEdges)
			this.edgeList.add(edgeList);
		verticesList = new ArrayList<Vertex>();
		for (Vertex vertexList : newVertices)
			this.verticesList.add(vertexList);
	}

	public Graph(int[] newVertices, int[]... newEdges) {
		edgeList = new ArrayList<Edge>();
		for (int[] edgeList : newEdges)
			this.edgeList.add(new Edge(new Vertex(edgeList[0]), new Vertex(edgeList[1])));
		verticesList = new ArrayList<Vertex>();
		for (int vertexList : newVertices)
			this.verticesList.add(new Vertex(vertexList));
	}
	public ArrayList<Vertex> getVertices() {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		for (Vertex vertex : this.verticesList)
			vertices.add(vertex);
		return vertices;
	}

	public Vertex getVertex(int i) {
		return verticesList.get(i);
	}

	public ArrayList<Edge> getEdges() {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (Edge edge : this.edgeList)
			edges.add(edge);
		return edges;
	}



	public int numVertices() {
		return verticesList.size();
	}

	public String toString() {
		return "Vertices: " + verticesList.toString() + "\n\nEdges: " + edgeList.toString();
	}
}





