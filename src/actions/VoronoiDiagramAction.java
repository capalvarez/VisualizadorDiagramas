package actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;

import readers.VoronoiOFFReader;

import utilities.MyEdge;
import utilities.MyPoint;
import writers.PointInputWriter;

import libraryCallers.VoronoiLibraryCall;

import dataProcessors.PointProcess;
import dataProcessors.VoronoiEdgeProcess;
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
		
		/*Llamar a libreria para obtener diagrama de Voronoi*/
		VoronoiLibraryCall v = new VoronoiLibraryCall(fileName);
		try {
			String output = v.callSystem();
			/*Si llamada es exitosa puedo leer del archivo de output*/
			VoronoiOFFReader reader = new VoronoiOFFReader(output);
			String[] points = reader.getPointList();
			MyEdge[] edges = (new VoronoiEdgeProcess(reader.getRegionList())).getEdgeList();
			
			Dimension size = window.getSize();
			
			MyPoint[] pointsToDraw = (new PointProcess(points, size.height, size.width)).getPointList();
			
			window.drawDiagramInPanel(pointsToDraw,edges);
			
		}catch (IOException e1) {
			e1.printStackTrace();
		}catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
