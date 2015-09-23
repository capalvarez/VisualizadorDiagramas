package generalTools;

import utilities.MyPoint;
import utilities.regions.MyRegion;

public class OutsidePointsCleaner {
	
	public boolean inside(MyPoint p,MyRegion region){
		return region.isInside(p);			
	}	
}
