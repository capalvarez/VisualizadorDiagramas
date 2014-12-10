package libraryCallers;

import java.io.IOException;

public class TestCall {

	public static void main(String[] args) throws IOException, InterruptedException{
		VoronoiLibraryCall v = new VoronoiLibraryCall("input");
		v.callSystem();
	}
	
}
