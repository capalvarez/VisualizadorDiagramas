package actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import writers.OutputFileWriter;

import display.IWindow;

public class OutputFileAction extends AbstractAction {
	private IWindow window;	
	
	public OutputFileAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {		 
		 JFileChooser fileChooser = new JFileChooser();
		 fileChooser.setDialogTitle("Seleccione archivo a guardar");   
		  
		 int userSelection = fileChooser.showSaveDialog(window);
		  
		 if (userSelection == JFileChooser.APPROVE_OPTION) {
		     File fileToSave = fileChooser.getSelectedFile();		     
		     /*Tengo las aristas y tengo que encontrar los indices de los puntos que la componen*/
		     OutputFileWriter ofw = new OutputFileWriter(window.getCurrentPoints(),window.getVoronoiPoints(),window.getVoronoiEdges(), fileToSave);
		     
		 }

	}

}
