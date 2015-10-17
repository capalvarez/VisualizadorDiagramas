package utilities;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import utilities.perforations.Perforation;

public class PointPair{
    private MyPoint first;
    private MyPoint second;
    private Perforation per;

    public PointPair(MyPoint first, MyPoint second) {
        this.first = first;
        this.second = second;
    }

    public MyPoint getFirst() {
        return first;
    }

    public MyPoint getSecond() {
        return second;
    }
    
    public void setFirst(MyPoint p) {
        first = p;
    }

    public void setSecond(MyPoint p) {
        second = p;
    }
        
    public void draw(Graphics2D g2d, MyScale s){
    	if(per!=null){
    		MyPoint[] limits = {first,second};
    		per.drawPerforation(g2d, s, limits);
    	}else{
    		MyPoint fS = s.getPixelValue(first);
    		MyPoint sS = s.getPixelValue(second);
    		
    		g2d.draw(new Line2D.Double(fS.getX(),fS.getY(),sS.getX(),sS.getY()));
    	}
    }
    
    public void setPerforation(Perforation p){
    	per = p;
    }
    
    public PointPair divide(MyPoint p){
    	PointPair newPair = new PointPair(p,second);
    	this.setSecond(p);
    	
    	return newPair;	
    }
    
    public ArrayList<MyPoint> intersectionPoint(Perforation p){
		return p.intersectionPoints(first, second);
    }
    
    @Override
    public String toString(){
    	return first.toString() + "-> " + second.toString();
    }
}