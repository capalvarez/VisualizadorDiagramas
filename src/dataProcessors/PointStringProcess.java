package dataProcessors;

import utilities.MyPoint;

public class PointStringProcess extends PointProcess{
	private String[] initPoints;
	double xMin = 1000000000;
	double xMax = -1000000000;
	double yMin = 1000000000;
	double yMax = -1000000000;
		
	public PointStringProcess(String[] points, int height, int width){
		initPoints = points;
		wHeight = height;
		wWidth = width;
		
		process();
	}
	
	private void process(){
		endPoints = new MyPoint[initPoints.length];
		initPointsList = new MyPoint[initPoints.length];
		/*Por ahora solamente valido para puntos en 2D*/
		
		/*Buscar los valores minimos y maximos en x e y para ajustar al tamaño de la ventana*/
		double xMed = 0;
		double yMed = 0;
		
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
		
			xMed += x;
			yMed += y;
	
		}
		
		xMed = xMed/(endPoints.length-1);
		yMed = yMed/(endPoints.length-1);
					
		for(int i=0;i<endPoints.length;i++){
			String[] info = (initPoints[i].trim()).split("\\s+");
			double x1 = Double.parseDouble(info[0]);
			double y1 = Double.parseDouble(info[1]);
					
			int x = (int) Math.round(wWidth*(x1-1.5*xMin)/(1.5*(xMax-xMin)));
			int y = (int) Math.round(wHeight/(1.5*(yMax-yMin))*(y1-1.5*yMin));
							
			endPoints[i] = new MyPoint(x,y,x1<xMed? -1 : 1, y1<yMed? -1 : 1);	
			initPointsList[i] =  endPoints[i];
		}
	}
	
	public double[] getScale(){
		double[] scale = {xMax,xMin,yMax,yMin};
		return scale;
	}
}
