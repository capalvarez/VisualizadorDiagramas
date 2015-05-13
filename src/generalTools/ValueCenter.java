package generalTools;

public class ValueCenter {
	int dimension;
	int[] valuesToCenter;
	int[] result;
	
	public ValueCenter(int dim, int[] values){
		valuesToCenter = values;
		dimension = dim;
		
		centerValues();
	}
	
	private void centerValues(){
		int midPoint = dimension/2;
		int diff = Math.abs(valuesToCenter[0] - valuesToCenter[1])/2;
	
		result = new int[2];
		
		result[0] = midPoint + diff;
		result[1] = midPoint - diff;
		
	}
	
	public int[] getCenteredValues(){
		return result;
	}
}
