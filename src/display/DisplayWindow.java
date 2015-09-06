package display;

import java.awt.Color;
import java.awt.Dimension;

import dataProcessors.*;

import utilities.MyCell;
import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyRegion;
import utilities.MyScale;
import utilities.MyTriangle;

public class DisplayWindow extends IWindow{
	DisplayMenu menu;
	DrawPanel Panel;
	double precision;
	MyPoint[] currentPoints;
	MyRegion currentRegion;
	MyPoint[] voronoiPoints;
	MyEdge[] voronoiEdges;
	MyCell[] voronoiCells;
	MyScale scale;
				
	public DisplayWindow(){
		menu = new DisplayMenu(this);
		precision = 0.999;
	}
	
	public void setPrecision(double pres){
		this.precision = pres;
	}
	
	public double getPrecision(){
		return precision;
	}
	
	public void showWindow(){
		setTitle("Diagramas");
	    setSize(600, 400);
	    setLocationRelativeTo(null);
	    Panel = new DrawPanel();
	    getContentPane().add(Panel);
	    
	    setJMenuBar(menu.getMenuBar());
	    setVisible(true);
	}

	
	public void drawRegionInPanel(MyPoint[] points) {
		menu.setPuntosEnabled(true);
		menu.setBordesEnabled(true);
		
		PointInitProcess pip = new PointInitProcess(points,this);
		MyPoint[] regionPoints = pip.getPointList();
		scale = pip.getScale();
		
		currentRegion = new MyRegion(points[0],points[1]);
		currentRegion.setPixelValues(regionPoints[0],regionPoints[1]);
		Panel.setRegion(currentRegion);	
		Panel.setScale(scale);
		
		Panel.switchRegion(true);	
		repaint();
		validate();
	}
	
	public void drawPointsInPanel(MyPoint[] pointsToDraw, MyPoint[] points){
		menu.setDiagramasEnabled(true);
		currentPoints = points;

		MyPoint[] pixelPoints = new MyPoint[pointsToDraw.length];
		for(int i=0; i<pixelPoints.length; i++){
			pixelPoints[i] = scale.getPixelValue(pointsToDraw[i]);
		}
		
		Panel.setPointsToPaint(pixelPoints);
		Panel.switchPoints(true);	
		repaint();
		validate();	
	}
	
	public void drawDiagramInPanel(MyPoint[] points, MyEdge[] edges){
		menu.setPrintEnabled(true);
		
		Panel.setLinesToPaint(edges,scale);
		voronoiPoints = points;
		voronoiEdges = edges;		

		Panel.switchDiagram(true);	
		repaint();
		validate();	
	}
	
	public void drawCoordSysInPanel(MyPoint origin){			
		Panel.switchCoordSys(origin,true);	
		repaint();
		validate();	
	}
	
	public void drawDelaunay(MyTriangle[] triangles){
		menu.setPrintEnabled(true);
		Panel.setTriangles(triangles,scale);
		Panel.switchDelaunay(true);
		
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
 	
 	public MyCell[] getVoronoiCells(){
 		return voronoiCells;
 	}
 	
	public void changeColorDiagram(Color color){
		Panel.setColor(color);
		repaint();
	}
	
	public void changeDelaunayColor(Color color){
		Panel.setDelaunayColor(color);
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
	
	public void setVoronoiCells(MyCell[] cells){
		voronoiCells = cells;
	}
	
	public void deleteDiagram(){
		Panel.switchDiagram(false);
		Panel.switchDelaunay(false);
		
		menu.setPrintEnabled(false);
		
		repaint();
		validate();	
	}
	
	public void deletePoints(){
		deleteDiagram();
		Panel.switchPoints(false);
			
		menu.setDiagramasEnabled(false);
		
		repaint();
		validate();	
	}
	
	public void deleteRegion(){
		deleteDiagram();
		deletePoints();
		Panel.switchRegion(false);
			
		menu.setPuntosEnabled(false);
		
		repaint();
		validate();	
	}
	
}
