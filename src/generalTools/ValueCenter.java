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
		
		System.out.println(valuesToCenter[0]+ " "+valuesToCenter[1]);
		System.out.println(midPoint);
		
		result = new int[2];
		
		result[0] = midPoint + diff;
		result[1] = midPoint - diff;
		
		System.out.println(result[0]+" "+result[1]);
		System.out.println(midPoint);
	
	}
	
	public int[] getCenteredValues(){
		return result;
	}
}
