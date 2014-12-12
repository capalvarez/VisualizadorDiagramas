package utilities;

public class MyEdge {
	private int i1;
	private int i2;
	
	public MyEdge(int i, int j){
		i1 = i;
		i2 = j;
	}
	
	public int getIndexFirst(){
		return i1;
	}

	public int getIndexSecond(){
		return i2;
	}	
}
