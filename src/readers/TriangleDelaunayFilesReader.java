package readers;

import utilities.MyPoint;
import utilities.MyTriangle;

public class TriangleDelaunayFilesReader{
	MyTriangle[] triangles;
	
	public TriangleDelaunayFilesReader(String fileName){
		MyPoint[] nodes = (new NodeFileReader(fileName)).getPoints();
		EdgeFileReader efr = new EdgeFileReader(fileName,nodes);
						
		triangles = (new EleFileReader(fileName,nodes,efr.getDictionary())).getTriangles();
		
		(new NeighFileReader(fileName,triangles)).addNeighbours();
	}
	
	public MyTriangle[] getTriangles(){
		return triangles;
	}
}
