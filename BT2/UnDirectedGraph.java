package BT2;

public class UnDirectedGraph extends Graph {

	public UnDirectedGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addEdge(int source, int destination) {
		// TODO Auto-generated method stub
		adjMatrix[source][destination]++;
		if (source != destination)
			adjMatrix[destination][source]++;

	}

	@Override
	protected void removeEdge(int source, int destination) {
		// TODO Auto-generated method stub
		if (adjMatrix[source][destination] != 0)
			adjMatrix[source][destination]--;
	}

	public int degree(int v) {
		int sum = 0;
		for (int i = 0; i < adjMatrix.length; i++) {
			sum += adjMatrix[v][i];
			if (i == v)
				sum += 2 * adjMatrix[v][i];
		}
		return sum;
	}
	

	protected void printEdges() {
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				if (adjMatrix[i][j] > 0)
					System.out.println("(" + i + "," + j + "), so luong:");
			}
		}
	}

	@Override
	protected int numOfEdge() {
		// TODO Auto-generated method stub
		int sumOfDegrees = 0;
		for(int i = 0; i< adjMatrix.length; i++) {
			sumOfDegrees += degree(i);
			
		}
		return sumOfDegrees/2;
	}
}
