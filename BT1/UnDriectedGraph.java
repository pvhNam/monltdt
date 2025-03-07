package BT1;

public class UnDriectedGraph<T> extends Graph<T> {

	@Override
	public void addEdge(T source, T destination) {
		// TODO Auto-generated method stub
		if (!adjList.containsKey(source)) 
			this.addVertex(source);
		
		if (!adjList.containsKey(destination)) 
			this.addVertex(destination);
			adjList.get(source).add(destination);
			if (!source.equals(destination))
				
			adjList.get(destination).add(source);
		}
	

	public int degree(T vertex) {
		int sum = 0;
		sum = adjList.get(vertex).size();
		sum += adjList.get(vertex).stream().filter(v -> v.equals(vertex)).count();
		return sum;

	}


	@Override
	public int NumofEdges() {
		// TODO Auto-generated method stub
		int sum = 0;
		for( T v:adjList.keySet())
			sum+=degree(v);
		return 0;
	}
}
