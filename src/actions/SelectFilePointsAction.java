package actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import readers.PointFileReader;

import utilities.MyPoint;

import display.DisplayWindow;
import display.IWindow;

public class SelectFilePointsAction extends AbstractAction{
	private IWindow window;	
	
	public SelectFilePointsAction(IWindow w){
		window = w;
	}
	
	public String readFile(File file) {

        StringBuffer fileBuffer = null;
        String fileString = null;
        String line = null;

        try {
            FileReader in = new FileReader(file);
            BufferedReader brd = new BufferedReader(in);
            fileBuffer = new StringBuffer();

            while ((line = brd.readLine()) != null) {
                fileBuffer.append(line).append(
                        System.getProperty("line.separator"));
            }

            in.close();
            fileString = fileBuffer.toString();
        } catch (IOException e) {
            return null;
        }
        return fileString;
    }
	
	@Override
	public void actionPerformed(ActionEvent event){
		/*Hacer que el usuario seleccione el archivo deseado*/
		JFileChooser fileopen = new JFileChooser();
		int ret = fileopen.showDialog(window, "Abrir archivo");
		
		/*Solamente leemos el archivo si el usuario apreta OK*/
		if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            MyPoint[] pointArray = (new PointFileReader(file)).getPointList();
            window.drawDiagramInPanel(pointArray);
    		window.repaint();
		}
	}		

}
