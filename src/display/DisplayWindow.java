package display;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import dataProcessors.*;

import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyRegion;
import utilities.MyScale;

public class DisplayWindow extends IWindow{
	DisplayMenu menu;
	DrawPanel Panel;
	MyPoint[] currentPoints;
	MyRegion currentRegion;
	MyPoint[] voronoiPoints;
	MyEdge[] voronoiEdges;
	MyScale scale;
				
	public DisplayWindow(){
		menu = new DisplayMenu(this);
	}
	
	public void showWindow(){
		setTitle("Diagramas :P");
	    setSize(600, 400);
	    setLocationRelativeTo(null);
	    Panel = new DrawPanel();
	    getContentPane().add(Panel);
	    
	    setJMenuBar(menu.getMenuBar());
	    setVisible(true);
	}

	
	public void drawRegionInPanel(MyPoint[] points) {
		menu.setPuntosEnabled();
		
		Dimension size = getSize();
		PointInitProcess pip = new PointInitProcess(points,size.width, size.height);
		MyPoint[] regionPoints = pip.getPointList();
		scale = pip.getScale();
		
		currentRegion = new MyRegion(regionPoints[0],regionPoints[1]);
		Panel.setRegion(currentRegion);	
		
		Panel.switchRegion();	
		repaint();
		validate();
	}
	
	public void drawPointsInPanel(MyPoint[] pointsToDraw, MyPoint[] points){
		menu.setDiagramasEnabled();
		currentPoints = points;

		Panel.setPointsToPaint(pointsToDraw);
		Panel.switchPoints();	
		repaint();
		validate();	
	}
	
	public void drawDiagramInPanel(MyPoint[] points, MyEdge[] edges){
		Panel.setLinesToPaint(points,edges);
		voronoiPoints = points;
		voronoiEdges = edges;
				
		Dimension size = getSize();
		//MyPoint[] newCPoints = (new PointScaleProcess(currentPoints,scaleToDraw, size.width, size.height)).getPointList();
				
		Panel.setPointsToPaint(points);
		Panel.switchDiagram();	
		repaint();
		validate();	
	}
	
	public void drawCoordSysInPanel(MyPoint origin){			
		Panel.switchCoordSys(origin);	
		repaint();
		validate();	
	}
	
	
 	public MyPoint[] getCurrentPoints(){
		return currentPoints;
	}	
 	
 	public MyRegion getCurrentRegion(){
 		return currentRegion;
 	}
 	
 	public MyPoint[] getVoronoiPoints(){
 		return voronoiPoints;
 	}
 	
 	public MyEdge[] getVoronoiEdges(){
 		return voronoiEdges;
 	}
 	
	public void changeColorDiagram(Color color){
		Panel.setColor(color);
		repaint();
	}
	
	public void changeBackGroundColor(Color color){
		Panel.setBackGroundColor(color);
		repaint();
	}
	
	public void changePointSize(int size){
		Panel.setPointSize(size);
		repaint();
	}
	
	public int getCurrPointSize(){
		return Panel.getPointSize();
	}
	
	public AbstractPanel getPanel(){
		return Panel;
	}

	public void setScale(MyScale s){
		scale = s;
	}
	
	public MyScale getScale(){
		return scale;
	}
	
}
