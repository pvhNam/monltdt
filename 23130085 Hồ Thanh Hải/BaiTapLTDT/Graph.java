package BaiTapLTDT;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public abstract class Graph {
	protected int numVex;
	protected int[][] matrix;

	public Graph(int numVex, int[][] matrix) {
		this.numVex = numVex;
		this.matrix = matrix;
	}

	public Graph() {
	}

	// Tá»± Ä‘á»™ng táº¡o Ä‘Ãºng loáº¡i Ä‘á»“ thá»‹
	public static Graph createGraph(int numVex, int[][] matrix) {
		if (checkUnGraph(matrix)) {
			System.out.println("Ä�á»“ thá»‹ vÃ´ hÆ°á»›ng.");
			System.out.println();
			return new UndirectionGraph(numVex, matrix);
		} else {
			System.out.println("Ä�á»“ thá»‹ cÃ³ hÆ°á»›ng.");
			System.out.println();
			return new DirectionGraph(numVex, matrix);
		}
	}

	// CÃ¢u 1: Láº¥y ma tráº­n tá»« file.txt
	public static Graph loadGraph(String pathFile) {
		pathFile = GetPath.getPath(pathFile);

		if (pathFile == null) {
			System.out.println("Lá»—i, file khÃ´ng tá»“n táº¡i hoáº·c khÃ´ng tÃ¬m tháº¥y Ä‘Æ°á»�ng dáº«n");
			System.exit(0);
		}

		try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
			String line = br.readLine();
			if (line == null || line.trim().isEmpty()) {
				System.out.println("Sá»‘ Ä‘á»‰nh khÃ´ng há»£p lá»‡.");
				return null;
			}
			int numVex = Integer.parseInt(line.trim());

			int[][] matrix = new int[numVex][numVex];

			for (int i = 0; i < numVex; i++) {
				line = br.readLine();
				if (line == null || line.trim().isEmpty()) {
					System.out.println("Dá»¯ liá»‡u khÃ´ng Ä‘áº§y Ä‘á»§ trong file.");
					return null;
				}

				String[] values = line.trim().split("\\s+");
				if (values.length != numVex) {
					System.out.println("Sá»‘ cá»™t trong ma tráº­n khÃ´ng Ä‘Ãºng táº¡i dÃ²ng " + (i + 1));
					return null;
				}
				for (int j = 0; j < numVex; j++) {
					matrix[i][j] = Integer.parseInt(values[j].trim());
				}
			}
			System.out.print("Táº£i file thÃ nh cÃ´ng. ");

			// Tá»± Ä‘á»™ng táº¡o Ä‘á»‘i tÆ°á»£ng phÃ¹ há»£p
			return createGraph(numVex, matrix);
		} catch (IOException e) {
			System.out.println("Lá»—i khi Ä‘á»�c file: " + e.getMessage());
			return null;
		} catch (NumberFormatException e) {
			System.out.println("Lá»—i Ä‘á»‹nh dáº¡ng dá»¯ liá»‡u trong file: " + e.getMessage());
			return null;
		}
	}

	// CÃ¢u 2. In ma tráº­n Ä‘á»“ thá»‹
	public void printMatrixGraph() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// CÃ¢u 3. Kiá»ƒm tra ma tráº­n Ä‘á»“ thá»‹ cÃ³ há»£p lá»‡
	public boolean checkValid() {
		if (matrix == null)
			return false;

		for (int[] column : matrix) {
			if (matrix.length != column.length)
				return false;
		}

		return true;
	}

	// CÃ¢u 4. Kiá»ƒm tra cÃ³ lÃ  Ä‘á»“ thá»‹ vÃ´ hÆ°á»›ng
	public static boolean checkUnGraph(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (matrix[i][j] != matrix[j][i]) {
					return false;
				}
			}
		}
		return true;
	}

	// CÃ¢u 5. PhÆ°Æ¡ng thá»©c thÃªm má»™t cáº¡nh
	public abstract void addEdge(int[][] matrix, int v1, int v2);

	// CÃ¢u 6. PhÆ°Æ¡ng thá»©c xÃ³a má»™t cáº¡nh
	public abstract void removeEdge(int[][] matrix, int v1, int v2);

	// CÃ¢u 7. PhÆ°Æ¡ng thá»©c tÃ­nh báº­c cá»§a má»—i Ä‘á»‰nh
	public abstract int deg(int v);

	// CÃ¢u 8. PhÆ°Æ¡ng thá»©c tÃ­nh tá»•ng báº­c cá»§a Ä‘á»“ thá»‹
	public abstract int sumDeg();

	// CÃ¢u 9. PhÆ°Æ¡ng thá»©c tÃ­nh tá»•ng sá»‘ Ä‘á»‰nh cá»§a Ä‘á»“ thá»‹
	public int numVertexs() {
		return numVex;
	}

	// CÃ¢u 10. PhÆ°Æ¡ng thá»©c tÃ­nh tá»•ng sá»‘ cáº¡nh cá»§a Ä‘á»“ thá»‹
	public abstract int numEdges();

	// CÃ¢u 11. PhÆ°Æ¡ng thá»©c kiá»ƒm tra Ä‘á»“ thá»‹ cÃ³ liÃªn thÃ´ng
	// Ä�á»“ thá»‹ liÃªn thÃ´ng náº¿u cÃ³ Ä‘Æ°á»�ng Ä‘i giá»¯a 2 Ä‘á»‰nh báº¥t ká»³

	public boolean checkConnect() {
		boolean[] visited = new boolean[numVex];
		Arrays.fill(visited, false);

		// Báº¯t Ä‘áº§u duyá»‡t tá»« Ä‘á»‰nh 0 (hoáº·c má»™t Ä‘á»‰nh báº¥t ká»³)
		System.out.println("Báº¯t Ä‘áº§u kiá»ƒm tra liÃªn thÃ´ng tá»« Ä‘á»‰nh 0");

		DFS(0, visited);

		// Kiá»ƒm tra náº¿u cÃ³ Ä‘á»‰nh nÃ o chÆ°a thÄƒm
		for (int i = 0; i < numVex; i++) {
			if (!visited[i]) {
				System.out.println("Ä�á»‰nh " + i + " chÆ°a Ä‘Æ°á»£c thÄƒm. Ä�á»“ thá»‹ khÃ´ng liÃªn thÃ´ng.");
				return false;
			}
		}

		System.out.println("Táº¥t cáº£ cÃ¡c Ä‘á»‰nh Ä‘Ã£ Ä‘Æ°á»£c thÄƒm. Ä�á»“ thá»‹ liÃªn thÃ´ng.");
		return true;
	}

	private void DFS(int start, boolean[] visited) {
		Stack<Integer> stack = new Stack<>();
		stack.push(start);

		while (!stack.isEmpty()) {
			int v = stack.pop();

			if (!visited[v]) {
				visited[v] = true;
				System.out.println("ThÄƒm Ä‘á»‰nh " + v);

				// Duyá»‡t táº¥t cáº£ cÃ¡c Ä‘á»‰nh ká»�
				for (int i = 0; i < numVex; i++) {
					if (matrix[v][i] == 1 && !visited[i]) {
						System.out.println(" -> Ä�áº©y Ä‘á»‰nh " + i + " vÃ o ngÄƒn xáº¿p Ä‘á»ƒ thÄƒm tiáº¿p theo.");
						stack.push(i);
					}
				}
			}
		}
	}

	// CÃ¢u 12. PhÆ°Æ¡ng thá»©c xÃ©t tÃ­nh liÃªn thÃ´ng cá»§a Ä‘á»“ thá»‹
	public abstract void xetTinhLienThong();

	public abstract void diTimCacDinhLienThong(boolean[] visited, int start, List<Integer> component);

	// CÃ¢u 13. PhÆ°Æ¡ng thá»©c dÃ¹ng giáº£i thuáº­t BFS duyá»‡t Ä‘á»“ thá»‹
	public void BFSGraph() {
		boolean[] visited = new boolean[numVex];
		System.out.println("Báº¯t Ä‘áº§u duyá»‡t Ä‘á»“ thá»‹ báº±ng BFS:");

		for (int i = 0; i < numVex; i++) {
			if (!visited[i]) {
				System.out.println("Báº¯t Ä‘áº§u tá»« Ä‘á»‰nh " + i);
				BFSGraph(i, visited);
			}
		}
	}

	// BFS báº¯t Ä‘áº§u tá»« má»™t Ä‘á»‰nh cho trÆ°á»›c
	public void BFSGraph(int startVex) {
		boolean[] visited = new boolean[numVex];
		BFSGraph(startVex, visited);
	}

	// HÃ m thá»±c hiá»‡n BFS tá»« má»™t Ä‘á»‰nh vá»›i tráº¡ng thÃ¡i Ä‘Ã£ thÄƒm
	private void BFSGraph(int startVex, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startVex);
		visited[startVex] = true;
		
		System.out.println("ThÄƒm Ä‘á»‰nh: "+startVex);
		

		while (!queue.isEmpty()) {
			int v = queue.poll();

			for (int i = 0; i < numVex; i++) {
				if (matrix[v][i] == 1 && !visited[i]) {
					queue.add(i);
					visited[i] = true;
					System.out.println("ThÄƒm Ä‘á»‰nh " + i);
				}
			}
		}
	}

	// CÃ¢u 14. PhÆ°Æ¡ng thá»©c dÃ¹ng giáº£i thuáº­t DFS duyá»‡t Ä‘á»“ thá»‹
	public void DFSGraph() {
		boolean[] visited = new boolean[numVex + 1]; // Máº£ng Ä‘Ã¡nh dáº¥u Ä‘Ã£ thÄƒm
		System.out.println("Báº¯t Ä‘áº§u DFS toÃ n bá»™ Ä‘á»“ thá»‹:");

		for (int i = 1; i <= numVex; i++) {
			if (!visited[i]) {
				System.out.println("Báº¯t Ä‘áº§u DFS tá»« Ä‘á»‰nh " + i);
				DFSRecursive(i, visited);
			}
		}
	}

	// HÃ m DFS Ä‘á»‡ quy
	private void DFSRecursive(int v, boolean[] visited) {
		visited[v] = true;
		System.out.println("ThÄƒm Ä‘á»‰nh " + v);

		for (int i = 1; i <= numVex; i++) {
			if (matrix[v - 1][i - 1] == 1 && !visited[i]) {
				System.out.println("Ä�i tá»« " + v + " Ä‘áº¿n " + i);
				DFSRecursive(i, visited);
			}
		}
	}

	// HÃ m thá»±c hiá»‡n DFS tá»« má»™t Ä‘á»‰nh
	public void DFSGraph(int startVex) {
		boolean[] visited = new boolean[numVex + 1];
		System.out.println("Báº¯t Ä‘áº§u DFS tá»« Ä‘á»‰nh " + startVex);
		DFSRecursive(startVex, visited);
	}

	// CÃ¢u 15. Kiá»ƒm tra tÃ­nh liÃªn thÃ´ng báº±ng giáº£i thuáº­t BFS hoáº·c DFS
	public boolean isConnected() {
		boolean[] visited = new boolean[numVex];

		// Báº¯t Ä‘áº§u duyá»‡t tá»« Ä‘á»‰nh Ä‘áº§u tiÃªn (thÆ°á»�ng lÃ  0)
		BFS(0, visited);

		// Kiá»ƒm tra náº¿u cÃ²n Ä‘á»‰nh chÆ°a thÄƒm thÃ¬ Ä‘á»“ thá»‹ khÃ´ng liÃªn thÃ´ng
		for (boolean v : visited) {
			if (!v) {
				return false;
			}
		}

		return true;
	}

	// Thuáº­t toÃ¡n BFS Ä‘á»ƒ duyá»‡t toÃ n bá»™ Ä‘á»“ thá»‹
	private void BFS(int startVex, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startVex);
		visited[startVex] = true;
		System.out.println("Báº¯t Ä‘áº§u tá»« Ä‘á»‰nh " + startVex);

		while (!queue.isEmpty()) {
			int v = queue.poll();
			System.out.println("Ä�ang thÄƒm Ä‘á»‰nh " + v);

			for (int i = 0; i < numVex; i++) {
				if (matrix[v][i] == 1 && !visited[i]) {
					queue.add(i);
					visited[i] = true;
					System.out.println("ThÃªm Ä‘á»‰nh " + i + " vÃ o hÃ ng Ä‘á»£i");
				}
			}
		}
	}

	// CÃ¢u 16. PhÆ°Æ¡ng thá»©c tÃ¬m Ä‘Æ°á»�ng Ä‘i giá»¯a 2 Ä‘á»‰nh tá»« s tá»›i t báº±ng thuáº­t toÃ¡n DFS
	// hay BFS
	public void findPathTwoVexs(int s, int t) {
		boolean[] visited = new boolean[numVex];
		int[] parent = new int[numVex]; // Máº£ng lÆ°u cha cá»§a tá»«ng Ä‘á»‰nh
		Arrays.fill(parent, -1); // Khá»Ÿi táº¡o táº¥t cáº£ cha lÃ  -1

		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		visited[s] = true;

		System.out.println("Báº¯t Ä‘áº§u tÃ¬m Ä‘Æ°á»�ng tá»« " + s + " Ä‘áº¿n " + t);

		while (!queue.isEmpty()) {
			int v = queue.poll();
			System.out.println("Ä�ang xÃ©t Ä‘á»‰nh " + v);

			// Náº¿u tÃ¬m tháº¥y Ä‘á»‰nh t thÃ¬ dá»«ng láº¡i
			if (v == t) {
				break;
			}

			// Duyá»‡t cÃ¡c Ä‘á»‰nh ká»�
			for (int i = 0; i < numVex; i++) {
				if (matrix[v][i] == 1 && !visited[i]) {
					queue.add(i);
					visited[i] = true;
					parent[i] = v; // LÆ°u cha cá»§a i lÃ  v
					System.out.println("ThÃªm Ä‘á»‰nh " + i + " vÃ o hÃ ng Ä‘á»£i, cha lÃ  " + v);
				}
			}
		}

		// Truy váº¿t láº¡i Ä‘Æ°á»�ng Ä‘i tá»« t vá»� s
		if (!visited[t]) {
			System.out.println("KhÃ´ng cÃ³ Ä‘Æ°á»�ng Ä‘i tá»« " + s + " Ä‘áº¿n " + t);
			return;
		}

		List<Integer> path = new ArrayList<>();
		for (int v = t; v != -1; v = parent[v]) {
			path.add(v);
		}
		Collections.reverse(path); // Ä�áº£o ngÆ°á»£c Ä‘Æ°á»�ng Ä‘i

		// In Ä‘Æ°á»�ng Ä‘i tÃ¬m Ä‘Æ°á»£c
		System.out.print("Ä�Æ°á»�ng Ä‘i tá»« " + s + " Ä‘áº¿n " + t + ": ");
		for (int i = 0; i < path.size(); i++) {
			if (i != 0)
				System.out.print(" -> ");
			System.out.print(path.get(i));
		}
		System.out.println();
		System.out.println();
	}

	// CÃ¢u 17. PhÆ°Æ¡ng thá»©c kiá»ƒm tra Ä‘á»“ thá»‹ lÆ°á»¡ng phÃ¢n hay khÃ´ng
	public boolean checkBipartiteGraph() {
		int[] colors = new int[numVex]; // Máº£ng lÆ°u mÃ u cá»§a tá»«ng Ä‘á»‰nh (0: chÆ°a tÃ´, 1 vÃ  2: hai mÃ u khÃ¡c nhau)
		Arrays.fill(colors, 0); // Ban Ä‘áº§u chÆ°a cÃ³ Ä‘á»‰nh nÃ o Ä‘Æ°á»£c tÃ´ mÃ u

		// Kiá»ƒm tra tá»«ng thÃ nh pháº§n liÃªn thÃ´ng (trÃ¡nh bá»� sÃ³t Ä‘á»“ thá»‹ khÃ´ng liÃªn thÃ´ng)
		for (int start = 0; start < numVex; start++) {
			if (colors[start] == 0) { // Náº¿u Ä‘á»‰nh chÆ°a Ä‘Æ°á»£c tÃ´ mÃ u, thá»±c hiá»‡n BFS tá»« Ä‘Ã³
				if (!BFSCheckBipartite(start, colors)) {
					return false; // Náº¿u phÃ¡t hiá»‡n Ä‘á»“ thá»‹ khÃ´ng lÆ°á»¡ng phÃ¢n thÃ¬ tráº£ vá»� false ngay
				}
			}
		}
		return true; // Náº¿u khÃ´ng cÃ³ lá»—i nÃ o, Ä‘á»“ thá»‹ lÃ  lÆ°á»¡ng phÃ¢n
	}

	// ðŸ”¹ BFS kiá»ƒm tra lÆ°á»¡ng phÃ¢n vÃ  tÃ´ mÃ u
	private boolean BFSCheckBipartite(int start, int[] colors) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		colors[start] = 1; // TÃ´ mÃ u Ä‘á»‰nh Ä‘áº§u tiÃªn lÃ  1
		System.out.println("Báº¯t Ä‘áº§u kiá»ƒm tra tá»« Ä‘á»‰nh " + start);

		while (!queue.isEmpty()) {
			int u = queue.poll();
			System.out.println("Ä�ang xÃ©t Ä‘á»‰nh " + u + " vá»›i mÃ u " + colors[u]);

			for (int v = 0; v < numVex; v++) {
				if (matrix[u][v] == 1) { // Náº¿u cÃ³ cáº¡nh ná»‘i u â†’ v
					if (colors[v] == 0) { // Náº¿u Ä‘á»‰nh v chÆ°a Ä‘Æ°á»£c tÃ´ mÃ u
						colors[v] = (colors[u] == 1) ? 2 : 1; // TÃ´ mÃ u khÃ¡c vá»›i u
						System.out.println("TÃ´ mÃ u Ä‘á»‰nh " + v + " lÃ  " + colors[v]);
						queue.add(v);
					} else if (colors[v] == colors[u]) { // Náº¿u hai Ä‘á»‰nh ká»� cÃ³ cÃ¹ng mÃ u
						System.out.println("Lá»—i: Ä�á»‰nh " + u + " vÃ  Ä‘á»‰nh " + v + " cÃ³ cÃ¹ng mÃ u " + colors[u]);
						return false;
					}
				}
			}
		}
		return true;
	}

	// CÃ¢u 19. PhÆ°Æ¡ng thá»©c kiá»ƒm tra Ä‘á»“ thá»‹ G cÃ³ Ä‘Æ°á»�ng Ä‘i Euler hay khÃ´ng?
	public abstract boolean isHalfEulerGraph();

	// CÃ¢u 18. PhÆ°Æ¡ng thá»©c kiá»ƒm tra Ä‘á»“ thá»‹ G cÃ³ chu trÃ¬nh Euler hay khÃ´ng?
	public abstract boolean isEulerGraph();
	// CÃ¢u 20. Viáº¿t phÆ°Æ¡ng thá»©c tÃ¬m chu trÃ¬nh Ueler Ä‘á»“ thi G
	public abstract void findEulerCycle();
	public  abstract  boolean hasCycle(int [][] a);
	public  abstract  boolean hasCycle(int u, int v ,int [][] a);
	public  abstract  int dfsMST(int v);
	public  abstract  int bfsMST(int v);
	
}
