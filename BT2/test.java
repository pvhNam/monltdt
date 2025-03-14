package BT2;


public class test {
	public static void main(String[] args) {
	 UnDirectedGraph G2 = new UnDirectedGraph(7);
		G2.addEdge(0,1);
		G2.addEdge(0,3 );
		G2.addEdge(1,2 );
		G2.addEdge(2,1 );
		G2.addEdge(2, 3);
		G2.addEdge(3, 4);
		G2.addEdge(3, 5);
		G2.addEdge(4, 6);
		G2.addEdge(5, 5);
		G2.addEdge(5, 6);

		System.out.println("bac cua dinh c trong G2 la:" );
		G2.printMatrix();
System.out.println("so canh"+((UnDirectedGraph)G2).degree(5));
	}

}
