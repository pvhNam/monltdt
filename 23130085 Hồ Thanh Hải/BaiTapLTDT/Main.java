package BaiTapLTDT;



public class Main {
	public static void main(String[] args) {
		
		// Câu 1: Lấy ma trận từ file.txt
		Graph graph1 = Graph.loadGraph("test1.txt");
		Graph graph2 = Graph.loadGraph("test2.txt");
	Graph graph3 = Graph.loadGraph("test3.txt");
	Graph graph4= Graph.loadGraph("test4.txt");	
//		// Câu 2. In ma trận đồ thị
	graph1.printMatrixGraph();
		graph2.printMatrixGraph();
		graph3.printMatrixGraph();
		graph4.printMatrixGraph();
		

//		
////		// Câu 16. Phương thức tìm đường đi giữa 2 đỉnh từ s tới t = DFS hay BFS
//		System.out.println("Câu 16. Phương thức tìm đường đi giữa 2 đỉnh từ s tới t = DFS hay BFS");
//	graph1.findPathTwoVexs(1, 3);
//	graph2.findPathTwoVexs(3, 1);
//		graph3.findPathTwoVexs(1, 3);
//		graph4.findPathTwoVexs(3, 1);
//		System.out.println();
////		
//		// Câu 17. Phương thức kiểm tra đồ thị lưỡng phân hay không
//		System.out.println("Câu 17. Phương thức kiểm tra đồ thị lưỡng phân hay không");
//		System.out.println(graph3.checkBipartiteGraph());
//		System.out.println();
//		System.out.println(graph4.checkBipartiteGraph());
//		System.out.println();
//		System.out.println(graph1.checkBipartiteGraph());
//		System.out.println();
//		System.out.println(graph2.checkBipartiteGraph());
//		System.out.println();
		// câu 19.
		System.out.println(graph3.isEulerGraph());
		// câu 18
		System.out.println(graph3.isHalfEulerGraph());
		System.out.println("Chu trình ueler");
		graph4.findEulerCycle();
	}
}

