package utilities;

import java.awt.Graphics2D;

public class MyRegion {
	MyPoint upLeftReal;
	MyPoint downRightReal;
	MyPoint upLeftPixel;
	MyPoint downRightPixel;
		
	public MyRegion(MyPoint pUL, MyPoint pDR){
		upLeftReal = pUL;
		downRightReal = pDR;
	}
	
	public void setPixelValues(MyPoint uL, MyPoint dR){
		upLeftPixel = uL;
		downRightPixel = dR;
	}
	
	public void drawRegion(Graphics2D g2d){
        int height = Math.abs(upLeftPixel.getY()- downRightPixel.getY());
        int width = Math.abs(upLeftPixel.getX()- downRightPixel.getX());
       
        g2d.drawRect(upLeftPixel.getX(),upLeftPixel.getY(),width,height);
		
	}
	
	public int getHeight(){
		return Math.abs(upLeftReal.getY() - downRightReal.getY()); 
	}
	
	public int getWidth(){
		return downRightReal.getX() - upLeftReal.getX();
	}
	
	public int getLeftCorner(){
		return upLeftReal.getX();
	}
	
	public int getUpCorner(){
		return upLeftReal.getY();
	}
	
	
	
}
