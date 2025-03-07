package BT1;

public class DriectedGraph<T> extends Graph<T> {

	@Override
	public void addEdge(T source, T destination) {
		// TODO Auto-generated method stub
		if (!adjList.containsKey(source)) 
			this.addVertex(source);
		
		if (!adjList.containsKey(destination)) 
			this.addVertex(destination);
			adjList.get(source).add(destination);
	}
	public int outDegree(T vertex) {
	
		return this.adjList.get(vertex).size();
	}
public int inDegree(T vertex) {
	int sum =0;
	for(T v:adjList.keySet()) {
		sum+= adjList.get(v).stream().filter(x->x.equals(vertex)).count();
				
	}
	return sum;
}
@Override
public int NumofEdges() {
	// TODO Auto-generated method stub
	int sum= 0;
	for(T v:adjList.keySet())
		sum+=outDegree(v);
	
	return sum;
}
}
