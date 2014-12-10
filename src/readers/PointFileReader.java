package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.MyPoint;

public class PointFileReader {
	MyPoint[] pointList;
	
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
			pointList = new MyPoint[nPoints];
			
			int i = 0;
			while((currentLine=br.readLine())!=null){
				String[] coord = currentLine.split("\\s+");
				
				if(coord.length>0){
					/*Para asegurarse de no tomar lineas vacias*/
					pointList[i] = new MyPoint(Integer.parseInt(coord[0].trim()),Integer.parseInt(coord[1].trim()));
				}
				
				i++;
			}	
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
    }

	private void printCoord(String[] coord){
		System.out.println("punto nuevo, son"+coord.length);
		
		for(int i=0;i<coord.length;i++){
			System.out.println(coord[i]);
		}
	}
	
	public MyPoint[] getPointList(){
		return pointList;
	}
	
}
