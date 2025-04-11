package BaiTapLTDT;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DirectionGraph extends Graph {

	public DirectionGraph(int numVex, int[][] matrix) {
		super(numVex, matrix);
	}

	// CÃ¢u 5. PhÆ°Æ¡ng thá»©c thÃªm má»™t cáº¡nh vÃ o Ä‘á»“ thá»‹
	@Override
	public void addEdge(int[][] matrix, int v1, int v2) {
		if (v1 < 0 || v2 < 0 || v1 >= matrix.length || v2 >= matrix.length) {
			System.out.println("Lá»—i: Ä�á»‰nh khÃ´ng há»£p lá»‡.");
			return;
		}
		matrix[v1][v2] = 1; // Chá»‰ thÃªm theo má»™t chiá»�u
	}

	// CÃ¢u 6. PhÆ°Æ¡ng thá»©c xÃ³a má»™t cáº¡nh
	@Override
	public void removeEdge(int[][] matrix, int v1, int v2) {
		if (v1 < 0 || v2 < 0 || v1 >= matrix.length || v2 >= matrix.length) {
			System.out.println("Lá»—i: Ä�á»‰nh khÃ´ng há»£p lá»‡.");
			return;
		}
		if (matrix[v1][v2] == 0) {
			System.out.println("Cáº¡nh (" + v1 + " â†’ " + v2 + ") khÃ´ng tá»“n táº¡i.");
			return;
		}
		matrix[v1][v2] = 0; // Chá»‰ xÃ³a má»™t chiá»�u
		System.out.println("Ä�Ã£ xÃ³a cáº¡nh (" + v1 + " â†’ " + v2 + ")");
	}

	// CÃ¢u 7. PhÆ°Æ¡ng thá»©c tÃ­nh báº­c cá»§a má»—i Ä‘á»‰nh
	@Override
	public int deg(int v) {
		if (v < 0 || v >= matrix.length) {
			System.out.println("Lá»—i: Ä�á»‰nh khÃ´ng há»£p lá»‡.");
			return -1;
		}

		int inDegree = 0, outDegree = 0;

		for (int i = 0; i < matrix.length; i++) {
			outDegree += matrix[v][i]; // Báº­c ra: Tá»•ng hÃ ng `v`
			inDegree += matrix[i][v]; // Báº­c vÃ o: Tá»•ng cá»™t `v`
		}

		return inDegree + outDegree;
	}

	// CÃ¢u 8. PhÆ°Æ¡ng thá»©c tÃ­nh tá»•ng báº­c cá»§a Ä‘á»“ thá»‹
	public int sumDeg() {
		int sum = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				sum += Math.abs(matrix[i][j]); // Tá»•ng báº­c ra + báº­c vÃ o
			}
		}
		return sum; // Tá»•ng báº­c cá»§a táº¥t cáº£ cÃ¡c Ä‘á»‰nh = tá»•ng sá»‘ cáº¡nh
	}

	// CÃ¢u 10. PhÆ°Æ¡ng thá»©c tÃ­nh tá»•ng sá»‘ cáº¡nh cá»§a Ä‘á»“ thá»‹
	@Override
	public int numEdges() {
		if (matrix == null)
			return 0; // Kiá»ƒm tra ma tráº­n rá»—ng

		int count = 0;
		int n = matrix.length;

		// Duyá»‡t qua ma tráº­n Ä‘á»ƒ Ä‘áº¿m sá»‘ cáº¡nh
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] != 0) {
					count++;
				}
			}
		}
		return count;
	}

	// CÃ‚u 12. PhÆ°Æ¡ng thá»©c xÃ©t tÃ­nh liÃªn thÃ´ng cá»§a Ä‘á»“ thá»‹
	@Override
	public void xetTinhLienThong() {
		boolean[] visited = new boolean[numVex];
		Arrays.fill(visited, false);
		int count = 0;
		List<List<Integer>> components = new ArrayList<>();

		System.out.println("Báº¯t Ä‘áº§u xÃ©t cÃ¡c thÃ nh pháº§n liÃªn thÃ´ng:");

		for (int i = 0; i < numVex; i++) {
			if (!visited[i]) {
				count++;
				System.out.println("TÃ¬m thÃ nh pháº§n liÃªn thÃ´ng thá»© " + count + " báº¯t Ä‘áº§u tá»« Ä‘á»‰nh " + i);
				List<Integer> component = new ArrayList<>();
				diTimCacDinhLienThong(visited, i, component);
				components.add(component);
			}
		}

		System.out.println("Sá»‘ thÃ nh pháº§n liÃªn thÃ´ng: " + count);
		for (int i = 0; i < components.size(); i++) {
			System.out.println("ThÃ nh pháº§n liÃªn thÃ´ng " + (i + 1) + ": " + components.get(i));
		}

		if (count == 1) {
			System.out.println("Ä�á»“ thá»‹ liÃªn thÃ´ng.");
		} else {
			System.out.println("Ä�á»“ thá»‹ khÃ´ng liÃªn thÃ´ng.");
		}
	}

	// CÃ¢u 12, PhÆ°Æ¡ng thá»©c tÃ¬m cÃ¡c Ä‘á»‰nh liÃªn thÃ´ng
	@Override
	public void diTimCacDinhLienThong(boolean[] visited, int start, List<Integer> component) {
		Stack<Integer> stack = new Stack<>();
		stack.push(start);

		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (!visited[v]) {
				visited[v] = true;
				component.add(v);
				System.out.println("ThÄƒm Ä‘á»‰nh " + v);

				for (int i = 0; i < numVex; i++) {
					if (matrix[v][i] == 1 && !visited[i]) {
						System.out.println(" -> Ä�áº©y Ä‘á»‰nh " + i + " vÃ o ngÄƒn xáº¿p Ä‘á»ƒ thÄƒm tiáº¿p theo.");
						stack.push(i);
					}
				}
			}
		}
	}

	// CÃ¢u 19. PhÆ°Æ¡ng thá»©c kiá»ƒm tra Ä‘á»“ thá»‹ G cÃ³ Ä‘Æ°á»�ng Ä‘i Euler hay khÃ´ng?
	@Override
	public boolean isHalfEulerGraph() {
		// Kiá»ƒm tra liÃªn thÃ´ng máº¡nh hoáº·c yáº¿u
		if (!isConnected()) {
			return false;
		}

		int[] inDegree = new int[numVex];
		int[] outDegree = new int[numVex];

		// TÃ­nh báº­c vÃ o vÃ  báº­c ra
		for (int i = 0; i < numVex; i++) {
			for (int j = 0; j < numVex; j++) {
				if (matrix[i][j] != 0) {
					outDegree[i]++;
					inDegree[j]++;
				}
			}
		}

		int startCount = 0, endCount = 0;
		// Kiá»ƒm tra Ä‘iá»�u kiá»‡n Euler Ä‘Æ°á»�ng Ä‘i
		for (int i = 0; i < numVex; i++) {
			int diff = outDegree[i] - inDegree[i];
			if (diff == 1) {
				startCount++;
			} else if (diff == -1) {
				endCount++;
			} else if (diff != 0) {
				return false;
			}
		}

		// Pháº£i cÃ³ Ä‘Ãºng 1 Ä‘á»‰nh cÃ³ out-degree = in-degree + 1 vÃ  1 Ä‘á»‰nh cÃ³ in-degree =
		// out-degree + 1
		return (startCount == 1 && endCount == 1);
	}

	// CÃ¢u 18. PhÆ°Æ¡ng thá»©c kiá»ƒm tra Ä‘á»“ thá»‹ G cÃ³ chu trÃ¬nh Euler hay khÃ´ng?
	// HÃ m kiá»ƒm tra liÃªn thÃ´ng máº¡nh
	public boolean isStronglyConnected() {
		return false;
		
	}

	@Override
	public boolean isEulerGraph() {
		// TODO Auto-generated method stub
		return false;
	}
// cÃ¢u 20
	
	@Override
	public void findEulerCycle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasCycle(int[][] a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCycle(int u, int v, int[][] a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int dfsMST(int v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bfsMST(int v) {
		// TODO Auto-generated method stub
		return 0;
	}

}
