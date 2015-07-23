package generalTools;

import utilities.MyPoint;
import utilities.MyRegion;

public class OutsidePointsCleaner {
	
	public boolean inside(MyPoint p,MyRegion region){
		return p.getX()<=region.getRightCorner() && p.getY()<=region.getUpCorner() && 
			   p.getX()>=region.getLeftCorner() && p.getY()>=region.getDownCorner();
	}

	public static void main(String[] args){
		MyPoint p = new MyPoint(120,120);
		MyPoint p2 = new MyPoint(-20,-20);
		OutsidePointsCleaner opc = new OutsidePointsCleaner();
		
		//System.out.println(opc.inside(p,100,100));
		//System.out.println(opc.inside(p2,100,100));
	}
	
}
