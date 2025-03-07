package BT1;

public class test {
	public static void main(String[] args) {
		Graph<String> G1 = new DriectedGraph<String>();
		G1.addEdge("a", "b");
		G1.addEdge("a", "c");
		G1.addEdge("b", "c");
		G1.addEdge("c", "b");
		G1.addEdge("c", "c");
		G1.printadjList();
		Graph<String> G2 = new UnDriectedGraph<String>();
		G2.addEdge("a", "b");
		G2.addEdge("a", "c");
		G2.addEdge("b", "c");
		G2.addEdge("c", "b");
		G2.addEdge("c", "c");
		G2.printadjList();
		System.out.println("bac cua dinh c trong G2 la:" + ((UnDriectedGraph<String>) G2).degree("c"));

	}
}
