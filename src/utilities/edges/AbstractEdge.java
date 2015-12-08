package utilities.edges;

import java.util.ArrayList;

import utilities.MyPoint;
import utilities.perforations.Perforation;
import utilities.triangles.MyTriangle;
import utilities.triangles.Triangle;

public abstract class AbstractEdge implements MyEdge {
	protected int i1;
	protected int i2;
	private MyPoint p1;
	private MyPoint p2;
	protected ArrayList<MyTriangle> triangles = new ArrayList<MyTriangle>();

	public AbstractEdge() {
		super();
	}

	public void setPoints(MyPoint p1, MyPoint p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public ArrayList<MyTriangle> getTriangles() {
		return triangles;
	}

	public MyPoint[] getPoints() {
		MyPoint[] points = new MyPoint[2];
		points[0] = p1;
		points[1] = p2;
				
		return points;
	}

	public int getIndexFirst() {
		return i1;
	}

	public int getIndexSecond() {
		return i2;
	}

	public MyPoint getFirstPoint() {
		return p1;
	}

	public MyPoint getSecondPoint() {
		return p2;
	}

	/* (non-Javadoc)
	 * @see utilities.edges.MyEdge#toString()
	 */
	@Override
	public String toString() {
		return this.i1+" "+this.i2 + " " + getNormal();
	}

	public String getNormal() {
		
		double norm = Math.sqrt(Math.pow((p2.getX() - p1.getX()),2) + Math.pow((p2.getY() - p1.getY()),2));
		if(norm==0){
			System.out.println("norma cero!");
		}
		
		double coord1 = -(p2.getY() - p1.getY())/norm;
		double coord2 = (p2.getX() - p1.getX())/norm;
		
		return coord1 + " " + coord2;
	
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof InternalEdge){
			MyEdge e2 = (MyEdge)o;
		
			return (i1==e2.getIndexFirst() && i2==e2.getIndexSecond()) ||
				   (i2==e2.getIndexFirst() && i1==e2.getIndexSecond()); 		
		}else{
			return false;
		}
	}

	public int getNormalDir(AbstractEdge e) {
		if(e.p1.equals(this.p1) && e.p2.equals(this.p2)){
			return 1;
		}else{
			return -1;
		}
	}

	public boolean inEdge(MyPoint p) {
		double res = p1.getX()*(p2.getY()-p.getY()) + p2.getX()*(p.getY()-p1.getY()) + p.getX()*(p1.getY()-p2.getY()); 
	
		return Math.abs(res)<0.00001;
	}

	public ArrayList<MyPoint> intersectionPoint(Perforation p) {
			return p.intersectionPoints(p1, p2);
	}

	public MyPoint getMidPoint(){
		return new MyPoint((p1.getX() + p2.getX())/2,(p1.getY() + p2.getY())/2); 
	}
	
	public MyPoint getPoint(int index){
		if(index==0){
			return getFirstPoint();
		}
		
		return getSecondPoint();
	}
}