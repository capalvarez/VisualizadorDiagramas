package actions;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;

import readers.TriangleDelaunayFilesReader;
import utilities.MyPoint;
import voronoiProcessors.DelaunayToVoronoiProcess;
import writers.PolyFileWriter;
import libraryCallers.TriangleDelaunayCall;
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
			fileName = (new PolyFileWriter(pointArray, window.getCurrentRegion())).writeInFile();
		} catch (FileNotFoundException | UnsupportedEncodingException e2) {
			e2.printStackTrace();
			return;
		}
			
		/*Llamar a libreria para obtener diagrama de Voronoi*/
		TriangleDelaunayCall del = new TriangleDelaunayCall(fileName);
		try {
			del.callSystem();
			
			/*Si llamada es exitosa puedo leer del archivo de output*/
			TriangleDelaunayFilesReader reader = new TriangleDelaunayFilesReader(fileName);
			DelaunayToVoronoiProcess voronoi = new DelaunayToVoronoiProcess(reader.getTriangles(),reader.getEdges());
			
			window.setPoints(Arrays.copyOfRange(reader.getDelaunayNodes(),1,reader.getDelaunayNodes().length));
					
			window.setVoronoiCells(voronoi.getVoronoiCells());
			window.drawDiagramInPanel(voronoi.getVoronoiPoints(),voronoi.getVoronoiEdges());		
		}catch (IOException e1) {
			e1.printStackTrace();
		}catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
		
	}

}
