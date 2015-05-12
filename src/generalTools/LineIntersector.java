package generalTools;

import utilities.MyEdge;
import utilities.MyPoint;

public class LineIntersector {
	private double EPSILON = 0.000001;
	private MyEdge testLine;
	
	public LineIntersector(MyEdge testLine){
		this.testLine = testLine;
	}	
		
	public double crossProduct(MyPoint a, MyPoint b) {
        return a.getX() * b.getY() - b.getX() * a.getY();
    }

	public boolean doBoundingBoxesIntersect(MyPoint[] a, MyPoint[] b) {
		return a[0].getX()<=b[1].getX() && a[1].getX()>=b[0].getX() && 
			   a[0].getY()<=b[1].getY()	&& a[1].getY()>= b[0].getY();
	}
	
	public boolean isPointOnLine(MyEdge a, MyPoint b) {
		MyPoint[] eP = a.getPoints();
		
		MyPoint left = eP[0].getPointLeft(eP[1]);
		MyPoint right = eP[0].getPointRight(eP[1]);
		MyPoint up = eP[0].getPointUp(eP[1]);
		MyPoint down = eP[0].getPointDown(eP[1]);
			
		MyEdge aTmp = new MyEdge(new MyPoint(0, 0), 
				                 new MyPoint(right.getX() - left.getX(), up.getY() - down.getY()));
	    MyPoint bTmp = new MyPoint(b.getX() - left.getX(), b.getY() - down.getY());
	    
	    double r = crossProduct(aTmp.getSecondPoint(), bTmp);
	    
	    return Math.abs(r) < EPSILON;
	}
  
	public boolean isPointRightOfLine(MyEdge a, MyPoint b) {
		MyPoint[] eP = a.getPoints();
		
		MyPoint left = eP[0].getPointLeft(eP[1]);
		MyPoint right = eP[0].getPointRight(eP[1]);
		MyPoint up = eP[0].getPointUp(eP[1]);
		MyPoint down = eP[0].getPointDown(eP[1]);
		
	    MyEdge aTmp = new MyEdge(new MyPoint(0, 0), new MyPoint(right.getX() - left.getX(), up.getY() - down.getY()));
	    MyPoint bTmp = new MyPoint(b.getX() - left.getX(), b.getY() - down.getY());
	    
	    return crossProduct(aTmp.getSecondPoint(), bTmp) < 0;
	}

	public boolean lineSegmentTouchesOrCrossesLine(MyEdge a, MyEdge b) {
	        return isPointOnLine(a, b.getFirstPoint()) || isPointOnLine(a, b.getSecondPoint())
	               || (isPointRightOfLine(a, b.getFirstPoint()) ^ isPointRightOfLine(a, b.getSecondPoint()));
	}
	
	public boolean doLinesIntersect(MyEdge a, MyEdge b) {
		MyPoint[] box1 = a.getBoundingBox();
		MyPoint[] box2 = b.getBoundingBox();
				
		return doBoundingBoxesIntersect(box1, box2)	&& lineSegmentTouchesOrCrossesLine(a, b)
			   && lineSegmentTouchesOrCrossesLine(b, a);
	}
	
	public MyEdge getIntersection(MyEdge e) {
		if(!doLinesIntersect(testLine,e)){
			return null;
		}		
		
	   int x1, y1, x2, y2;

	   if (a.getFirstPoint().getX()==a.getSecondPoint().getX()) { 
		   x1 = a.getFirstPoint().getX();
	       x2 = x1;
	       
	       if (b.getFirstPoint().getX()==a.getSecondPoint().getX()) {
	       
	            if(a.getFirstPoint().getY() > a.getSecondPoint().getY()) {
	                a = {"first": a["second"], "second": a["first"]};
	            }
	            
	            if(b["first"]["y"] > b["second"]["y"]) {
	                b = {"first": b["second"], "second": b["first"]};
	            }
	            
	            if(a["first"]["y"] > b["first"]["y"]) {
	                var tmp = a;
	                a = b;
	                b = tmp;
	            }

	            y1 = b["first"]["y"];
	            y2 = Math.min(a["second"]["y"], b["second"]["y"]);
	        } else {
	            var m, t;
	            m = (b["first"]["y"] - b["second"]["y"])/
	                (b["first"]["x"] - b["second"]["x"]);
	            t = b["first"]["y"] - m*b["first"]["x"];
	            y1 = m*x1 + t;
	            y2 = y1
	        }
	    } else if (b["first"]["x"] == b["second"]["x"]) {
	        x1 = b["first"]["x"];
	        x2 = x1;

	        var tmp = a;
	        a = b;
	        b = tmp;

	        var m, t;
	        m = (b["first"]["y"] - b["second"]["y"])/
	            (b["first"]["x"] - b["second"]["x"]);
	        t = b["first"]["y"] - m*b["first"]["x"];
	        y1 = m*x1 + t;
	        y2 = y1
	    } else {
	        // Case (C)
	        // Both lines can be represented mathematically
	        var ma, mb, ta, tb;
	        ma = (a["first"]["y"] - a["second"]["y"])/
	             (a["first"]["x"] - a["second"]["x"]);
	        mb = (b["first"]["y"] - b["second"]["y"])/
	             (b["first"]["x"] - b["second"]["x"]);
	        ta = a["first"]["y"] - ma*a["first"]["x"];
	        tb = b["first"]["y"] - mb*b["first"]["x"];
	        if (ma == mb) {
	            // Case (CA)
	            // both lines are in parallel. As we know that they 
	            // intersect, the intersection could be a line
	            // when we rotated this, it would be the same situation 
	            // as in case (AA)

	            // Normalize
	            if(a["first"]["x"] > a["second"]["x"]) {
	                a = {"first": a["second"], "second": a["first"]};
	            }
	            if(b["first"]["x"] > b["second"]["x"]) {
	                b = {"first": b["second"], "second": b["first"]};
	            }
	            if(a["first"]["x"] > b["first"]["x"]) {
	                var tmp = a;
	                a = b;
	                b = tmp;
	            }

	            // get the relavant x intervall
	            x1 = b["first"]["x"];
	            x2 = Math.min(a["second"]["x"], b["second"]["x"]);
	            y1 = ma*x1+ta;
	            y2 = ma*x2+ta;
	        } else {
	            x1 = (tb-ta)/(ma-mb);
	            y1 = ma*x1+ta;
	            x2 = x1;
	            y2 = y1;
	        }
	    }

	    return {"first": {"x":x1, "y":y1}, "second": {"x":x2, "y":y2}};
	}


}



    
  
}