package readers;

import java.util.ArrayList;
import java.util.HashMap;

import utilities.MyPoint;
import utilities.edges.MyEdge;
import utilities.triangles.MyTriangle;

public class TriangleDelaunayFilesReader{
	MyTriangle[] triangles;
	MyEdge[] edges;
	MyPoint[] nodes;
	HashMap<MyPoint,ArrayList<MyTriangle>> pointMap;
	
	public TriangleDelaunayFilesReader(String fileName){
		nodes = (new NodeFileReader(fileName)).getPoints();
		
		EdgeFileReader efr = new EdgeFileReader(fileName,nodes);
		EleFileReader elefr = new EleFileReader(fileName,nodes,efr.getDictionary());
		
		edges = efr.getEdges();	
		triangles = elefr.getTriangles();	
		pointMap = elefr.getPointMap();
		
		(new NeighFileReader(fileName,triangles)).addNeighbours();
	}
	
	public MyTriangle[] getTriangles(){
		return triangles;
	}
	
	public MyEdge[] getEdges(){
		return edges;
	}
	
	public MyPoint[] getDelaunayNodes(){
		return nodes;
	}
	
	public HashMap<MyPoint,ArrayList<MyTriangle>> getPointMap(){
		return pointMap;
	}
	
}
