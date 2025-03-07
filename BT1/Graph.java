package BT1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Graph<T> {

	protected Map<T, List<T>> adjList;

	public Graph() {
		adjList = new HashMap<>();
	}

	public void addVertex(T vertex) {
		adjList.put(vertex, new ArrayList<>());

	}

	public void printadjList() {
		for (Entry<T, List<T>> entry : adjList.entrySet()) {
			System.out.println(entry.getKey() + ":\t" + entry.getValue());
		}
	}

	public abstract void addEdge(T source, T destination);

	public abstract int NumofEdges();
}
