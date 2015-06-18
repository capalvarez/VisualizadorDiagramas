package display;

import java.awt.Color;

import javax.swing.JFrame;

import utilities.MyCell;
import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyRegion;
import utilities.MyScale;
import utilities.MyTriangle;

public abstract class IWindow extends JFrame{
	public abstract void drawPointsInPanel(MyPoint[] pointsToDraw,MyPoint[] points);

	public abstract void drawRegionInPanel(MyPoint[] points); 
	
	public abstract void drawDiagramInPanel(MyPoint[] points, MyEdge[] edges);

	public abstract MyPoint[] getCurrentPoints();

	public abstract MyRegion getCurrentRegion();
	
	public abstract MyPoint[] getVoronoiPoints();
	
	public abstract MyEdge[] getVoronoiEdges();
	
	public abstract MyCell[] getVoronoiCells();
	
	public abstract void changeColorDiagram(Color color);
	
	public abstract void changeDelaunayColor(Color color);

	public abstract void changeBackGroundColor(Color color);

	public abstract int getCurrPointSize();
	
	public abstract void changePointSize(int value); 
	
	public abstract void drawCoordSysInPanel(MyPoint origin);
	
	public abstract AbstractPanel getPanel();

	public abstract void setScale(MyScale scale);
	
	public abstract MyScale getScale();
	
	public abstract void setVoronoiCells(MyCell[] cells);

	public abstract void drawDelaunay(MyTriangle[] triangleList);
	
	public abstract void deleteDiagram();
	
	public abstract void deletePoints();
	
	public abstract void deleteRegion();
	
	
}
