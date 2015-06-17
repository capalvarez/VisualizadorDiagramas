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
	private ArrayList<MyTriangle> borderTriangles;
	
	public VoronoiRegionProcess(String[] regions, MyPoint[] inputPoints, MyPoint[] voronoiP, MyRegion currentRegion, MyTriangle[] delaunay){
		voronoiPoints = new ArrayList<MyPoint>(Arrays.asList(voronoiP));
		borderTriangles = 	new ArrayList<MyTriangle>(Arrays.asList(delaunay));
	
		processRegions(regions,inputPoints,voronoiPoints,currentRegion);
	}

	private void processRegions(String[] regionList, MyPoint[] inputPoints, ArrayList<MyPoint> voronoiPoints, MyRegion region){					
				
		for(int i=0; i<regionList.length;i++){
			String[] regInfo = regionList[i].split(" ");			
			MyCell newCell = new MyCell();
			ArrayList<MyPoint> infinityPoints = new ArrayList<MyPoint>();
						
			for(int j=1;j<=Integer.parseInt(regInfo[0]);j++){
				int i1,i2;
				
				if(j==Integer.parseInt(regInfo[0])){
					i1 = Integer.parseInt(regInfo[j]);
					i2 = Integer.parseInt(regInfo[1]);
				}else{
					i1 = Integer.parseInt(regInfo[j]);
					i2 = Integer.parseInt(regInfo[j+1]);
				}	
					
				MyEdge newEdge;
				
				if(i2==0){
					MyTriangle container = findTriangle(voronoiPoints.get(i1));
					ArrayList<TriangleEdge> nullEdges = container.nullNeighbours();				
					
					if(nullEdges.size()==1){					
						MyPoint midPoint = nullEdges.get(0).getMidPoint(); 
						infinityPoints.add(midPoint);
						
						if(!voronoiPoints.contains(midPoint)){
							voronoiPoints.add(midPoint);
						}
												
						newEdge = new MyEdge(i1,voronoiPoints.indexOf(midPoint));
						newEdge.setPoints(voronoiPoints.get(i1),midPoint);
					
					}else{
						MyPoint midPoint = nullEdges.get(0).getMidPoint();
												
						if(nullEdges.get(0).inEdge(inputPoints[i]) && nullEdges.get(1).inEdge(inputPoints[i])){
					
							if(infinityPoints.contains(midPoint)){
								MyPoint otherMidPoint = nullEdges.get(1).getMidPoint();
								infinityPoints.add(otherMidPoint);
								
								if(!voronoiPoints.contains(otherMidPoint)){
									voronoiPoints.add(otherMidPoint);
								}
							
								newEdge = new MyEdge(i1,voronoiPoints.indexOf(otherMidPoint));
								newEdge.setPoints(voronoiPoints.get(i1), otherMidPoint);
										
							}else{
								infinityPoints.add(midPoint);
								if(!voronoiPoints.contains(midPoint)){
									voronoiPoints.add(midPoint);
								}
								
								newEdge = new MyEdge(i1,voronoiPoints.indexOf(midPoint));
								newEdge.setPoints(voronoiPoints.get(i1), midPoint);
							}	
						}else{
							if(nullEdges.get(0).inEdge(inputPoints[i])){
								infinityPoints.add(midPoint);
								if(!voronoiPoints.contains(midPoint)){
									voronoiPoints.add(midPoint);
								}
								
								newEdge = new MyEdge(i1,voronoiPoints.indexOf(midPoint));
								newEdge.setPoints(voronoiPoints.get(i1), midPoint);
							}else{
								MyPoint otherMidPoint = nullEdges.get(1).getMidPoint();
								infinityPoints.add(otherMidPoint);
								
								if(!voronoiPoints.contains(otherMidPoint)){
									voronoiPoints.add(otherMidPoint);
								}
							
								newEdge = new MyEdge(i1,voronoiPoints.indexOf(otherMidPoint));
								newEdge.setPoints(voronoiPoints.get(i1), otherMidPoint);
							}
						}
					}	
				}else if(i1==0){
					MyTriangle container = findTriangle(voronoiPoints.get(i2));
					ArrayList<TriangleEdge> nullEdges = container.nullNeighbours();
					
					if(nullEdges.size()==1){					
						MyPoint midPoint = nullEdges.get(0).getMidPoint();
						infinityPoints.add(midPoint);
						
						if(!voronoiPoints.contains(midPoint)){
							voronoiPoints.add(midPoint);
						}
											
						newEdge = new MyEdge(voronoiPoints.indexOf(midPoint),i2);
						newEdge.setPoints(midPoint,voronoiPoints.get(i2));
						
					}else{
						MyPoint midPoint = nullEdges.get(0).getMidPoint();
						
						if(nullEdges.get(0).inEdge(inputPoints[i]) && nullEdges.get(1).inEdge(inputPoints[i])){
												
							if(infinityPoints.contains(midPoint)){
								MyPoint otherMidPoint = nullEdges.get(1).getMidPoint();
								infinityPoints.add(otherMidPoint);
								
								if(!voronoiPoints.contains(otherMidPoint)){
									voronoiPoints.add(otherMidPoint);
								}
							
								newEdge = new MyEdge(voronoiPoints.indexOf(otherMidPoint),i2);
								newEdge.setPoints(otherMidPoint,voronoiPoints.get(i2));
										
							}else{
								infinityPoints.add(midPoint);
								if(!voronoiPoints.contains(midPoint)){
									voronoiPoints.add(midPoint);
								}
								
								newEdge = new MyEdge(voronoiPoints.indexOf(midPoint),i2);
								newEdge.setPoints(midPoint,voronoiPoints.get(i2));
							}	
						}else{
							if(nullEdges.get(0).inEdge(inputPoints[i])){
								infinityPoints.add(midPoint);
								if(!voronoiPoints.contains(midPoint)){
									voronoiPoints.add(midPoint);
								}
								
								newEdge = new MyEdge(voronoiPoints.indexOf(midPoint),i2);
								newEdge.setPoints(midPoint,voronoiPoints.get(i2));
							}else{
								MyPoint otherMidPoint = nullEdges.get(1).getMidPoint();
								infinityPoints.add(otherMidPoint);
								
								if(!voronoiPoints.contains(otherMidPoint)){
									voronoiPoints.add(otherMidPoint);
								}
							
								newEdge = new MyEdge(voronoiPoints.indexOf(otherMidPoint),i2);
								newEdge.setPoints(otherMidPoint,voronoiPoints.get(i2));
							}
						}	
					}	
				}else{
					newEdge = new MyEdge(i1,i2);
					newEdge.setPoints(voronoiPoints.get(i1), voronoiPoints.get(i2));
				}			
						
				if(!edgeList.contains(newEdge)){					
					edgeList.add(newEdge);
					newCell.addEdge(edgeList.indexOf(newEdge),newEdge,1);
							
				}else{
					MyEdge oldEdge = edgeList.get(edgeList.indexOf(newEdge));
					int nDir = oldEdge.getNormalDir(newEdge);
							
					newCell.addEdge(edgeList.indexOf(newEdge), newEdge, nDir);
				}
			}
			if(infinityPoints.size()>0){
				MyEdge closeEdge = new MyEdge(voronoiPoints.indexOf(infinityPoints.get(0)),voronoiPoints.indexOf(infinityPoints.get(1)));
				closeEdge.setPoints(infinityPoints.get(0), infinityPoints.get(1));
				
				if(!closeEdge.inEdge(inputPoints[i])){
					voronoiPoints.add(inputPoints[i]);
					
					MyEdge close1 = new MyEdge(voronoiPoints.indexOf(infinityPoints.get(0)),voronoiPoints.indexOf(inputPoints[i]));
					close1.setPoints(infinityPoints.get(0), inputPoints[i]); 
					
					MyEdge close2 = new MyEdge(voronoiPoints.indexOf(inputPoints[i]),voronoiPoints.indexOf(infinityPoints.get(1)));
					close2.setPoints(inputPoints[i],infinityPoints.get(1));
					
					edgeList.add(close1);
					newCell.addEdge(edgeList.indexOf(close1), close1, 1);
					
					edgeList.add(close2);
					newCell.addEdge(edgeList.indexOf(close2), close2, 1);
										
				}else{
					edgeList.add(closeEdge);
					newCell.addEdge(edgeList.indexOf(closeEdge), closeEdge, 1);		
				}
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
	
	private MyTriangle findTriangle(MyPoint point){
		for(int i=0;i<borderTriangles.size();i++){			
			if(borderTriangles.get(i).contains(point) && borderTriangles.get(i).hasNullNeighbours()){
				return borderTriangles.get(i);
			}		
		}
		
		return null;
	}
}
