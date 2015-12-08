package utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MyPoint {
	private double x;
	private double y;
	private double z;
	private int dimension;
	
	public MyPoint(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.dimension = 3;
	}
	
	public MyPoint(double x, double y){
		this.x = x;
		this.y = y;
		this.z = 0;
		this.dimension = 2;
	}
	
	public MyPoint(int x, int y){
		this.x = x;
		this.y = y;
		this.z = 0;
		this.dimension = 2;
	}
		
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public double getZ(){
		return this.z;
	}
	
	public int getDimension(){
		return dimension;
	}
	
	public double squareNorm(){
		return (Math.pow(getX(), 2) + Math.pow(getY(), 2));
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof MyPoint){
			MyPoint p2 = (MyPoint)o;
			
			return Math.abs(p2.getX()-this.getX())<0.000001 && Math.abs(p2.getY()-this.getY())<0.000001
					   && p2.getZ()-this.getZ()<0.000001;		
		}else{
			return false;
		}		   	
	}
	
	@Override
	public String toString(){
		return this.x + " " + this.y;
	}
	
	public double distance(MyPoint p2){
		return Math.sqrt(Math.pow(p2.getX() - getX(), 2) + Math.pow(p2.getY() - getY(), 2));
	}
	
	public MyPoint getPointRight(MyPoint p2){
		if(this.x<=p2.getX()){
			return p2;
		}else{
			return this;
		}	
	}
	
	public MyPoint getPointLeft(MyPoint p2){
		if(this.x<=p2.getX()){
			return this;
		}else{
			return p2;
		}	
	}
	
	public MyPoint getPointDown(MyPoint p2){
		if(this.y<=p2.getY()){
			return this;
		}else{
			return p2;
		}	
	}
	
	public MyPoint getPointUp(MyPoint p2){
		if(this.y>=p2.getY()){
			return this;
		}else{
			return p2;
		}	
	}
	
	public MyPoint substract(MyPoint p){
		double x = this.getX() - p.getX();
		double y = this.getY() - p.getY();
		
		return new MyPoint(x,y);
	}
	
	public MyPoint getSmallestPoint(MyPoint p){
		if(this.getX()<p.getX()){
			return this;
		}else{
			if(this.getX()==p.getX()){
				if(this.getY()<p.getY()){
					return this;
				}else{
					return p;
				}
			}else{
				return p;
			}
		}
	}
	
	public MyPoint getGreatestPoint(MyPoint p){
		if(this.getX()>p.getX()){
			return this;
		}else{
			if(this.getX()==p.getX()){
				if(this.getY()>p.getY()){
					return this;
				}else{
					return p;
				}
			}else{
				return p;
			}
		}
	}
	
	public MyPoint closestTo(ArrayList<MyPoint> list){
		final MyPoint thisPoint = this;
				
		Collections.sort(list, new Comparator<MyPoint>() {
	         @Override
	         public int compare(MyPoint p1, MyPoint p2) {
	           	 double dist1 = p1.distance(thisPoint);
	         	 double dist2 = p2.distance(thisPoint); 
	    	         	 
	        	 if(dist1<dist2){
	             	return -1;
	             }else{
	            	if(dist1==dist2)
	            		return 0;
	            	else
	            		return 1;
	             }
	         }
	     });
		
		return list.get(0);
	}
	
	public void add(double xVal, double yVal){
		x = x + xVal;
		y = y + yVal;
	}
}
