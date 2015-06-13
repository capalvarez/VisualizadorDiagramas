package actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;

import readers.DelaunayOFFReader;
import readers.VoronoiOFFReader;

import utilities.MyCell;
import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyTriangle;
import writers.PointInputWriter;

import libraryCallers.DelaunayLibraryCall;
import libraryCallers.VoronoiLibraryCall;

import dataProcessors.PointStringProcess;
import dataProcessors.VoronoiRegionProcess;
import display.IWindow;

public class VoronoiDiagramAction extends AbstractAction {
	private IWindow window;	
	
	public VoronoiDiagramAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		/*Obtener puntos de pantalla->Escribirlos en un archivo para llamar a la libreria*/
		MyPoint[] pointArray = window.getCurrentPoints();
		String fileName;
		try {
			fileName = (new PointInputWriter(pointArray)).writeInFile();
		} catch (FileNotFoundException | UnsupportedEncodingException e2) {
			e2.printStackTrace();
			return;
		}
		
		MyTriangle[] triangles = null;
		
		/*Llamar a libreria para obtener diagrama de Voronoi*/
		DelaunayLibraryCall del = new DelaunayLibraryCall(fileName);
		try {
			String outputDelaunay = del.callSystem();
			
			/*Si llamada es exitosa puedo leer del archivo de output*/
			DelaunayOFFReader DelReader = new DelaunayOFFReader(outputDelaunay,pointArray);
			triangles = DelReader.getTriangleList();
		}catch (IOException e1) {
			e1.printStackTrace();
		}catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
		
		/*Si no puedo llamar a qdelaunay, termino la ejecución*/
		if(triangles==null){
			return;
		}
			
		/*Llamar a libreria para obtener diagrama de Voronoi*/
		VoronoiLibraryCall v = new VoronoiLibraryCall(fileName);
		try {
			String output = v.callSystem();
			
			/*Si llamada es exitosa puedo leer del archivo de output*/
			VoronoiOFFReader reader = new VoronoiOFFReader(output);
			String[] points = reader.getPointList();
			PointStringProcess psp = new PointStringProcess(points);		
						
			VoronoiRegionProcess vrp = new VoronoiRegionProcess(reader.getRegionList(),pointArray,psp.getPointList(),window.getCurrentRegion(),triangles);
			MyEdge[] edges = vrp.getEdgeList();
			MyCell[] voronoiCells = vrp.getCellList();  			
			
			MyPoint[] pointsToDraw = vrp.getPointList();
	
			window.setVoronoiCells(voronoiCells);
			window.drawDiagramInPanel(pointsToDraw,edges);
			
		}catch (IOException e1) {
			e1.printStackTrace();
		}catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
