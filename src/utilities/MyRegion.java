package utilities;

import java.awt.Graphics2D;

public class MyRegion {
	MyPoint upLeft;
	MyPoint downRight;
	
	public MyRegion(MyPoint pUL, MyPoint pDR){
		upLeft = pUL;
		downRight = pDR;
	}
	
	public void drawRegion(Graphics2D g2d){
        int height = Math.abs(upLeft.getY()- downRight.getY());
        int width = Math.abs(upLeft.getX()- downRight.getX());
       
        g2d.drawRect(upLeft.getX(),upLeft.getY(),width,height);
		
	}
	
	public int getHeight(){
		return upLeft.getY() - downRight.getY(); 
	}
	
	public int getWidth(){
		return downRight.getX() - upLeft.getX();
	}
	
	public int getLeftCorner(){
		return upLeft.getX();
	}
	
	public int getUpCorner(){
		return upLeft.getY();
	}
	
	
	
}
