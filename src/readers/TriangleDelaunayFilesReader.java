package readers;

import utilities.MyPoint;
import utilities.edges.MyEdge;
import utilities.triangles.AbstractTriangle;
import utilities.triangles.MyTriangle;

public class TriangleDelaunayFilesReader{
	MyTriangle[] triangles;
	MyEdge[] edges;
	
	public TriangleDelaunayFilesReader(String fileName){
		MyPoint[] nodes = (new NodeFileReader(fileName)).getPoints();
		EdgeFileReader efr = new EdgeFileReader(fileName,nodes);
		
		edges = efr.getEdges();
		triangles = (new EleFileReader(fileName,nodes,efr.getDictionary())).getTriangles();	
		
		(new NeighFileReader(fileName,triangles)).addNeighbours();
	}
	
	public MyTriangle[] getTriangles(){
		return triangles;
	}
	
	public MyEdge[] getEdges(){
		return edges;
	}
}
