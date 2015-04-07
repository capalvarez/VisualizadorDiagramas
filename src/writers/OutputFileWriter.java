package writers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import utilities.*;

public class OutputFileWriter {
	MyPoint[] points;
	MyPoint[] voronoiPoints;
	int[][] voronoiEdges;
	MyCell[] voronoiCells;
	File outputFile;
	
	public OutputFileWriter(MyPoint[] points, MyPoint[] vPoints, int[][] vEdges, MyCell[] vCells, File out){
		this.points = points;
		voronoiPoints = vPoints;
		voronoiEdges = vEdges;
		voronoiCells = vCells;
		outputFile = out;
	}
	
	public void writeInFile() throws IOException{
		FileOutputStream fos = new FileOutputStream(outputFile);
		OutputStreamWriter writer = new OutputStreamWriter(fos);
			
		/*Escribir el numero de puntos de input*/
		writer.write(points.length);
		
		/*Escribir uno a uno los puntos a usados*/
		for(int i=0;i<points.length;i++){
			/*Por ahora, se asume trabajar en 2D*/
			writer.write(points[i].toString());
		}
		
		/*Escribir el numero de puntos de Voronoi*/
		writer.write(voronoiPoints.length);
		
		/*Escribir los puntos de Voronoi*/
		for(int i=0;i<voronoiPoints.length;i++){
			/*Por ahora, se asume trabajar en 2D*/
			writer.write(voronoiPoints[i].toString());
		}
				
		/*Escribir el numero de arcos de Voronoi*/
		writer.write(voronoiEdges.length);
				
		/*Escribir los arcos de Voronoi*/
		for(int i=0;i<voronoiEdges.length;i++){
			/*Por ahora, se asume trabajar en 2D*/
			writer.write(voronoiEdges[i][0] + "    " + voronoiEdges[i][1]);
		}
				
		/*Escribir el numero de regiones de Voronoi*/
		writer.write(voronoiCells.length);
		
		/*Escribir las regiones de Voronoi*/
		for(int i=0;i<voronoiCells.length;i++){
			/*Por ahora, se asume trabajar en 2D*/
			writer.write(voronoiCells[i].toString());
		}
				
		writer.close();
	}

}
