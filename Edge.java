public class Edge {
	private Vertex vertex1;
	private Vertex vertex2;

	public Edge(Vertex v1, Vertex v2) {
		this.vertex1 = v1;
		this.vertex2 = v2;
	}

	public Edge(int v1, int v2) {
		this.vertex1 = new Vertex(v1);
		this.vertex2 = new Vertex(v2);
	}

	public Edge(Vertex[] edge) {
		this.vertex1 = edge[0];
		this.vertex2 = edge[1];
	}

	public Edge(int[] edge) {
		this.vertex1 = new Vertex(edge[0]);
		this.vertex2 = new Vertex(edge[1]);
	}

	public Edge(Vertex[]... edges) {
		for (Vertex[] edge : edges) {
			new Edge(edge);
		}
	}

	public Edge(int[]... edges) {
		for (int[] edge : edges) {
			new Edge(edge);
		}
	}

	public Vertex getVertex1() {
		return this.vertex1;
	}

	public Vertex getVertex2() {
		return this.vertex2;
	}

	public boolean hasVertex(Vertex vertex) {
		if (this.vertex1.getValue() == vertex.getValue() || this.vertex2.getValue() == vertex.getValue())
			return true;
		else
			return false;
	}

	public String toString() {
		return "(" + vertex1 + ", " + vertex2 + ")";
	}
}
