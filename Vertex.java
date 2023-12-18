public class Vertex {
	private int vertex;

	public Vertex(int vertex) {
		this.vertex = vertex;
	}

	public Vertex(int... vertices) {
		for (int vertex : vertices) {
			new Vertex(vertex);
		}
	}

	protected int getValue() {
		return vertex;
	}

	public String toString() {
		return Integer.toString(vertex);
	}
}
