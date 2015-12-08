package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import utilities.MyPoint;

public class NodeFileReader {
	MyPoint[] points;
	
	public NodeFileReader(String name){
		File file = new File(name + ".1.node");
		
		readFile(file);
	}
	
	private void readFile(File input){
		BufferedReader br = null;	
		try {
			String currentLine;
			br = new BufferedReader(new FileReader(input));		
			currentLine = br.readLine();
			
			/*Leemos en la primera linea la dimension de los puntos (2 o 3)*/
			String[] firstLine = currentLine.trim().split("\\s+");

			/*Leemos la segunda linea, que contiene el numero de puntos*/
			int totalPoints = Integer.parseInt(firstLine[0]);
			points = new MyPoint[totalPoints + 1];
						
			int i = 1;
			while(i<=totalPoints){
				currentLine=br.readLine();
				
				String[] coord = currentLine.trim().split("\\s+");
				
				if(coord.length>0){
					/*Para asegurarse de no tomar lineas vacias*/
					points[i] = new MyPoint(Double.parseDouble(coord[1].trim()),Double.parseDouble(coord[2].trim()));
				}
				
				i++;
			}	
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
    }
	
	public MyPoint[] getPoints(){
		return points;
	}
}
