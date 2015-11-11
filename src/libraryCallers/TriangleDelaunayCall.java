package libraryCallers;

import java.io.IOException;

public class TriangleDelaunayCall extends LibraryCall {
	
	public TriangleDelaunayCall(String input){
		inputFile = input;
		createDelaunayOutputFile();
	}
			
	public String callSystem() throws IOException, InterruptedException{
		
		ProcessBuilder pb = new ProcessBuilder("./triangle","-p","-n",inputFile);
		callBashMethod(pb);	
						
		return outputFile;
	}

}
