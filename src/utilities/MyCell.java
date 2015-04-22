package utilities;

public class MyCell {
	int[] edges;
	
	public MyCell(int[] edges){
		this.edges = edges;
	}
	
	public String toString(){
		String result = edges.length + " ";
		
		for(int i = 0;i<edges.length;i++){
			result = result + edges[i] + " ";
		}
				
		return result;	
	}
	
	
	
}
