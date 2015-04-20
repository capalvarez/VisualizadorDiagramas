package writers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import utilities.*;

public class OutputFileWriter {
	MyPoint[] points;
	MyPoint[] voronoiPoints;
	MyEdge[] voronoiEdges;
	File outputFile;
	
	public OutputFileWriter(MyPoint[] points, MyPoint[] vPoints, MyEdge[] vEdges, File out){
		this.points = points;
		voronoiPoints = vPoints;
		voronoiEdges = vEdges;
		outputFile = out;
	
		asignPointsToEdges();
	}
	
	private void asignPointsToEdges(){
		for(int i = 0;i<voronoiEdges.length;i++){
			voronoiEdges[i].setPoints(voronoiPoints[voronoiEdges[i].getIndexFirst()], voronoiPoints[voronoiEdges[i].getIndexSecond()]);
		}
	}
	
	
	public void writeInFile() throws IOException{
		FileOutputStream fos = new FileOutputStream(outputFile);
		OutputStreamWriter writer = new OutputStreamWriter(fos);
			
		/*Escribir el numero de puntos de input*/
		writer.write(points.length);
		
		/*Escribir uno a uno los puntos a usados*/
		for(int i=0;i<points.length;i++){
				writer.write(points[i].toString());
		}
		
		/*Escribir el numero de puntos de Voronoi*/
		writer.write(voronoiPoints.length);
		
		/*Escribir los puntos de Voronoi*/
		for(int i=0;i<voronoiPoints.length;i++){
			writer.write(voronoiPoints[i].toString());
		}
				
		/*Escribir el numero de arcos de Voronoi*/
		writer.write(voronoiEdges.length);
				
		/*Escribir los arcos de Voronoi*/
		for(int i=0;i<voronoiEdges.length;i++){
			/*Por ahora, se asume trabajar en 2D*/
			writer.write(voronoiEdges.toString());
		}
				
		/*Escribir el numero de regiones de Voronoi*/
		//writer.write(voronoiCells.length);
		
		/*Escribir las regiones de Voronoi*/
		//for(int i=0;i<voronoiCells.length;i++){
			/*Por ahora, se asume trabajar en 2D*/
		//	writer.write(voronoiCells[i].toString());
		//}
				
		writer.close();
	}

}
