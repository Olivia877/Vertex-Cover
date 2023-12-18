import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VertexCover {
	private Graph graph;
	private int kValue;
	private int minVertices;
	private ArrayList<Vertex> vertexCover;
	ArrayList<ArrayList<Vertex>> possibleCovers = new ArrayList<ArrayList<Vertex>>();

	public VertexCover(Graph graph, int kValue) {
		this.graph = graph;
		this.kValue = kValue;
	}

	protected void removeEdges(Vertex vertex, ArrayList<Edge> edgeList) {
		ArrayList<Edge> removeEdge = new ArrayList<Edge>();
		for (int i = 0; i < edgeList.size(); i++) {
			if ((edgeList.get(i)).hasVertex(vertex))
				removeEdge.add(edgeList.get(i));
		}
		for (int i = 0; i < removeEdge.size(); i++)
			edgeList.remove(removeEdge.get(i));
	}

	protected Vertex getMaxDegree(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
		Map<Vertex, Integer> vertexDegreeMap = new HashMap<>();
		int maxDegree = 0;
		Vertex maxVertex = new Vertex();
		for (Vertex vertex : vertices) {
			vertexDegreeMap.put(vertex, 0);
			for (Edge edge : edges) {
				if (edge.hasVertex(vertex)) {
					vertexDegreeMap.put(vertex, vertexDegreeMap.get(vertex) + 1);
				}
			}
			if (vertexDegreeMap.get(vertex) > maxDegree) {
				maxDegree = vertexDegreeMap.get(vertex);
				maxVertex = vertex;
			}
		}
		return maxVertex;
	}

	protected void combinations(ArrayList<Vertex> vertices, int k, ArrayList<Vertex> current, int currentTotal, boolean[] visit) {
		if (currentTotal >= k - 1) {
			ArrayList<Vertex> temp = new ArrayList<>();
			for (int i = 0; i < current.size(); i++)
				temp.add(current.get(i));
			possibleCovers.add(temp);
			return;
		}
		for (int i = 0; i < vertices.size(); i++) {
			if (!visit[i]) {
				current.set(++currentTotal, vertices.get(i));
				visit[i] = true;
				combinations(vertices, k, current, currentTotal, visit);
				visit[i] = false;
				currentTotal--;
			}
		}
	}

	protected void bruteForce() {
		long startTime = System.currentTimeMillis();
		possibleCovers = new ArrayList<>();
		ArrayList<Vertex> v = new ArrayList<>();
		for (int i = 0; i < this.kValue; i++) {
			v.add(new Vertex(i));
		}
		boolean[] visit = new boolean[graph.getVertices().size()];
		for (int i = 0; i < graph.getVertices().size(); i++) {
			visit[i] = false;
		}
		this.combinations(graph.getVertices(), this.kValue, v, -1, visit);
		ArrayList<Edge> edges = graph.getEdges();
		vertexCover = new ArrayList<Vertex>();
		int min = this.kValue + 1;
		int coverIndex = 0;
		for (int i = 0; i < possibleCovers.size(); i++) {
			for (int j = 0; j < this.kValue; j++) {
				this.removeEdges(possibleCovers.get(i).get(j), edges);
				if (edges.isEmpty()) {
					if ((j + 1) < min) {
						min = (j + 1);
						coverIndex = i;
					}
				}
			}
			edges = graph.getEdges();
		}
		if (min <= kValue) {
			for (int i = 0; i < min; i++)
				vertexCover.add(possibleCovers.get(coverIndex).get(i));
			System.out.println("************************BRUTE FORCE************************************");
			System.out.println("A brute force method has been discovered, meeting the condition k = " + kValue);
			System.out.println("The vertices of the smallest cover are: " + vertexCover.toString());
		} else
			System.out.println("No brute force cover that satisfies k = " + kValue + " has been found.");
		long endTime = System.currentTimeMillis();
		long duration = endTime-startTime;
		System.out.println("Time cost for Brute force: " + duration + "ms");
		System.out.println("************************************************************");
		possibleCovers.clear();
	}

	protected void optimizedBruteForce() {
		long startTime = System.currentTimeMillis();
		ArrayList<Vertex> v = new ArrayList<>();
		for (int i = 0; i < this.kValue; i++) {
			v.add(new Vertex(i));
		}
		boolean[] visit = new boolean[graph.getVertices().size()];
		for (int i = 0; i < graph.getVertices().size(); i++) {
			visit[i] = false;
		}
		this.minVertices = kValue + 1;
		vertexCover = new ArrayList<Vertex>();
		this.optimizedBruteForce(graph.getVertices(), this.kValue, v, -1, visit);
		if (!this.vertexCover.isEmpty()) {
			System.out.println("**********************OPTIMIZED BRUTE FORCE*********************");
			System.out.println("An optimized brute force cover has been discovered, meeting the condition k = " + kValue);
			System.out.println("The vertices of the smallest cover are: " + this.vertexCover.toString());
		} else
			System.out.println("No optimized brute force cover that satisfies k = " + kValue + " has been found.");
		long endTime = System.currentTimeMillis();
		long duration = endTime-startTime;
		System.out.println("Optimized brute force Duration: " + duration + "ms");
	}

	protected void optimizedBruteForce(ArrayList<Vertex> vertices, int k, ArrayList<Vertex> current, int currentTot,
									   boolean[] visit) {
		ArrayList<Edge> edges = graph.getEdges();
		if (currentTot < k - 1) {
			if (currentTot < minVertices) {
				for (int j = 0; j < current.size(); j++) {
					this.removeEdges(current.get(j), edges);
					if (edges.isEmpty()) {
						if ((j + 1) < this.minVertices) {
							this.minVertices = (j + 1);
							vertexCover.clear();
							for (int i = 0; i <= j; i++) {
								vertexCover.add(current.get(i));
							}
						}
					}
				}
			}
			edges = graph.getEdges();
		} else if (currentTot == k - 1 && currentTot < minVertices) {
			for (int j = 0; j < current.size(); j++) {
				this.removeEdges(current.get(j), edges);
				if (edges.isEmpty()) {
					if ((j + 1) < this.minVertices) {
						this.minVertices = (j + 1);
						vertexCover.clear();
						for (int i = 0; i <= j; i++) {
							vertexCover.add(current.get(i));
						}
					}
				}
			}
			edges = graph.getEdges();
			return;
		} else {
			return;
		}
		for (int i = 0; i < vertices.size(); i++) {
			if (!visit[i]) {
				current.set(++currentTot, vertices.get(i));
				visit[i] = true;
				optimizedBruteForce(vertices, k, current, currentTot, visit);
				visit[i] = false;
				currentTot--;
			}
		}


	}

//

	protected void approxCover() {
		long startTime = System.currentTimeMillis();
		ArrayList<Edge> edges = graph.getEdges();
		vertexCover = new ArrayList<Vertex>();
		while (!edges.isEmpty()) {
			Random random = new Random();
			int edge = random.nextInt(edges.size());
			Edge e = edges.get(edge);
			vertexCover.add(e.getVertex1());
			vertexCover.add(e.getVertex2());
			this.removeEdges(e.getVertex1(), edges);
			this.removeEdges(e.getVertex2(), edges);
		}
		long endTime = System.currentTimeMillis();
		long duration = endTime-startTime;
		System.out.println("**********************APPROX COVER*********************");
		System.out.println("An approximation cover for the graph has been found:");
		System.out.println("The vertices of the approximation cover are: " + vertexCover.toString());
		System.out.println("Approx Duration: " + duration + "ms");
	}

	protected void greedyCover() {
		long startTime = System.currentTimeMillis();
		ArrayList<Edge> edges = graph.getEdges();
		ArrayList<Vertex> vertices = graph.getVertices();
		vertexCover = new ArrayList<Vertex>();
		while (!edges.isEmpty()) {
			Vertex vertex = getMaxDegree(vertices, edges);
			vertexCover.add(vertex);
			vertices.remove(vertex);
			this.removeEdges(vertex, edges);
		}
		long endTime = System.currentTimeMillis();
		long duration = endTime-startTime;
		System.out.println("*****************GREEDY COVER*****************");
		System.out.println("A greedy cover for the graph has been found:");
		System.out.println("The vertices of the greedy cover are: " + vertexCover.toString());
		System.out.println("Greedy Duration: " + duration + "ms");
		System.out.println("************************************************************");
	}
}
