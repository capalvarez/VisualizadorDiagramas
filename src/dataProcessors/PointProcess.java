package dataProcessors;

import utilities.MyPoint;

public class PointProcess {
	private String[] initPoints;
	private MyPoint[] endPoints;
	private int wHeight;
	private int wWidth;
	
	public PointProcess(String[] points, int height, int width){
		initPoints = points;
		wHeight = height;
		wWidth = width;
		
		process();
	}
	
	private void process(){
		endPoints = new MyPoint[initPoints.length];
		/*Por ahora solamente valido para puntos en 2D*/
		/*Buscar los valores minimos y maximos en x e y para ajustar al tamaño de la ventana*/
		double xMin = 1000000000;
		double xMax = -1000000000;
		double yMin = 1000000000;
		double yMax = -1000000000;
				
		/*Para buscar minimo y maximo se comienza del segundo punto (el primero corresponde*/
		/*al vertice en el infinito)*/
		for(int i=1;i<endPoints.length;i++){
			String[] info = (initPoints[i].trim()).split("\\s+");
			
			double x = Double.parseDouble(info[0]);
			double y = Double.parseDouble(info[1]);
		
			xMax = x>xMax? x : xMax;
			xMin = x<xMin? x : xMin;
			yMax = y>yMax? y : yMax;
			yMin = y<yMin? y : yMin;	
		}
			
		for(int i=0;i<endPoints.length;i++){
			String[] info = (initPoints[i].trim()).split("\\s+");
			double x1 = Double.parseDouble(info[0]);
			double y1 = Double.parseDouble(info[1]);
					
			int x = (int) Math.round(wWidth/(1.3*(xMax-xMin))*(x1-1.3*xMin));
			int y = (int) Math.round(wHeight/(1.3*(yMax-yMin))*(y1-1.3*yMin));
				
			endPoints[i] = new MyPoint(x,y);		
		}
	}
	
	public MyPoint[] getPointList(){
		return endPoints;
	}

}
