package libraryCallers;

import java.io.IOException;

public class DelaunayLibraryCall extends LibraryCall{

	public DelaunayLibraryCall(String input){
		inputFile = input;
		createDelaunayOutputFile();
	}
			
	public String callSystem() throws IOException, InterruptedException{
		//Cambiar al comando para llamar a qdelaunay
		ProcessBuilder pb = new ProcessBuilder("qdelaunay","i","Fn","Qt","TI",inputFile,"TO",outputFile);
		callBashMethod(pb);
				
		return outputFile;
	}
}
