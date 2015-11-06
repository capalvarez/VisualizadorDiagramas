package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import writers.OutputFileWriter;
import writers.PolyFileWriter;
import display.IWindow;

public class PolyFileAction extends AbstractAction{
private IWindow window;	
	
	public PolyFileAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {		 
		try {
			(new PolyFileWriter(window.getCurrentPoints(),window.getCurrentRegion())).writeInFile();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
