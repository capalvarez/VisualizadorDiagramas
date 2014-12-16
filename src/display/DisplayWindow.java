package display;

import java.awt.CardLayout;

import utilities.MyEdge;
import utilities.MyPoint;

public class DisplayWindow extends IWindow{
	DisplayMenu menu;
	ShapePanel SPanel;
	PointPanel PPanel;
	DiagramPanel DPanel;
	CardLayout layout;
	MyPoint[] currentPoints;
		
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
		
		DPanel = new DiagramPanel();
		getContentPane().add(DPanel,"diagramPanel");
	}
	
	
	public void drawRegionInPanel(MyPoint[] points) {
		layout.show(getContentPane(),"shapePanel");
		SPanel.setPointsToPaint(points);
		SPanel.switchShown();	
		repaint();
		validate();
	}
	
	public void drawPointsInPanel(MyPoint[] pointsToDraw, MyPoint[] points){
		menu.setDiagramasEnabled();
		currentPoints = points;
		layout.show(getContentPane(),"pointPanel");
		PPanel.setPointsToPaint(pointsToDraw);
		PPanel.switchShown();	
		repaint();
		validate();	
	}
	
	public void drawDiagramInPanel(MyPoint[] points, MyEdge[] edges){
		layout.show(getContentPane(),"diagramPanel");
		DPanel.setLinesToPaint(points,edges);
		DPanel.switchShown();	
		repaint();
		validate();	
	}
	
	public MyPoint[] getCurrentPoints(){
		return currentPoints;
	}
}
