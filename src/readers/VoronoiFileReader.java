package readers;

public interface VoronoiFileReader {
	
	public int getInputPointNumber();
	public int getDimension();
	/*Devolver strings[] o un formato mas estandar?*/
	public String[] getPointList();
	public String[] getRegionList();

}
