package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import utilities.MyPoint;
import utilities.MyTriangle;
import utilities.MyEdge;
import utilities.PointPair;

public class EleFileReader {
	MyTriangle[] triangles;
	
	public EleFileReader(String name, MyPoint[] points, HashMap<PointPair,MyEdge> edges){
		File file = new File(name + ".1.ele");
		
		readFile(file,points,edges);
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
					
					triangles[i] = new MyTriangle(trianglePoints);
					
					edges.get(new PointPair(p1,p2)).setTriangle(triangles[i]);
					edges.get(new PointPair(p2,p3)).setTriangle(triangles[i]);
					edges.get(new PointPair(p3,p1)).setTriangle(triangles[i]);
															
					/*Para asegurarse de no tomar lineas vacias*/
					triangles[i] = new MyTriangle(trianglePoints);
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
}
