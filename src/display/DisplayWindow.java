package display;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import utilities.MyPoint;

public class DisplayWindow extends IWindow{
	DisplayMenu menu;
	ShapePanel SPanel;
	PointPanel PPanel;
	CardLayout layout;
		
	public DisplayWindow(){
		menu = new DisplayMenu(this);
		layout = new CardLayout();
	}
	
	public void showWindow(){
		setTitle("Primera ventana");
	    setSize(600, 400);
	    setLocationRelativeTo(null);
	    getContentPane().setLayout(layout);

	    addPanels();
	    
	    setJMenuBar(menu.getMenuBar());
	    setVisible(true);
	}

	private void addPanels(){
		SPanel = new ShapePanel();
		getContentPane().add(SPanel, "shapePanel");
	    
		PPanel = new PointPanel();
		getContentPane().add(PPanel,"pointPanel");
	}
	
	
	public void drawPointsInPanel(MyPoint[] points) {
		layout.show(getContentPane(),"shapePanel");
		SPanel.setPointsToPaint(points);
		SPanel.switchShown();	
		repaint();
		validate();
	}
	
	public void drawDiagramInPanel(MyPoint[] points){
		layout.show(getContentPane(),"pointPanel");
		PPanel.setPointsToPaint(points);
		PPanel.switchShown();	
		repaint();
		validate();
		/*Incluir un boton que permita ver diagrama de Voronoi (incluso de Delaunay)*/
	
	
	}
	
	
	
	/*
	
	public void abrirTablaManual(){
		JTable table = new JTable();
	    JScrollPane scrollpane = new JScrollPane(table);
	 
	}*/
	
}
