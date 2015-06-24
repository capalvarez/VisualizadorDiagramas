package generalTools;

public class ValueCenter {
	int dimension;
	double[] valuesToCenter;
	double[] result;
	
	public ValueCenter(int dim, double[] values){
		valuesToCenter = values;
		dimension = dim;
		
		centerValues();
	}
	
	private void centerValues(){
		int midPoint = dimension/2;
		double diff = Math.abs(valuesToCenter[0] - valuesToCenter[1])/2;
	
		result = new double[2];
		
		result[0] = midPoint + diff;
		result[1] = midPoint - diff;
		
	}
	
	public double[] getCenteredValues(){
		return result;
	}
}
