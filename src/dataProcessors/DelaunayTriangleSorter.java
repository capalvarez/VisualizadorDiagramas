package dataProcessors;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import utilities.MyPoint;
import utilities.MyTriangle;

public class DelaunayTriangleSorter {
	
	public DelaunayTriangleSorter(MyTriangle[] delaunay){
		sortTriangles(delaunay);
	}
	
	private void sortTriangles(MyTriangle[] triangles){		
		for(int i=0;i<triangles.length;i++){
			sortPoints(triangles[i]);
		}	
	}
	
	private void sortPoints(MyTriangle t){
		ArrayList<MyPoint> points = t.getPoints();
		MyPoint centroid = getCentroid(t.getPoints());
		
		List<Map.Entry<Double,MyPoint>> list = new ArrayList<Map.Entry<Double,MyPoint>>();
		
		for(MyPoint p: points){
			double angle = Math.atan2(p.getX() - centroid.getX(),p.getY() - centroid.getY());
			list.add(new AbstractMap.SimpleImmutableEntry<>((Double)angle, p));
		}
		
		sortList(list);
		ArrayList<MyPoint> orderedPoints = new ArrayList<MyPoint>();
		
		for(int i=0;i<list.size();i++){
			orderedPoints.add(list.get(i).getValue());
		}
	
		t.setPoints(orderedPoints);
	}
	
	private void sortList(List<Map.Entry<Double,MyPoint>> list){
		Collections.sort(list, new Comparator<Map.Entry<Double, MyPoint>>() {
			@Override
			public int compare(Map.Entry<Double, MyPoint> r1,
					Map.Entry<Double, MyPoint> r2) {
				if (r1.getKey() < r2.getKey()) {
					return -1;
				} else if (r1.getKey() > r2.getKey()) {
					return 1;
				} else {
					return 0;
				}

			}
		});
	}
		
	private MyPoint getCentroid(ArrayList<MyPoint> points){
		double centroidX = 0, centroidY = 0;

        for(MyPoint p : points) {
            centroidX += p.getX();
            centroidY += p.getY();
        }
        
        return new MyPoint(centroidX/points.size(), centroidY/points.size());
	}

}
