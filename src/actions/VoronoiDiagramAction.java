package actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;

import readers.VoronoiOFFReader;

import utilities.MyEdge;
import utilities.MyPoint;

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
		MyPoint[] pointArray;
		/*Llamar a libreria para obtener diagrama de Voronoi*/
		VoronoiLibraryCall v = new VoronoiLibraryCall("input");
		try {
			String output = v.callSystem();
			/*Si llamada es exitosa puedo leer del archivo de output*/
			VoronoiOFFReader reader = new VoronoiOFFReader(output);
			String[] points = reader.getPointList();
			MyEdge[] edges = (new VoronoiEdgeProcess(reader.getRegionList())).getEdgeList();
			
			Dimension size = window.getSize();
			
			MyPoint[] pointsToDraw = (new PointProcess(points, size.height, size.width)).getPointList();
			
			for(int i=0;i<pointsToDraw.length;i++){
				System.out.println(pointsToDraw[i].toString());
			}
			
			System.out.println(" ");
			
			for(int i=0;i<edges.length;i++){
				System.out.println(edges[i].toString());
			}
			
			
			window.drawDiagramInPanel(pointsToDraw,edges);
			
		}catch (IOException e1) {
			e1.printStackTrace();
		}catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
