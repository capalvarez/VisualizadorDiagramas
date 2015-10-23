package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.MyPoint;

public class PointFileReader {
	String[] pointList;
	MyPoint[] points;
	
	public PointFileReader(File input){
		readFile(input);
	}
	
	private void readFile(File input){
		BufferedReader br = null;	
		try {
			String currentLine;
			br = new BufferedReader(new FileReader(input));		
			currentLine = br.readLine();
			/*Leemos en la primera linea la dimension de los puntos (2 o 3)*/
			int dimension = Integer.parseInt(currentLine.trim());

			/*Leemos la segunda linea, que contiene el numero de puntos*/
			int nPoints = Integer.parseInt(br.readLine().trim());  
			pointList = new String[nPoints];
			points = new MyPoint[nPoints];
			
			int i = 0;
			while((currentLine=br.readLine())!=null){
				String[] coord = currentLine.split("\\s+");
				
				if(coord.length>0){
					/*Para asegurarse de no tomar lineas vacias*/
					pointList[i] = (coord[0].trim() +" " + (coord[1].trim()));
					points[i] = new MyPoint(Integer.parseInt(coord[0].trim()),Integer.parseInt(coord[1].trim()));
				}
				
				i++;
			}	
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
    }
	
	public String[] getPointList(){
		return pointList;
	}
	
	public MyPoint[] getPoints(){
		return points;
	}
	
}
