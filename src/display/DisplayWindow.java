package display;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import utilities.MyPoint;

public class DisplayWindow extends IWindow{
	DisplayMenu menu;
	ShapePanel SPanel;
	
	public DisplayWindow(){
		menu = new DisplayMenu(this);
	}
	
	public void showWindow(){
		setTitle("Primera ventana");
	    setSize(600, 400);
	    setLocationRelativeTo(null);
	    setLayout(new BorderLayout());

		SPanel = new ShapePanel();
		this.add(SPanel,BorderLayout.CENTER);
	    
	    setJMenuBar(menu.getMenuBar());
	    setVisible(true);
	}

	public void drawPointsInPanel(MyPoint[] points) {
		SPanel.setPointsToPaint(points);
		SPanel.switchShown();	
		repaint();
		validate();
	}
	
	/*public void abrirArchivo(){
		JFileChooser fileopen = new JFileChooser();
		int ret = fileopen.showDialog(panel, "Abrir archivo");
	
	}
	
	public void abrirTablaManual(){
		JTable table = new JTable();
	    JScrollPane scrollpane = new JScrollPane(table);
	 
	}*/
	
}
