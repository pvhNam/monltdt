package BT2;

public abstract class Graph {
 protected int[][] adjMatrix;
  public Graph(int n) {
	  adjMatrix = new int [n][n];
  }
  protected abstract void addEdge(int source, int destination);
  protected abstract void removeEdge(int source,  int destination);
  protected abstract void printEdges();
  protected abstract int numOfEdge();
  public void printMatrix() {
	  for (int i = 0; i < adjMatrix.length; i++) {
		for (int j = 0; j < adjMatrix.length; j++) {
			System.out.print("\t"+adjMatrix[i][j]);
		}
		System.out.println();
	}
  }
  
}
