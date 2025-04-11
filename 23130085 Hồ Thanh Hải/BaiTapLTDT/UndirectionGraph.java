package BaiTapLTDT;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class UndirectionGraph extends Graph {
	
	public UndirectionGraph(int numVex, int[][] matrix) {
		super(numVex, matrix);
	}

	// CÃ¢u 5. PhÆ°Æ¡ng thá»©c thÃªm má»™t cáº¡nh vÃ o Ä‘á»“ thá»‹
	@Override
	public void addEdge(int[][] matrix, int v1, int v2) {
		if (v1 < 0 || v2 < 0 || v1 >= matrix.length || v2 >= matrix.length) {
			System.out.println("Lá»—i: Ä�á»‰nh khÃ´ng há»£p lá»‡.");
			return;
		}
		matrix[v1][v2] = 1;
		matrix[v2][v1] = 1; // Ä�á»‘i xá»©ng cho Ä‘á»“ thá»‹ vÃ´ hÆ°á»›ng
	}

	// CÃ¢u 6. PhÆ°Æ¡ng thá»©c xÃ³a má»™t cáº¡nh
	@Override
	public void removeEdge(int[][] matrix, int v1, int v2) {
		if (v1 < 0 || v2 < 0 || v1 >= matrix.length || v2 >= matrix.length) {
			System.out.println("Lá»—i: Ä�á»‰nh khÃ´ng há»£p lá»‡.");
			return;
		}
		if (matrix[v1][v2] == 0) {
			System.out.println("Cáº¡nh (" + v1 + ", " + v2 + ") khÃ´ng tá»“n táº¡i.");
			return;
		}
		matrix[v1][v2] = 0;
		matrix[v2][v1] = 0; // XÃ³a Ä‘á»‘i xá»©ng cho Ä‘á»“ thá»‹ vÃ´ hÆ°á»›ng
		System.out.println("Ä�Ã£ xÃ³a cáº¡nh (" + v1 + ", " + v2 + ")");
	}

	// CÃ¢u 7. PhÆ°Æ¡ng thá»©c tÃ­nh báº­c cá»§a má»—i Ä‘á»‰nh
	@Override
	public int deg(int v) {
		if (v < 0 || v >= matrix.length) {
			System.out.println("Lá»—i: Ä�á»‰nh khÃ´ng há»£p lá»‡.");
			return -1;
		}
		int degree = 0;
		for (int i = 0; i < matrix.length; i++) {
			degree += matrix[v][i]; // Tá»•ng sá»‘ cáº¡nh liÃªn káº¿t vá»›i Ä‘á»‰nh v
		}
		return degree;
	}

	// CÃ¢u 8. PhÆ°Æ¡ng thá»©c tÃ­nh tá»•ng báº­c cá»§a Ä‘á»“ thá»‹
	public int sumDeg() {
		int sum = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				sum += matrix[i][j]; // Má»—i cáº¡nh Ä‘Æ°á»£c tÃ­nh hai láº§n
			}
		}
		return sum; // Tráº£ vá»� tá»•ng báº­c
	}

	// CÃ¢u 10. PhÆ°Æ¡ng thá»©c tÃ­nh tá»•ng sá»‘ cáº¡nh cá»§a Ä‘á»“ thá»‹
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
		return count / 2;
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

	// CÃ¢u 19. PhÆ°Æ¡ng thá»©c kiá»ƒm tra Ä‘á»“ thá»‹ G cÃ³ Ä‘Æ°á»�ng Ä‘i Euler hay khÃ´ng?
	@Override
	public boolean isHalfEulerGraph() {
		// Kiá»ƒm tra liÃªn thÃ´ng
		if (!isConnected()) {
			return false;
		}

		// Ä�áº¿m sá»‘ Ä‘á»‰nh cÃ³ báº­c láº»
		int oddCount = 0;
		for (int i = 0; i < numVex; i++) {
			int degree = 0;
			for (int j = 0; j < numVex; j++) {
				if (matrix[i][j] != 0) {
					degree++;
				}
			}
			if (degree % 2 != 0) {
				oddCount++;
			}
		}

		// Chá»‰ cÃ³ Ä‘Æ°á»�ng Ä‘i Euler náº¿u cÃ³ 0 hoáº·c 2 Ä‘á»‰nh báº­c láº»
		return (oddCount == 0 || oddCount == 2);
	}

	// CÃ¢u 18. PhÆ°Æ¡ng thá»©c kiá»ƒm tra Ä‘á»“ thá»‹ G cÃ³ chu trÃ¬nh Euler hay khÃ´ng?
	@Override
	public boolean isEulerGraph() {
		// Kiá»ƒm tra liÃªn thÃ´ng
		if (!isConnected()) {
			return false;
		}

		// Kiá»ƒm tra táº¥t cáº£ cÃ¡c Ä‘á»‰nh cÃ³ báº­c cháºµn
		for (int i = 0; i < numVex; i++) {
			int degree = 0;
			for (int j = 0; j < numVex; j++) {
				if (matrix[i][j] != 0) {
					degree++;
				}
			}
			if (degree % 2 != 0) {
				return false;
			}
		}

		// Náº¿u táº¥t cáº£ cÃ¡c Ä‘á»‰nh cÃ³ báº­c cháºµn -> CÃ³ chu trÃ¬nh Euler
		return true;
	}
	// cÃ¢u 20
	
	@Override

	public void findEulerCycle() {
		if (!isConnected()) {
	        System.out.println("KhÃ´ng tá»“n táº¡i chu trÃ¬nh Euler vÃ¬ Ä‘á»“ thá»‹ khÃ´ng liÃªn thÃ´ng.");
	        return;
	    }

	    // Kiá»ƒm tra táº¥t cáº£ cÃ¡c Ä‘á»‰nh cÃ³ báº­c cháºµn
	    for (int i = 0; i < numVex; i++) {
	        if (deg(i) % 2 != 0) {
	            System.out.println("KhÃ´ng tá»“n táº¡i chu trÃ¬nh Euler vÃ¬ cÃ³ Ä‘á»‰nh báº­c láº».");
	            return;
	        }
	    }

	    // TÃ¬m chu trÃ¬nh Euler báº±ng thuáº­t toÃ¡n Fleury
	    Stack<Integer> stack = new Stack<>();
	    List<Integer> cycle = new ArrayList<>();
	    int[][] tempMatrix = new int[numVex][numVex];

	    // Copy ma tráº­n Ä‘á»ƒ khÃ´ng áº£nh hÆ°á»Ÿng dá»¯ liá»‡u gá»‘c
	    for (int i = 0; i < numVex; i++) {
	        for (int j = 0; j < numVex; j++) {
	            tempMatrix[i][j] = matrix[i][j];
	        }
	    }

	    stack.push(0); // Báº¯t Ä‘áº§u tá»« Ä‘á»‰nh 0

	    while (!stack.isEmpty()) {
	        int v = stack.peek();
	        boolean hasEdge = false;

	        for (int u = 0; u < numVex; u++) {
	            if (tempMatrix[v][u] > 0) {
	                stack.push(u);
	                tempMatrix[v][u]--;
	                tempMatrix[u][v]--; // XÃ³a cáº¡nh Ä‘á»‘i xá»©ng
	                hasEdge = true;
	                break;
	            }
	        }

	        if (!hasEdge) {
	            cycle.add(stack.pop());
	        }
	    }

	    System.out.println("Chu trÃ¬nh Euler: " + cycle);
	    
	}
	// cÃ¢u 21 Ä‘Æ°á»�ng Ä‘i

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