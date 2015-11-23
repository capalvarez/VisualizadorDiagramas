package writers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;

import utilities.MyPoint;
import utilities.edges.MyEdge;
import utilities.perforations.Perforation;
import utilities.regions.MyRegion;

public class PolyFileWriter {
	private MyPoint[] points;
	private MyRegion region;
	
	public PolyFileWriter(MyPoint[] points,MyRegion region){
		this.points = points;
		this.region = region;
	}
	
	public String writeInFile() throws FileNotFoundException, UnsupportedEncodingException{
		String name = "../data/" + RandomStringUtils.random(8, true, true);
		String fileName = name + ".poly";
		
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		
		MyPoint[] regionPoints = region.getPoints();
		MyPoint[] perforationPoints = region.getPerforationPoints();
			
		int totalPoints = points.length + regionPoints.length + perforationPoints.length;
		
		/*Primera linea, por ahora se asume todo en cero*/
		writer.println(totalPoints + " 2 0 0" );
			
		/*Escribir uno a uno los puntos a procesar*/
		int i = 1;
		while(i<=points.length){
			writer.println(i + " " + points[i-1].toString());
			i++;
		}
		int j = 0;
		while(j<regionPoints.length){
			writer.println(i + " " + regionPoints[j].toString());
			i++;
			j++;
		}
		
		j=0;
		while(j<perforationPoints.length){
			writer.println(i + " " + perforationPoints[j].toString());
			i++;
			j++;
		}
		
		MyEdge[] regionEdges = region.getEdges();
		int regionTotal = regionEdges.length + perforationPoints.length;
		writer.println(regionTotal + " 0");
		
		i = 1;
		while(i<=regionEdges.length){
			int i1 = regionEdges[i-1].getIndexFirst() + points.length;
			int i2 = regionEdges[i-1].getIndexSecond() + points.length;
			
			writer.println(i + " " + i1 + " " + i2);
			i++;
		}
		
		j = 1;
		int perforationMod = perforationPoints.length; 
		int lengths = points.length + regionPoints.length;
		while(j<=perforationPoints.length){
			int k_0 = j%perforationMod;
			int k = (j+1)%perforationMod;
			
			if(k_0 == 0)
				k_0 = perforationMod;
					
			if(k == 0)
				k = perforationMod;
			
			int i1 = k_0 + lengths;
			int i2 = k + lengths;
			
			writer.println(i + " " + i1 + " " + i2);
			j++;
			i++;
		}
		
		ArrayList<Perforation> perforation = region.getPerforationList();
		
		writer.println(perforation.size());
		
		int k = 1;
		while(k<=perforation.size()){
			writer.println(k + " " + perforation.get(k-1).writeCenter());
			k++;
		}
		
		writer.close();
	
		return name;
	}		
}
