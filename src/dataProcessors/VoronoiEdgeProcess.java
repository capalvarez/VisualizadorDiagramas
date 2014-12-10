package dataProcessors;

import java.util.ArrayList;

public class VoronoiEdgeProcess {
	private ArrayList<String> edgeList = new ArrayList<String>();
	
	public VoronoiEdgeProcess(String[] regions){
		processRegions(regions);
	}

	private void processRegions(String[] regionList){
		for(int i=0; i<regionList.length;i++){
			String[] regInfo = regionList[i].split(" ");
			
			for(int j=1;j<Integer.parseInt(regInfo[0])-1;j++){
				edgeList.add(regInfo[j]+" "+regInfo[j+1]);
			}
			
			edgeList.add(regInfo[Integer.parseInt(regInfo[0])]+" "+regInfo[0]);		
		}
	}
	
	public String[] getEdgeList(){
		String[] edgeArr = new String[edgeList.size()];
		edgeArr = edgeList.toArray(edgeArr);
		
		return edgeArr;
	}
}
