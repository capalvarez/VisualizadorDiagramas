package utilities;

import java.util.ArrayList;

public class MyCell {
	private ArrayList<Integer> edgeIndexList = new ArrayList<Integer>();
	private ArrayList<MyEdge> edges = new ArrayList<MyEdge>();
	private ArrayList<Integer> normalDir = new ArrayList<Integer>();
	MyPoint centerPoint;
	
	public MyCell(){}
	
	public void addEdge(int edgeIndex, MyEdge edge, int norm){
		edgeIndexList.add(edgeIndex);
		edges.add(edge);
		normalDir.add(norm);
	}
	
	@Override
	public String toString(){
		String result = edges.size() + " ";
		
		for(int i = 0;i<edges.size();i++){		
			result = result + edgeIndexList.get(i) + " ";
		}
		
		for(int i = 0;i<normalDir.size();i++){
			result = result + normalDir.get(i) + " ";
		}
				
		result = result + getArea();
		
		return result;	
	}
	
	public double getArea(){
		double[] subAreas = getAreaEdges();
		double sum = 0;
		
		for(int i=0;i<subAreas.length;i++){
			sum = sum + subAreas[i];
		}
		
		return sum;	
	}
	
	public double[] getAreaEdges(){
		double[] res = new double[edges.size()];
		
		for(int i=0;i<res.length;i++){
			MyPoint[] p = edges.get(i).getPoints();
			res[i] = getTriangleArea(p[0],p[1],centerPoint);
		}
		
		return res;
	}
	
	public double getTriangleArea(MyPoint pA, MyPoint pB, MyPoint pC){
		return Math.abs((pA.getX() - pC.getX())*(pB.getY() - pA.getY()) - (pA.getX() - pB.getX())*(pC.getY() - pA.getY()))/2;
	}
		
	public void setCenterPoint(MyPoint center){
		centerPoint = center;
	}	
}
