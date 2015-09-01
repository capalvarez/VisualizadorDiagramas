package generalTools;

import java.util.ArrayList;
import utilities.MyPoint;

public class RowPointGenerator {
	ArrayList<MyPoint> returnList = new ArrayList<MyPoint>();
	
	public RowPointGenerator(double iX, double eX, double dX, double yValue){
		double currentPos = iX;
		
		while(currentPos<=eX){
			returnList.add(new MyPoint(currentPos,yValue));
			
			currentPos = currentPos + dX;
		}
	}
	
	public ArrayList<MyPoint> getPoints(){
		return returnList;
	}
	
}
