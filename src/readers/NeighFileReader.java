package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import utilities.edges.MyEdge;
import utilities.triangles.MyTriangle;
import utilities.triangles.NullTriangle;
import utilities.MyPoint;
import utilities.PointPair;

public class NeighFileReader {
	MyTriangle[] triangles;
	File input;
	HashMap<MyEdge,MyTriangle> nullTriangleMap = new HashMap<MyEdge,MyTriangle>();
	HashMap<MyPoint,ArrayList<MyEdge>> externalDictionary;
	
	public NeighFileReader(String name, MyTriangle[] triangle){
		input = new File(name + ".1.neigh");
		triangles = triangle;
	}
	
	public void addNeighbours(){
		BufferedReader br = null;	
		try {
			String currentLine;
			br = new BufferedReader(new FileReader(input));		
			currentLine = br.readLine();
			
			/*Leemos en la primera linea la dimension de los puntos (2 o 3)*/
			String[] firstLine = currentLine.trim().split("\\s+");

			/*Leemos la segunda linea, que contiene el numero de puntos*/
			int totalTriangles = Integer.parseInt(firstLine[0]);
						
			int i = 1;
			while(i<=totalTriangles){
				currentLine=br.readLine();
				
				String[] coord = currentLine.trim().split("\\s+");
				
				if(coord.length>0){
					MyTriangle[] neighbours = new MyTriangle[3];
					
					for(int j=0;j<3;j++){
						neighbours[j] = getNeighbour(Integer.parseInt(coord[j+1]),triangles[i].getOppositeEdge(j),j);
						triangles[i].setNeighbourInMap(neighbours[j], j);
					}
										
					/*Para asegurarse de no tomar lineas vacias*/
					triangles[i].setNeighbours(neighbours);
				}
				
				i++;
			}	
			
			correctNullNeighbours();		
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
    }
	
	private MyTriangle getNeighbour(int index, MyEdge e, int pointIndex){
		if(index>0){
			return triangles[index];
		}else{
			NullTriangle nullT = new NullTriangle(e); 
			nullTriangleMap.put(e, nullT);
			
			return nullT;
		}
	}
	
	private void correctNullNeighbours(){
		MyEdge initEdge = null;
		MyEdge e = initEdge;
		
		MyTriangle t = nullTriangleMap.get(initEdge);
		t.setNeighbourInMap(nullTriangleMap.get(getNextEdge(initEdge.getFirstPoint(),initEdge)), 0);
		
		MyEdge nextEdge = getNextEdge(initEdge.getFirstPoint(),initEdge);
		int i = 0;		
		MyTriangle nextTriangle;
		
		while(!initEdge.equals(e) || i==0){
			nextTriangle = nullTriangleMap.get(nextEdge);
			t.setNeighbourInMap(nullTriangleMap.get(nextEdge), 0);
			nextTriangle.setNeighbourInMap(t, 0);
			
			t = nextTriangle;
			e = nextEdge;		
			
			i++;
		}
	}
	
	private MyEdge getNextEdge(MyPoint p, MyEdge e){
		ArrayList<MyEdge> edges = externalDictionary.get(p);
		
		if(edges.get(0).equals(e)){
			return edges.get(1);
		}else{
			return edges.get(0);
		}
		
	}
}
