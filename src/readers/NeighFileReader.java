package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import utilities.MyTriangle;

public class NeighFileReader {
	MyTriangle[] triangles;
	
	public NeighFileReader(String name, MyTriangle[] triangle){
		File file = new File(name + ".1.neigh");
		triangles = triangle;
		
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
			int totalTriangles = Integer.parseInt(firstLine[0]);
						
			int i = 1;
			while(i<=totalTriangles){
				currentLine=br.readLine();
				
				String[] coord = currentLine.split("\\s+");
				
				if(coord.length>0){
					MyTriangle[] neighbours = new MyTriangle[3];
					
					for(int j=0;j<3;j++){
						neighbours[j] = getNeighbour(Integer.parseInt(coord[j+1]));
					}
										
					/*Para asegurarse de no tomar lineas vacias*/
					triangles[i].setNeighbours(neighbours);
				}
				
				i++;
			}	
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
    }
	
	private MyTriangle getNeighbour(int index){
		if(index>0){
			return triangles[index];
		}else{
			return null;
		}
	}
}
