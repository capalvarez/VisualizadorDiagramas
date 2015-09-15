package libraryCallers;

import java.io.IOException;

import org.apache.commons.lang3.SystemUtils;

public class DelaunayLibraryCall extends LibraryCall{

	public DelaunayLibraryCall(String input){
		inputFile = input;
		createDelaunayOutputFile();
	}
			
	public String callSystem() throws IOException, InterruptedException{
		
		if(SystemUtils.IS_OS_WINDOWS){
			copyExeFile("./resources/qdelaunay.exe","../resources/qdelaunay.exe");
			ProcessBuilder pb = new ProcessBuilder("../resources/qdelaunay.exe","i","Fn","Qt","TI",inputFile,"TO",outputFile);
			callBashMethod(pb);
			deleteExeFile("../resources/qdelaunay.exe");	
		}else{
			ProcessBuilder pb = new ProcessBuilder("qdelaunay","i","Fn","Qt","TI",inputFile,"TO",outputFile);
			callBashMethod(pb);
		}
						
		return outputFile;
	}
}
