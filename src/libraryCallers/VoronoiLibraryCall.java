package libraryCallers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.RandomStringUtils;

public class VoronoiLibraryCall {
	private String inputFile;
	private String outputFile;
	
	public VoronoiLibraryCall(String input){
		inputFile = input;
		createOutputFile();
	}
		
	private void createOutputFile(){
		outputFile = "output" + RandomStringUtils.random(8, true, true);
	}
	
	private void copyToOutput(InputStream in) throws IOException {
	    byte[] buffer = new byte[in.available()];
	    in.read(buffer);
	 
	    File targetFile = new File(outputFile);
	    OutputStream outStream = new FileOutputStream(targetFile);
	    outStream.write(buffer);
	    outStream.close();
	}
		
	public String callSystem() throws IOException, InterruptedException{
		ProcessBuilder pb = new ProcessBuilder("qvoronoi","o","TI",inputFile);
		pb.redirectErrorStream(true);
		
		try{
			Process shell = pb.start();
		    copyToOutput(shell.getInputStream());
		    shell.waitFor();
	        System.out.println("Exit Status : " + shell.exitValue()); 
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return outputFile;
	}
	
}
