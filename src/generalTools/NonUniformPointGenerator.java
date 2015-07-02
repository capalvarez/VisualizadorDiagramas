package generalTools;

import java.util.ArrayList;

import utilities.MyPoint;

public class NonUniformPointGenerator {
	MyPoint[] points;
	
	public NonUniformPointGenerator(double iX,double mX,double iY,double mY,double w,double h){		
		ArrayList<Double> xValues = new ArrayList<Double>();
		double current=0;
		
		for(int i=0;current<=w;i++){
			xValues.add(current);	
			current = current + iX*Math.pow(mX,i);
		}
		
		ArrayList<Double> yValues = new ArrayList<Double>();
		double currentY=0;
		
		for(int i=0;currentY<=h;i++){
			yValues.add(currentY);	
			currentY = currentY + iY*Math.pow(mY,i);
		}
		
		points = new MyPoint[yValues.size()*xValues.size()];
		int global = 0;
		
		for(int i=0;i<yValues.size();i++){
			for(int j=0;j<xValues.size();j++){
				points[global] = new MyPoint(xValues.get(j),yValues.get(i));
				global++;
			}
		}
	}
	
	
	public MyPoint[] getPoints(){
		return points;
	}

}
