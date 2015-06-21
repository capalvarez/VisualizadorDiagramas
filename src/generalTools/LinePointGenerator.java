package generalTools;

import utilities.MyPoint;

public class LinePointGenerator {
	
	public MyPoint[] getPointsX(double min,double max,double jump,double y){
		int number = (int) Math.floor(Math.abs(max-min)/jump);
		MyPoint[] returnValues = new MyPoint[number+1];
		
		for(int i=0;i<=number;i++){
			returnValues[i] = new MyPoint(min+i*jump,y);	
		}
		
		return returnValues;
	}
	
	public MyPoint[] getPointsY(double min,double max,double jump,double x){
		int number = (int) Math.floor(Math.abs(max-min)/jump);
		MyPoint[] returnValues = new MyPoint[number+1];
		
		for(int i=0;i<=number;i++){
			returnValues[i] = new MyPoint(x,min+i*jump);	
		}
		
		return returnValues;		
	}
	
}
