package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import utilities.MyPoint;
import utilities.PointPair;
import utilities.edges.InternalEdge;
import utilities.edges.AbstractEdge;
import utilities.edges.MyEdge;
import utilities.triangles.AbstractTriangle;
import utilities.triangles.MyTriangle;
import utilities.triangles.Triangle;

public class EleFileReader {
	MyTriangle[] triangles;
	HashMap<MyPoint,ArrayList<MyTriangle>> pointMap = new HashMap<MyPoint,ArrayList<MyTriangle>>();
	
	public EleFileReader(String name, MyPoint[] points, HashMap<PointPair,MyEdge> edges){
		File file = new File(name + ".1.ele");
		
		initMap(points);
		readFile(file,points,edges);
	}
	
	private void initMap(MyPoint[] points){
		for(int i=1;i<points.length;i++){
			pointMap.put(points[i], new ArrayList<MyTriangle>());
		}
	}
	
	private void readFile(File input, MyPoint[] points, HashMap<PointPair,MyEdge> edges){
		BufferedReader br = null;	
		try {
			String currentLine;
			br = new BufferedReader(new FileReader(input));		
			currentLine = br.readLine();
			
			/*Leemos en la primera linea la dimension de los puntos (2 o 3)*/
			String[] firstLine = currentLine.trim().split("\\s+");

			/*Leemos la segunda linea, que contiene el numero de puntos*/
			int totalTriangles = Integer.parseInt(firstLine[0]);
			triangles = new MyTriangle[totalTriangles + 1];
						
			int i = 1;
			while(i<=totalTriangles){
				currentLine=br.readLine();
				
				String[] coord = currentLine.trim().split("\\s+");
			
				if(coord.length>0){
					ArrayList<MyPoint> trianglePoints = new ArrayList<MyPoint>();
					
					MyPoint p1 = points[Integer.parseInt(coord[1])];
					MyPoint p2 = points[Integer.parseInt(coord[2])];
					MyPoint p3 = points[Integer.parseInt(coord[3])];
										
					trianglePoints.add(p1);
					trianglePoints.add(p2);
					trianglePoints.add(p3);
					
					triangles[i] = new Triangle(trianglePoints);
					
					addToMap(trianglePoints,triangles[i]);
										
					edges.get(new PointPair(p1,p2)).setTriangle(triangles[i]);
					edges.get(new PointPair(p2,p3)).setTriangle(triangles[i]);
					edges.get(new PointPair(p3,p1)).setTriangle(triangles[i]);
				}
				
				i++;
			}	
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
    }
	
	public MyTriangle[] getTriangles(){
		return triangles;
	}
	
	private void addToMap(ArrayList<MyPoint> points, MyTriangle triangle){
		for(int i=0;i<points.size();i++){
			pointMap.get(points.get(i)).add(triangle);
		}
	}
	
	public HashMap<MyPoint,ArrayList<MyTriangle>> getPointMap(){
		return pointMap;
	}
}
