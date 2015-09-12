package writers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.RandomStringUtils;

import utilities.MyPoint;

public class PointInputWriter {
	MyPoint[] points;
	
	public PointInputWriter(MyPoint[] pointsToWrite){
		points = pointsToWrite;
	}
	
	public String writeInFile() throws FileNotFoundException, UnsupportedEncodingException{
		String fileName = "../data/" + RandomStringUtils.random(8, true, true);
		
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		
		/*Escribir la dimension de los puntos, asumimos que todos tienen la misma dimension*/
		writer.println(points[0].getDimension());
		
		/*Escribir el numero de puntos de input*/
		writer.println(points.length);
		
		/*Escribir uno a uno los puntos a procesar*/
		for(int i=0;i<points.length;i++){
			/*Por ahora, se asume trabajar en 2D*/
			writer.println(points[i].getX()+"     "+points[i].getY());
		}
		
		writer.close();
	
		return fileName;
	}
}
