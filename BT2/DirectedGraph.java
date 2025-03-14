package BT2;

public class DirectedGraph extends Graph {

	public DirectedGraph(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addEdge(int source, int destination) {
		// TODO Auto-generated method stub
		adjMatrix[source][destination]++;
		
	}

	@Override
	protected void removeEdge(int source, int destination) {
		// TODO Auto-generated method stub
		
	}
	protected void printEdges() {
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				if(adjMatrix[i][j]>0)
					System.out.println("("+i+","+j+"), so luong:");
			}
		}
	}

	@Override
	protected int numOfEdge() {
		// TODO Auto-generated method stub
		return 0;
	}

}
