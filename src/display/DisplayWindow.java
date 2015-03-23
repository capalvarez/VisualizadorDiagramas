package display;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import dataProcessors.*;

import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyRegion;

public class DisplayWindow extends IWindow{
	DisplayMenu menu;
	ShapePanel SPanel;
	PointPanel PPanel;
	DiagramPanel DPanel;
	CardLayout layout;
	MyPoint[] currentPoints;
	MyRegion currentRegion;
			
	public DisplayWindow(){
		menu = new DisplayMenu(this);
		layout = new CardLayout();
	}
	
	public void showWindow(){
		setTitle("Diagramas :P");
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
		
		DPanel = new DiagramPanel();
		getContentPane().add(DPanel,"diagramPanel");
	}
	
	
	public void drawRegionInPanel(MyPoint[] points) {
		menu.setPuntosEnabled();
		layout.show(getContentPane(),"shapePanel");
		
		Dimension size = getSize();
		MyPoint[] regionPoints = new PointInitProcess(points,size.width, size.height).getPointList();
		SPanel.setPointsToPaint(regionPoints);
		
		currentRegion = new MyRegion(regionPoints[0],regionPoints[1]);
		
		SPanel.switchShown();	
		repaint();
		validate();
	}
	
	public void drawPointsInPanel(MyPoint[] pointsToDraw, MyPoint[] points){
		menu.setDiagramasEnabled();
		currentPoints = points;
		layout.show(getContentPane(),"pointPanel");
		PPanel.setPointsToPaint(pointsToDraw, currentRegion);
		PPanel.switchShown();	
		repaint();
		validate();	
	}
	
	public void drawDiagramInPanel(MyPoint[] points, MyEdge[] edges){
		layout.show(getContentPane(),"diagramPanel");
		DPanel.setLinesToPaint(points,edges);
		
		Dimension size = getSize();
		MyPoint[] newCPoints = (new PointScaleProcess(currentPoints,scaleToDraw, size.width, size.height)).getPointList();
				
		DPanel.setPointsToPaint(newCPoints);
		DPanel.switchShown();	
		repaint();
		validate();	
	}
	
	private void printPoints(MyPoint[] points){
		for(int i=0;i<points.length;i++){
			System.out.println(points[i].toString());
		}
	}
	
 	public MyPoint[] getCurrentPoints(){
		return currentPoints;
	}	
 	
 	public MyRegion getCurrentRegion(){
 		return currentRegion;
 	}
 	
	public void changeColorDiagram(Color color){
		SPanel.setColor(color);
		PPanel.setColor(color);
		DPanel.setColor(color);
		repaint();
	}
	
	public void changeBackGroundColor(Color color){
		SPanel.setBackGroundColor(color);
		PPanel.setBackGroundColor(color);
		DPanel.setBackGroundColor(color);
		repaint();
	}
	
	public void changePointSize(int size){
		SPanel.setPointSize(size);
		PPanel.setPointSize(size);
		DPanel.setPointSize(size);
		repaint();
	}
	
	public int getCurrPointSize(){
		return PPanel.getPointSize();
	}
}
