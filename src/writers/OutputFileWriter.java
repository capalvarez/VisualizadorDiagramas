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
	MyCell[] voronoiCells;
	
	File outputFile;
	
	public OutputFileWriter(MyPoint[] points, MyPoint[] vPoints, MyEdge[] vEdges, MyCell[] cells, File out) throws IOException{
		this.points = points;
		voronoiPoints = vPoints;
		voronoiEdges = vEdges;
		voronoiCells = cells;
		outputFile = out;
			
		writeInFile();
	}
		
	public void writeInFile() throws IOException{
		FileOutputStream fos = new FileOutputStream(outputFile);
		OutputStreamWriter writer = new OutputStreamWriter(fos);
			
		/*Escribir el numero de puntos de input*/
		writer.write(points.length + "\n");
		
		/*Escribir uno a uno los puntos a usados*/
		for(int i=0;i<points.length;i++){
			writer.write(points[i].toString() + "\n");
		}
		
		/*Escribir el numero de puntos de Voronoi*/
		writer.write(voronoiPoints.length + "\n");
		
		/*Escribir los puntos de Voronoi*/
		for(int i=0;i<voronoiPoints.length;i++){
			writer.write(voronoiPoints[i].toString() + "\n");
		}
				
		/*Escribir el numero de arcos de Voronoi*/
		writer.write(voronoiEdges.length + "\n");
				
		/*Escribir los arcos de Voronoi*/
		for(int i=0;i<voronoiEdges.length;i++){
			/*Por ahora, se asume trabajar en 2D*/
			writer.write(voronoiEdges[i].toString() + "\n");
		}
				
		/*Escribir el numero de regiones de Voronoi*/
		writer.write(voronoiCells.length + "\n");
		
		/*Escribir las regiones de Voronoi*/
		for(int i=0;i<voronoiCells.length;i++){
			/*Por ahora, se asume trabajar en 2D*/
			writer.write(voronoiCells[i].toString() + "\n");
		}
				
		writer.close();
	}

}
