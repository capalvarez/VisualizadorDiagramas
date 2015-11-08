package generalTools;

import java.util.ArrayList;

import utilities.MyPoint;

public class CurveDiscretizer {
	private double radius;
	private MyPoint center;
	
	public CurveDiscretizer(double rad, MyPoint center){
		this.center = center;
		radius = rad;	
	}
	
	private double radian(double angle){
		return angle*Math.PI/180.0;	
	}
	
	public ArrayList<MyPoint>  discretizeCircleFine(){
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
	
	    return points;
	}
	
	public ArrayList<MyPoint> discretizeCircle(int grade){
		return discretizeArc(0,360,grade);
	}
	
	
	public ArrayList<MyPoint> discretizeArc(double init, double end, double grade){
		ArrayList<MyPoint> points = new ArrayList<MyPoint>();
		double delta = Math.abs(end-init)/grade;
		
		double angle = init;
		while(angle<end){
			double x = center.getX() + radius*Math.cos(radian(angle));
			double y = center.getY() + radius*Math.sin(radian(angle));
			
			points.add(new MyPoint(x,y));
			
			angle += delta;
		}
		
		return points;	
	}
}
