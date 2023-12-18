public class VertexCoverTester {
	public static void main(String[] args) {
		Vertex[] vertices = {new Vertex(2), new Vertex(4), new Vertex(6), new Vertex(8), new Vertex(10)};
		Edge[] graphEdges = {new Edge(2,4), new Edge(2,6), new Edge(2,8),
				new Edge(2,10), new Edge(4,6), new Edge(4,8),
				new Edge(8,10)};
		Graph testGraph = new Graph(vertices, graphEdges);
		System.out.println("# of Vertices: " + testGraph.numVertices());
		System.out.println(testGraph);

		VertexCover vertexCover = new VertexCover(testGraph, 5);
		vertexCover.bruteForce();
		vertexCover.optimizedBruteForce();
		vertexCover.greedyCover();
		vertexCover.approxCover();
		System.out.println();

		Vertex[] vertices2 = {new Vertex(11), new Vertex(8), new Vertex(10),
				new Vertex(4), new Vertex(7), new Vertex(6),
				new Vertex(3)};
		Edge[] graphEdges2 = {new Edge(11,8), new Edge(8,10), new Edge(10,4),
				new Edge(10,7), new Edge(4,7), new Edge(4,6),
				new Edge(4,3), new Edge(7,6)};
		testGraph = new Graph(vertices2, graphEdges2);
		System.out.println("# of Vertices: " + testGraph.numVertices());
		System.out.println(testGraph);

		vertexCover = new VertexCover(testGraph, 6);
		vertexCover.bruteForce();
		vertexCover.optimizedBruteForce();
		vertexCover.greedyCover();
		vertexCover.approxCover();
		System.out.println();

		Vertex[] vertices3 = {new Vertex(1), new Vertex(2), new Vertex(3),
				new Vertex(4), new Vertex(5), new Vertex(6),
				new Vertex(7), new Vertex(8), new Vertex(9)};
		Edge[] graphEdges3 = {new Edge(1,2), new Edge(2,3), new Edge(3,4),
				new Edge(3,5), new Edge(4,5), new Edge(4,6),
				new Edge(4,7), new Edge(5,6), new Edge(4,8),
				new Edge(5,9)};
		testGraph = new Graph(vertices3, graphEdges3);
		System.out.println("number of Vertices is : " + testGraph.numVertices());
		System.out.println(testGraph);

		vertexCover = new VertexCover(testGraph, 7);
		vertexCover.bruteForce();
		vertexCover.optimizedBruteForce();
		vertexCover.greedyCover();
		vertexCover.approxCover();
		System.out.println();
	}
}