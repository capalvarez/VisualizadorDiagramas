package dataProcessors;

import java.util.ArrayList;
import java.util.Arrays;

import utilities.MyCell;
import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyRegion;

public class VoronoiRegionProcess {
	private ArrayList<MyEdge> edgeList = new ArrayList<MyEdge>();
	private ArrayList<MyCell> cellList = new ArrayList<MyCell>();
	private ArrayList<MyPoint> voronoiPoints;
	
	public VoronoiRegionProcess(String[] regions, MyPoint[] inputPoints, MyPoint[] voronoiP, MyRegion currentRegion){
		voronoiPoints = new ArrayList<MyPoint>(Arrays.asList(voronoiP));
		processRegions(regions,inputPoints,voronoiPoints, currentRegion);
	}

	private void processRegions(String[] regionList, MyPoint[] inputPoints, ArrayList<MyPoint> voronoiPoints, MyRegion region){					
		int count = 1;
		for(int i=0; i<regionList.length;i++){
			
			String[] regInfo = regionList[i].split(" ");			
			MyCell newCell = new MyCell();
			
			for(int j=1;j<Integer.parseInt(regInfo[0])-1;j++){
				int i1 = Integer.parseInt(regInfo[j]);
				int i2 = Integer.parseInt(regInfo[j+1]);
							
				if(i1==0){
					
				}
				
				MyEdge newEdge = new MyEdge(i1,i2);
				newEdge.setPoints(voronoiPoints.get(i1), voronoiPoints.get(i2));
											
				MyPoint interPoint = region.getIntersection(newEdge); 
				
				if(interPoint!=null){
					int insideIndex = region.getInsidePoint(newEdge);
					voronoiPoints.add(interPoint);
					
					if(insideIndex==0){
						newEdge = new MyEdge(i1,voronoiPoints.indexOf(interPoint));						
						newEdge.setPoints(voronoiPoints.get(i1), interPoint);
					
					}else{
						newEdge = new MyEdge(voronoiPoints.indexOf(interPoint),i2);						
						newEdge.setPoints(voronoiPoints.get(i1), interPoint);
					}	 
				}
				
				if(!edgeList.contains(newEdge)){
					edgeList.add(newEdge);
					newCell.addEdge(count,newEdge,1);
					
					count++;					
				}else{
					MyEdge oldEdge = edgeList.get(edgeList.indexOf(newEdge));
					int nDir = oldEdge.getNormalDir(newEdge);
							
					newCell.addEdge(edgeList.indexOf(newEdge), newEdge, nDir);
				}
			}
						 		
			int i1 = Integer.parseInt(regInfo[Integer.parseInt(regInfo[0])]);
			int i2 = Integer.parseInt(regInfo[1]);
					
			MyEdge newEdge = new MyEdge(i1,i2);
			newEdge.setPoints(voronoiPoints.get(i1), voronoiPoints.get(i2));
			
			MyPoint interPoint = region.getIntersection(newEdge); 
			
			if(interPoint!=null){
				int insideIndex = region.getInsidePoint(newEdge);
				voronoiPoints.add(interPoint);
				
				if(insideIndex==0){
					newEdge = new MyEdge(i1,voronoiPoints.indexOf(interPoint));						
					newEdge.setPoints(voronoiPoints.get(i1), interPoint);
				
				}else{
					newEdge = new MyEdge(voronoiPoints.indexOf(interPoint),i2);						
					newEdge.setPoints(voronoiPoints.get(i1), interPoint);
				}
				 
			}
			
			if(!edgeList.contains(newEdge)){
				edgeList.add(newEdge);
				newCell.addEdge(count,newEdge,1);
				
				count++;					
			}else{
				MyEdge oldEdge = edgeList.get(edgeList.indexOf(newEdge));
				int nDir = oldEdge.getNormalDir(newEdge);
						
				newCell.addEdge(edgeList.indexOf(newEdge), newEdge, nDir);
			}
			
			newCell.setCenterPoint(inputPoints[i]);
			cellList.add(newCell);
		}
	}
	
	public MyEdge[] getEdgeList(){	
		MyEdge[] edgeArray = new MyEdge[edgeList.size()];
		edgeArray = edgeList.toArray(edgeArray);
		
		return edgeArray;
	}
	
	public MyCell[] getCellList(){	
		MyCell[] cellArray = new MyCell[cellList.size()];
		cellArray = cellList.toArray(cellArray);
		
		return cellArray;
	}
	
	public MyPoint[] getPointList(){
		MyPoint[] pointArray = new MyPoint[voronoiPoints.size()];
		pointArray = voronoiPoints.toArray(pointArray);
		
		return pointArray;
	}
}
