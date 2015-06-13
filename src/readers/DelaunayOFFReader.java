package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dataProcessors.DelaunayTriangleSorter;

import utilities.MyPoint;
import utilities.MyTriangle;

public class DelaunayOFFReader {
	private MyTriangle[] triangleList;
		
	public DelaunayOFFReader(String fileName, MyPoint[] inputPoints){
		BufferedReader br = null;	
		
		try {
			String currentLine;

			br = new BufferedReader(new FileReader(new File(fileName)));
					
			/*Leer numero de triangulos en la triangulacion*/
			currentLine = br.readLine();
			int triangleNumber = Integer.parseInt(currentLine);
			
			triangleList = new MyTriangle[triangleNumber];
			
			for(int i=0;i<triangleNumber;i++){
				currentLine = br.readLine();
				String[] indexes = currentLine.split("\\s+");
								
				MyPoint[] points = {inputPoints[Integer.parseInt(indexes[0])],
						inputPoints[Integer.parseInt(indexes[1])],inputPoints[Integer.parseInt(indexes[2])]};
				
				triangleList[i] = new MyTriangle(points);	
			}
			
			currentLine = br.readLine();
			
			/*Leer el resto del archivo, que contiene los vecinos de cada triangulo*/	
			for(int i=0;i<triangleNumber;i++){
				currentLine = br.readLine();
				String[] indexes = currentLine.split("\\s+");
						
				for(int j=1;j<4;j++){
					int index = Integer.parseInt(indexes[j]);
										
					if(index>=0){
						triangleList[i].setNeighbour(triangleList[index],j-1);
					}else{
						triangleList[i].setNeighbour(null,j-1);
					}
						
				}	
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public MyTriangle[] getTriangleList() {	
		DelaunayTriangleSorter dts = new DelaunayTriangleSorter(triangleList);

		return triangleList;
	}
}
