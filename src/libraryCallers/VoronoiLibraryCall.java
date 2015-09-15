package libraryCallers;

import java.io.IOException;

import org.apache.commons.lang3.SystemUtils;

public class VoronoiLibraryCall extends LibraryCall{

	public VoronoiLibraryCall(String input){
		inputFile = input;
		createVoronoiOutputFile();
	}
			
	public String callSystem() throws IOException, InterruptedException{
		if(SystemUtils.IS_OS_WINDOWS){
			copyExeFile("./resources/qvoronoi.exe","../resources/qvoronoi.exe");
			ProcessBuilder pb = new ProcessBuilder("../resources/qvoronoi.exe","o","TI",inputFile,"TO",outputFile);
			callBashMethod(pb);
			deleteExeFile("../resources/qvoronoi.exe");
			
		}else{
			ProcessBuilder pb = new ProcessBuilder("qvoronoi","o","TI",inputFile,"TO",outputFile);
			callBashMethod(pb);
		}
				
		return outputFile;
	}
	
}
