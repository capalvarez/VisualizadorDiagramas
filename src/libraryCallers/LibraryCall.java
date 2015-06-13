package libraryCallers;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;

public abstract class LibraryCall {
	protected String inputFile;
	protected String outputFile;
	
	protected void createDelaunayOutputFile(){
		outputFile = "./data/DelaunayOutput" + RandomStringUtils.random(8, true, true);
	}
	
	protected void createVoronoiOutputFile(){
		outputFile = "./data/VoronoiOutput" + RandomStringUtils.random(8, true, true);
	}

	protected void callBashMethod(ProcessBuilder pb) throws InterruptedException{
		pb.redirectErrorStream(true);
		
		try{
			Process shell = pb.start();
		    shell.waitFor();
	        System.out.println("Exit Status : " + shell.exitValue()); 
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
