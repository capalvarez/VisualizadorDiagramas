package utilities;

import display.IWindow;

public class MyScale {
	private MyPoint upLeftPixel;
	private MyPoint upLeftReal;
	private MyPoint downRightPixel;
	private MyPoint downRightReal;
	private IWindow window;
	
	public MyScale(MyPoint p1, MyPoint p2, MyPoint p3, MyPoint p4, IWindow w){
		upLeftReal = p1;
		upLeftPixel = p2;
		downRightReal = p3;
		downRightPixel = p4;
		window = w;
	}
	
	public MyPoint getPixelValue(MyPoint toChange){
		double xPix = upLeftPixel.getX() + (toChange.getX() - upLeftReal.getX())*(downRightPixel.getX() - upLeftPixel.getX())/(downRightReal.getX() - upLeftReal.getX());
		double yPix = window.getHeight()*0.9 - (upLeftPixel.getY() + (toChange.getY() - upLeftReal.getY())*(downRightPixel.getY() - upLeftPixel.getY())/(downRightReal.getY() - upLeftReal.getY()));	
	
		return new MyPoint(xPix,yPix);
	}
	
	public double getPixelValue(double value){
		double newValue = value/(downRightReal.getX() - upLeftReal.getX())*(downRightPixel.getX() - upLeftPixel.getX());

		return newValue;
	}
	
	public void printScale(){
		System.out.println("Real: " + upLeftReal.toString() + "         En pixeles: " + upLeftPixel.toString());
		System.out.println("Real: " + downRightReal.toString() + "         En pixeles: " + downRightPixel.toString());
	}

}

