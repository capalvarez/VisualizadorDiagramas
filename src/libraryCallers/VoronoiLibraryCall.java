package libraryCallers;

import java.io.IOException;

public class VoronoiLibraryCall extends LibraryCall{

	public VoronoiLibraryCall(String input){
		inputFile = input;
		createVoronoiOutputFile();
	}
			
	public String callSystem() throws IOException, InterruptedException{
		ProcessBuilder pb = new ProcessBuilder("qvoronoi","o","TI",inputFile,"TO",outputFile);
		callBashMethod(pb);
				
		return outputFile;
	}
	
}
