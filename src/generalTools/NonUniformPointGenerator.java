package generalTools;

import java.util.ArrayList;

import utilities.MyPoint;

public class NonUniformPointGenerator {
	MyPoint[] points;
	
	public NonUniformPointGenerator(double iX,double mX,double iY,double mY,double width,double height){		
		ArrayList<Double> xValues = new ArrayList<Double>();
		double currentX=0;
		
		for(int i=0;currentX<=width;i++){
			xValues.add(currentX);	
			
			if(mX>=1){
				currentX = currentX + iX*mX*i;
			}else{
				currentX = currentX + iX*mX/i;
			}
			
		}
		
		ArrayList<Double> yValues = new ArrayList<Double>();
		double currentY=0;
		
		for(int i=0;currentY<height;i++){
			yValues.add(currentY);	
			if(mY>=1){
				currentY = currentY + iY*mY*i;
			}else{
				currentY = currentY + iY*mY/i;
			}
		}
		
		/*Revisar si es necesario agregar puntos en los bordes*/
		
		if(currentY==height && currentX==width){
			points = new MyPoint[yValues.size()*xValues.size()];
			
			int global = 0;
			
			for(int i=0;i<yValues.size();i++){
				for(int j=0;j<xValues.size();j++){
					points[global] = new MyPoint(xValues.get(j),yValues.get(i));
					global++;
				}
			}
		}else{
			if(currentY==height){
				points = new MyPoint[yValues.size()*xValues.size() + yValues.size()];
				int global = 0;
				
				for(int i=0;i<yValues.size();i++){
					for(int j=0;j<xValues.size();j++){
						points[global] = new MyPoint(xValues.get(j),yValues.get(i));
						global++;
					}
				}
							
				for(int i=0;i<yValues.size();i++){
					points[global] = new MyPoint(width,yValues.get(i));
					global++;
				}
				
			}	
			
			if(currentX==width){
				points = new MyPoint[yValues.size()*xValues.size() + xValues.size()];
				int global = 0;
				
				for(int i=0;i<yValues.size();i++){
					for(int j=0;j<xValues.size();j++){
						points[global] = new MyPoint(xValues.get(j),yValues.get(i));
						global++;
					}
				}
							
				for(int i=0;i<xValues.size();i++){
					points[global] = new MyPoint(xValues.get(i),height);
					global++;
				}
			}
			
			if(currentX!=width && currentY!=height){

				points = new MyPoint[yValues.size()*xValues.size() + yValues.size() + xValues.size() + 1];
				int global = 0;
				
				for(int i=0;i<yValues.size();i++){
					for(int j=0;j<xValues.size();j++){
						points[global] = new MyPoint(xValues.get(j),yValues.get(i));
						global++;
					}
				}
				
				for(int i=0;i<xValues.size();i++){
					points[global] = new MyPoint(xValues.get(i),height);
					global++;
				}
				
				for(int i=0;i<yValues.size();i++){
					points[global] = new MyPoint(width,yValues.get(i));
					global++;
				}
			
				points[global] = new MyPoint(width,height);
				
			}
		}	
	}

	
	public MyPoint[] getPoints(){
		return points;
	}

}
