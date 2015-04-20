package utilities;

public class MyScale {
	private MyPoint upLeftPixel;
	private MyPoint upLeftReal;
	private MyPoint downRightPixel;
	private MyPoint downRightReal;
	
	public MyScale(MyPoint p1, MyPoint p2, MyPoint p3, MyPoint p4){
		upLeftReal = p1;
		upLeftPixel = p2;
		downRightReal = p3;
		downRightPixel = p4;
	}
	
	public MyPoint getPixelValue(MyPoint toChange){
		int xPix = upLeftPixel.getX() + Math.round((toChange.getX() - upLeftReal.getX())*(downRightPixel.getX() - upLeftPixel.getX())/(downRightReal.getX() - upLeftReal.getX()));
		int yPix = upLeftPixel.getY() + Math.round((toChange.getY() - upLeftReal.getY())*(downRightPixel.getY() - upLeftPixel.getY())/(downRightReal.getY() - upLeftReal.getY()));	
		
		return new MyPoint(xPix,yPix);
	
	}

}

