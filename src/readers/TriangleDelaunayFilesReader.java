package readers;

import utilities.MyPoint;
import utilities.MyTriangle;

public class TriangleDelaunayFilesReader{
	MyTriangle[] triangles;
	
	public TriangleDelaunayFilesReader(String fileName){
		MyPoint[] nodes = (new NodeFileReader(fileName)).getPoints();
		
		for(int i=0;i<nodes.length;i++){
			if(nodes[i]!=null){
				System.out.println(nodes[i].toString());
			}
		}
				
		triangles = (new EleFileReader(fileName,nodes)).getTriangles();
		
		(new NeighFileReader(fileName,triangles)).addNeighbours();
	}
	
	public MyTriangle[] getTriangles(){
		return triangles;
	}
}
