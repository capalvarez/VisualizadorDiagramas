package dataProcessors;

import utilities.MyPoint;

public abstract class PointProcess {
	protected MyPoint[] endPoints;
	protected MyPoint[] initPointsList;
	protected int wHeight;
	protected int wWidth;
	
	public MyPoint[] getPointList(){
		return endPoints;
	}

	public MyPoint[] getInitPointsList(){
		return initPointsList;
	}
}
