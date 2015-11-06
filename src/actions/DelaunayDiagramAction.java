package actions;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;

import libraryCallers.DelaunayLibraryCall;
import libraryCallers.TriangleDelaunayCall;
import readers.DelaunayOFFReader;
import utilities.MyPoint;
import writers.PointInputWriter;
import writers.PolyFileWriter;
import display.IWindow;

public class DelaunayDiagramAction extends AbstractAction {
	private IWindow window;	
	
	public DelaunayDiagramAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		/*Obtener puntos de pantalla->Escribirlos en un archivo para llamar a la libreria*/
		MyPoint[] pointArray = window.getCurrentPoints();
		String fileName;
		try {
			fileName = (new PolyFileWriter(pointArray, window.getCurrentRegion())).writeInFile();
		} catch (FileNotFoundException | UnsupportedEncodingException e2) {
			e2.printStackTrace();
			return;
		}
		System.out.println(fileName);
		/*Llamar a libreria para obtener diagrama de Voronoi*/
		TriangleDelaunayCall v = new TriangleDelaunayCall(fileName);
		try {
			String output = v.callSystem();
			System.out.println(output);
						
			/*Si llamada es exitosa puedo leer del archivo de output*/
			//DelaunayOFFReader reader = new DelaunayOFFReader(output,pointArray);
					
			//window.drawDelaunay(reader.getTriangleList());
		
		}catch (IOException e1) {
			e1.printStackTrace();
		}catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}
