package dataProcessors;

import java.util.ArrayList;

import utilities.MyEdge;

public class VoronoiRegionProcess {
	private ArrayList<MyEdge> edgeList = new ArrayList<MyEdge>();
	private ArrayList<String> edgeIndex = new ArrayList<String>();
	private ArrayList<String> regionIndex = new ArrayList<String>();
	
	public VoronoiRegionProcess(String[] regions){
		processRegions(regions);
	}

	private void processRegions(String[] regionList){		
		for(int i=0; i<regionList.length;i++){
			
			String[] regInfo = regionList[i].split(" ");
			
			for(int j=1;j<Integer.parseInt(regInfo[0])-1;j++){
				edgeList.add(new MyEdge(Integer.parseInt(regInfo[j]),Integer.parseInt(regInfo[j+1])));
				edgeIndex.add(regInfo[j] + " " + regInfo[j+1]);
				
			}
			
			edgeList.add(new MyEdge(Integer.parseInt(regInfo[Integer.parseInt(regInfo[0])]),Integer.parseInt(regInfo[1])));		
			edgeIndex.add(regInfo[0] + " " + regInfo[1]);
			regionIndex.add(Integer.parseInt(regInfo[0])+1 + " " );
			
		}
	}
	
	public MyEdge[] getEdgeList(){	
		MyEdge[] edgeArray = new MyEdge[edgeList.size()];
		edgeArray = edgeList.toArray(edgeArray);
		
		return edgeArray;
	}
	
	public String[] getEdgeIndexList(){
		String[] edgeIndexArray = new String[edgeIndex.size()];
		edgeIndexArray = edgeIndex.toArray(edgeIndexArray);
		
		return edgeIndexArray;
		
	}
}
