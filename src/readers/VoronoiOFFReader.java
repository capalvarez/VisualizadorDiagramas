package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import utilities.MyPoint;

public class VoronoiOFFReader{
	private int dimension;
	private int inputPoints;
	private int voronoiPoints;
	private String[] voronoiPointsList;
	private String[] voronoiRegionList;
	
	/*Antes de devolver los valores, habria que procesarlos*/
	
	public VoronoiOFFReader(String fileName){
		BufferedReader br = null;	
		
		try {
			String currentLine;

			br = new BufferedReader(new FileReader(new File(fileName)));
			
			/*Leer dimension*/
			currentLine = br.readLine();
			dimension = Integer.parseInt(currentLine);
			
			/*Leer segunda linea*/
			currentLine = br.readLine();
			String[] results = currentLine.split("\\s+");
		
			/*Segunda linea contiene N°Ptos de Voronoi - N°de regiones - 1*/
			inputPoints = Integer.parseInt(results[1]);
			
			/*Leer el numero de lineas que corresponde al numero de vertices (result[0])*/
			voronoiPoints = Integer.parseInt(results[0]);
			
			voronoiPointsList = new String[voronoiPoints];
			
			for(int i=0;i<voronoiPoints;i++){
				voronoiPointsList[i] = br.readLine();	
			}
			
			/*Leer el resto del archivo, que contiene las regiones de Voronoi*/	
			int i = 0;
			voronoiRegionList = new String[inputPoints];
			while((currentLine=br.readLine())!=null){
				voronoiRegionList[i] = currentLine;
				i++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public int getInputPointNumber() {
		return inputPoints;
	}

	public int getDimension() {
		return dimension;
	}

	public String[] getPointList() {		
		return voronoiPointsList;
	}

	public String[] getRegionList() {
		return voronoiRegionList;
	}
}
