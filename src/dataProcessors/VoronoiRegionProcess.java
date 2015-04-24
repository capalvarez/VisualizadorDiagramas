package dataProcessors;

import java.util.ArrayList;

import utilities.MyCell;
import utilities.MyEdge;
import utilities.MyPoint;

public class VoronoiRegionProcess {
	private ArrayList<MyEdge> edgeList = new ArrayList<MyEdge>();
	private ArrayList<MyCell> cellList = new ArrayList<MyCell>();
	
	public VoronoiRegionProcess(String[] regions, MyPoint[] inputPoints){
		processRegions(regions,inputPoints);
	}

	private void processRegions(String[] regionList,MyPoint[] inputPoints){		
		int count = 1;
		for(int i=0; i<regionList.length;i++){
			
			String[] regInfo = regionList[i].split(" ");			
			MyCell newCell = new MyCell();
			
			for(int j=1;j<Integer.parseInt(regInfo[0])-1;j++){
				MyEdge newEdge = new MyEdge(Integer.parseInt(regInfo[j]),Integer.parseInt(regInfo[j+1])); 
				
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
			
			MyEdge newEdge = new MyEdge(Integer.parseInt(regInfo[Integer.parseInt(regInfo[0])]),Integer.parseInt(regInfo[1])); 		
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
}
