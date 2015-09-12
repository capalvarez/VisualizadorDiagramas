package libraryCallers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.RandomStringUtils;

public abstract class LibraryCall {
	protected String inputFile;
	protected String outputFile;
	
	protected void createDelaunayOutputFile(){
		outputFile = "../data/DelaunayOutput" + RandomStringUtils.random(8, true, true);
	}
	
	protected void createVoronoiOutputFile(){
		outputFile = "../data/VoronoiOutput" + RandomStringUtils.random(8, true, true);
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
	
	protected void copyExeFile(String fileName, String newFileName) throws IOException{
		File file = new File(newFileName);
		FileOutputStream os = new FileOutputStream(file, false); 
		                                                                 
		InputStream is = new FileInputStream(fileName);		
		byte[] buffer = new byte[2048];
		int read;
	
		while ((read = is.read(buffer)) != -1)
		{
		    os.write(buffer, 0, read);
		}
		
		is.close();
		os.flush();
		os.close();	
	}
	
	protected void deleteExeFile(String fileName){
		new File(fileName).delete();
	}
}
