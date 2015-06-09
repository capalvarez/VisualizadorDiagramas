package dataProcessors;

import java.util.ArrayList;
import java.util.Arrays;

import utilities.MyCell;
import utilities.MyEdge;
import utilities.MyPoint;
import utilities.MyRegion;
import utilities.MyTriangle;
import utilities.TriangleEdge;

public class VoronoiRegionProcess {
	private ArrayList<MyEdge> edgeList = new ArrayList<MyEdge>();
	private ArrayList<MyCell> cellList = new ArrayList<MyCell>();
	private ArrayList<MyPoint> voronoiPoints;
	private MyTriangle[] delaunayTriangles;
	
	public VoronoiRegionProcess(String[] regions, MyPoint[] inputPoints, MyPoint[] voronoiP, MyRegion currentRegion, MyTriangle[] delaunay){
		voronoiPoints = new ArrayList<MyPoint>(Arrays.asList(voronoiP));
		delaunayTriangles = delaunay;
		
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
					
				MyEdge newEdge;
				
				if(i2==0){
					MyTriangle container = findTriangle(delaunayTriangles,inputPoints[i]);
					ArrayList<TriangleEdge> nullEdges = container.nullNeighbours();
					
					if(nullEdges.size()==1){					
						newEdge = new MyEdge(voronoiPoints.get(i1),nullEdges.get(0).getMidPoint());
						voronoiPoints.add(nullEdges.get(0).getMidPoint());
					
					}else{
						newEdge = null;
					}
					
				}else{
					newEdge = new MyEdge(i1,i2);
					newEdge.setPoints(voronoiPoints.get(i1), voronoiPoints.get(i2));
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
			
			/*revisar aqui, falta*/
			
			
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
	
	private MyTriangle findTriangle(MyTriangle[] triangles, MyPoint point){
		return null;
	}
}
