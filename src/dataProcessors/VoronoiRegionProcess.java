package dataProcessors;

import java.util.ArrayList;
import java.util.Arrays;

import display.IWindow;

import utilities.MyCell;
import utilities.MyPoint;
import utilities.edges.InternalEdge;
import utilities.edges.MyEdge;
import utilities.edges.TriangleEdge;
import utilities.regions.MyRegion;
import utilities.triangles.MyTriangle;
import utilities.triangles.Triangle;

public class VoronoiRegionProcess {
	private ArrayList<InternalEdge> edgeList = new ArrayList<InternalEdge>();
	private ArrayList<MyCell> cellList = new ArrayList<MyCell>();
	private ArrayList<MyPoint> voronoiPoints;
	private ArrayList<Triangle> borderTriangles;
	private IWindow window;
	
	public VoronoiRegionProcess(String[] regions, MyPoint[] inputPoints, MyPoint[] voronoiP, MyRegion currentRegion, Triangle[] delaunay, IWindow w){
		window = w;
		voronoiPoints = new ArrayList<MyPoint>(Arrays.asList(voronoiP));
		borderTriangles = 	new ArrayList<Triangle>(Arrays.asList(delaunay));
	
		processRegions(regions,inputPoints,voronoiPoints,currentRegion);
	}

	private void processRegions(String[] regionList, MyPoint[] inputPoints, ArrayList<MyPoint> voronoiPoints, MyRegion region){					
		edgeList.add(new InternalEdge(-1,-1));
		cellList.add(new MyCell());
				
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
					
				InternalEdge newEdge;
				
				if(i2==0){
					ArrayList<Triangle> containers = findTriangle(voronoiPoints.get(i1),inputPoints[i]);
					ArrayList<TriangleEdge> nullEdges;			
					
					if(containers.size()==1){											
						nullEdges = containers.get(0).nullNeighbours();
					}else{						
						/*Caso particular en que estoy en una esquina que comparte dos triangulos de Delaunay*/				
						if(regInfo.length==3){
							MyPoint midPoint1 = containers.get(0).nullNeighbours().get(0).getMidPoint();
							MyPoint midPoint2 = containers.get(1).nullNeighbours().get(0).getMidPoint();
													
							if (!voronoiPoints.contains(midPoint1)) {
								voronoiPoints.add(midPoint1);
							}
							if (!voronoiPoints.contains(midPoint2)) {
								voronoiPoints.add(midPoint2);
							}
							if (!voronoiPoints.contains(inputPoints[i])) {
								voronoiPoints.add(inputPoints[i]);
							}					
							
							InternalEdge e1 = new InternalEdge(i1,voronoiPoints.indexOf(midPoint1));
							e1.setPoints(voronoiPoints.get(i1), midPoint1);
							edgeList.add(e1);

							InternalEdge e2 = new InternalEdge(voronoiPoints.indexOf(midPoint1),voronoiPoints.indexOf(inputPoints[i]));
							e2.setPoints(midPoint1,inputPoints[i]);
							edgeList.add(e2);

							InternalEdge e3 = new InternalEdge(voronoiPoints.indexOf(inputPoints[i]),voronoiPoints.indexOf(midPoint2));
							e3.setPoints(inputPoints[i], midPoint2);
							edgeList.add(e3);

							InternalEdge e4 = new InternalEdge(voronoiPoints.indexOf(midPoint2),i1);
							e4.setPoints(midPoint2,voronoiPoints.get(i1));
							edgeList.add(e4);
																		
							newCell.addEdge(edgeList.indexOf(e1), e1, 1);
							newCell.addEdge(edgeList.indexOf(e2), e2, 1);
							newCell.addEdge(edgeList.indexOf(e3), e3, 1);
							newCell.addEdge(edgeList.indexOf(e4), e4, 1);					

							newCell.setCenterPoint(inputPoints[i]);
							cellList.add(newCell);
		
							break;
						}else{
							MyTriangle container = chooseTriangleWithoutPoint(containers);
							nullEdges = container.nullNeighbours();
						}
					}
						
					if(nullEdges.size()==1){																	
						MyPoint midPoint = nullEdges.get(0).getMidPoint(); 
						infinityPoints.add(midPoint);
						
						if(!voronoiPoints.contains(midPoint)){
							voronoiPoints.add(midPoint);
						}
					
						newEdge = new InternalEdge(i1,voronoiPoints.indexOf(midPoint));
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
							
								newEdge = new InternalEdge(i1,voronoiPoints.indexOf(otherMidPoint));
								newEdge.setPoints(voronoiPoints.get(i1), otherMidPoint);
										
							}else{
								infinityPoints.add(midPoint);
								if(!voronoiPoints.contains(midPoint)){
									voronoiPoints.add(midPoint);
								}
								
								newEdge = new InternalEdge(i1,voronoiPoints.indexOf(midPoint));
								newEdge.setPoints(voronoiPoints.get(i1), midPoint);
							}	
						}else{
							if(nullEdges.get(0).inEdge(inputPoints[i])){
								infinityPoints.add(midPoint);
								if(!voronoiPoints.contains(midPoint)){
									voronoiPoints.add(midPoint);
								}
								
								newEdge = new InternalEdge(i1,voronoiPoints.indexOf(midPoint));
								newEdge.setPoints(voronoiPoints.get(i1), midPoint);
							}else{
								MyPoint otherMidPoint = nullEdges.get(1).getMidPoint();
								infinityPoints.add(otherMidPoint);
								
								if(!voronoiPoints.contains(otherMidPoint)){
									voronoiPoints.add(otherMidPoint);
								}
							
								newEdge = new InternalEdge(i1,voronoiPoints.indexOf(otherMidPoint));
								newEdge.setPoints(voronoiPoints.get(i1), otherMidPoint);
							}
						}
					}	
				}else if(i1==0){
					ArrayList<Triangle> containers = findTriangle(voronoiPoints.get(i2),inputPoints[i]);
					if(containers.size()==0){
						System.out.println("Algo salio muy mal con el punto " + voronoiPoints.get(i2));
						System.out.println("Y este input point  " + inputPoints[i]);
						System.out.println();
						
						for(int k=0;i<borderTriangles.size();k++){
							System.out.println(borderTriangles.get(k).toString() + " " + borderTriangles.get(k).nullNeighbours().size());
						}
					}
					
					ArrayList<TriangleEdge> nullEdges = containers.get(0).nullNeighbours();			
						
					if(nullEdges.size()==1){					
						MyPoint midPoint = nullEdges.get(0).getMidPoint();
						infinityPoints.add(midPoint);
						
						if(!voronoiPoints.contains(midPoint)){
							voronoiPoints.add(midPoint);
						}
											
						newEdge = new InternalEdge(voronoiPoints.indexOf(midPoint),i2);
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
							
								newEdge = new InternalEdge(voronoiPoints.indexOf(otherMidPoint),i2);
								newEdge.setPoints(otherMidPoint,voronoiPoints.get(i2));
										
							}else{
								infinityPoints.add(midPoint);
								if(!voronoiPoints.contains(midPoint)){
									voronoiPoints.add(midPoint);
								}
								
								newEdge = new InternalEdge(voronoiPoints.indexOf(midPoint),i2);
								newEdge.setPoints(midPoint,voronoiPoints.get(i2));
							}	
						}else{
							if(nullEdges.get(0).inEdge(inputPoints[i])){
								infinityPoints.add(midPoint);
								if(!voronoiPoints.contains(midPoint)){
									voronoiPoints.add(midPoint);
								}
								
								newEdge = new InternalEdge(voronoiPoints.indexOf(midPoint),i2);
								newEdge.setPoints(midPoint,voronoiPoints.get(i2));
							}else{
								MyPoint otherMidPoint = nullEdges.get(1).getMidPoint();
								infinityPoints.add(otherMidPoint);
								
								if(!voronoiPoints.contains(otherMidPoint)){
									voronoiPoints.add(otherMidPoint);
								}
							
								newEdge = new InternalEdge(voronoiPoints.indexOf(otherMidPoint),i2);
								newEdge.setPoints(otherMidPoint,voronoiPoints.get(i2));
							}
						}	
					}	
				}else{
					newEdge = new InternalEdge(i1,i2);
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
					
			if(infinityPoints.size()==2){
				InternalEdge closeEdge = new InternalEdge(voronoiPoints.indexOf(infinityPoints.get(0)),voronoiPoints.indexOf(infinityPoints.get(1)));
				closeEdge.setPoints(infinityPoints.get(0), infinityPoints.get(1));
				
				if(!closeEdge.inEdge(inputPoints[i])){				
					if(!voronoiPoints.contains(inputPoints[i])){
						voronoiPoints.add(inputPoints[i]);
					}				
					
					InternalEdge close1 = new InternalEdge(voronoiPoints.indexOf(infinityPoints.get(0)),voronoiPoints.indexOf(inputPoints[i]));
					close1.setPoints(infinityPoints.get(0), inputPoints[i]); 
					
					InternalEdge close2 = new InternalEdge(voronoiPoints.indexOf(inputPoints[i]),voronoiPoints.indexOf(infinityPoints.get(1)));
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
		edgeList.remove(new InternalEdge(-1,-1));
		InternalEdge[] edgeArray = new InternalEdge[edgeList.size()];
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
	
	private ArrayList<Triangle> findTriangle(MyPoint point, MyPoint inputPoint){
		double precision = window.getPrecision();
		ArrayList<Triangle> returnValues = new ArrayList<Triangle>();
		
		for(int i=0;i<borderTriangles.size();i++){			
			if(borderTriangles.get(i).contains(point,precision) && borderTriangles.get(i).hasNullNeighbours()
				&& borderTriangles.get(i).contains(inputPoint,precision)){
				returnValues.add(borderTriangles.get(i));
			}		
		}
			
		return returnValues;
	}
	
	private MyTriangle chooseTriangleWithoutPoint(ArrayList<Triangle> triangles){
		double precision = window.getPrecision();
		MyTriangle t1 = triangles.get(0);
		MyTriangle t2 = triangles.get(1);
		
		for(int i=0;i<voronoiPoints.size();i++){
			if(t1.contains(voronoiPoints.get(i), precision)){
				return t2;
			}
			
			if(t2.contains(voronoiPoints.get(i), precision)){
				return t1;
			}
		}
		
		return null;
	}
	
}
