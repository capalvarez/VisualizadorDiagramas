package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
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
		     try {
				(new OutputFileWriter(window.getCurrentPoints(),window.getVoronoiPoints(),window.getVoronoiEdges(), fileToSave)).writeInFile();
		     }catch (IOException e) {
				e.printStackTrace();
		     }		     
		 }

	}

}
