package utilities.regions;

import java.util.ArrayList;
import java.util.Iterator;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.perforations.Perforation;

public abstract class AbstractRegion implements MyRegion {
	ArrayList<Perforation> perforation = new ArrayList<Perforation>();
	protected MyScale scale;
	
	public void emptyPerforations(){
		perforation = new ArrayList<Perforation>();
	}
	
	public void addPerforation(Perforation p){
		perforation.add(p);
	}
	
	public ArrayList<Perforation> getPerforationList(){
		return perforation;
	}
	
	public void cleanPerforation(ArrayList<MyPoint> points){
		for(Perforation p: perforation){
			Iterator<MyPoint> it = points.iterator();
			
			while (it.hasNext()){
			    MyPoint point = it.next();
			    if (p.contains(point)){
			        it.remove();
			    }
			}
		}
	}
	
	public MyPoint[] pointListToArray(ArrayList<MyPoint> list){	
		MyPoint[] array = new MyPoint[list.size()];
		array = list.toArray(array);
		
		return array;
	}
	
	public MyPoint[] getPerforationPoints(){
		ArrayList<MyPoint> finalList = new ArrayList<MyPoint>();
			
		for(Perforation p: perforation){
			finalList.addAll(p.getPerforationPoints());
		}

		return pointListToArray(finalList);
	}

	protected MyPoint[] cleanOutsidePoints(MyPoint[] p){
		ArrayList<MyPoint> inside = new ArrayList<MyPoint>();
		
		for(int i=0;i<p.length;i++){
				
			if(isInside(p[i])){
				inside.add(p[i]);
			}
		}
		
		return pointListToArray(inside);
	}
	
	
}
