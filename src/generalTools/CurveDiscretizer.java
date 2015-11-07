package generalTools;

import java.util.ArrayList;

import utilities.MyPoint;

public class CurveDiscretizer {
	public MyPoint init;
	public MyPoint end;
	private double radius;
	private MyPoint center;
	
	public CurveDiscretizer(MyPoint i, MyPoint e, double rad, MyPoint center){
		init = i;
		end = e;
		this.center = center;
		radius = rad;
		
		
	}
	
	public MyPoint[] discretizeCircle(){
		ArrayList<MyPoint> points = new ArrayList<MyPoint>();
		
		double x = 0;
	    double y = radius;
	    double p = (1-radius);

	    do{
	    	points.add(new MyPoint((center.getX() + x),(center.getY() + y)));
	        points.add(new MyPoint((center.getX() + y),(center.getY() + x)));
	        points.add(new MyPoint((center.getX() + y),(center.getY() - x)));
	        points.add(new MyPoint((center.getX() + x),(center.getY() - y)));
	        points.add(new MyPoint((center.getX() - x),(center.getY() - y)));
	        points.add(new MyPoint((center.getX() - y),(center.getY() - x)));
	        points.add(new MyPoint((center.getX() - y),(center.getY() + x)));
	        points.add(new MyPoint((center.getX() - x),(center.getY() + y)));

	        x++;
	        if(p<0){
	        	p+=((2*x)+1);
	        }else{
	           y--;
	           p+=((2*(x-y))+1);
	        }
	      }while(x<=y);
	
	    return pointListToArray(points);
	}
	
	
	public MyPoint[] pointListToArray(ArrayList<MyPoint> list){	
		MyPoint[] array = new MyPoint[list.size()];
		array = list.toArray(array);
		
		return array;
	}
	
}
