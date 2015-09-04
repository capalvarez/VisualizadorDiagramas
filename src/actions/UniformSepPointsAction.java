package actions;

import generalTools.PointGenerator;
import generalTools.RowPointGenerator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyPoint;
import utilities.MyRegion;
import utilities.MyScale;

import display.IWindow;

public class UniformSepPointsAction extends AbstractAction {
	private IWindow window;	
	
	public UniformSepPointsAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField deltaX = new JTextField(5);
		JTextField deltaY = new JTextField(5);
	
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		inputPanel.add(new JLabel("Separacion en X:"));
		
		JPanel xPanel = new JPanel();
		xPanel.add(deltaX);
		inputPanel.add(xPanel);
		
		inputPanel.add(new JLabel("Separacion en Y:"));
		JPanel yPanel = new JPanel();
		yPanel.add(deltaY);
		inputPanel.add(yPanel);
		
		final JCheckBox secondRow = new JCheckBox("Segunda fila desfasada");
		secondRow.setSelected(false);   
		inputPanel.add(secondRow);
		
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos uniforme por separacion", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			double dX = Double.parseDouble(deltaX.getText());
			double dY = Double.parseDouble(deltaY.getText());		
			
			MyRegion current = window.getCurrentRegion();
		
			int numX = (int)Math.floor(current.getWidth()/dX)+1;
			int numY = (int)Math.floor(current.getHeight()/dY)+1;			
			
			MyPoint[] pointArray;
			
			if(!secondRow.isSelected()){
				/*Generar los puntos*/
				pointArray = (new PointGenerator(numX,dX,numY,dY,current.getUpCorner(),current.getLeftCorner())).getPoints();
			}else{
				ArrayList<MyPoint> finalList = new ArrayList<MyPoint>();
				
				for(int i=0; i<numY;i++){
					double yValue = i*dY + current.getUpCorner(); 
					ArrayList<MyPoint> pointList;
					/*Generar los puntos*/
					if(i%2==0){
						/*Filas pares se comportan normalmente*/
						pointList = (new RowPointGenerator(current.getLeftCorner(),current.getRightCorner(),dX,yValue)).getPoints();	
					}else{
						double initX = current.getLeftCorner() + Math.floor(dX/2);
						double endX = current.getRightCorner() - Math.floor(dX/2);
						
						pointList = (new RowPointGenerator(initX,endX,dX,yValue)).getPoints();
						/*Al estar desfasada naturalmente no se incluyen los puntos de los bordes, asi que 
						 * hay que ponerlos a mano*/
						
						MyPoint initPoint = new MyPoint(current.getLeftCorner(),yValue);
						MyPoint endPoint = new MyPoint(current.getRightCorner(),yValue);
						
						pointList.add(initPoint);
						pointList.add(endPoint);
						
					}
					finalList.addAll(pointList);
				}
				
				pointArray = new MyPoint[finalList.size()];
				pointArray = finalList.toArray(pointArray);		
			}
						
			/*Dibujar los puntos y dejarlos guardados en la ventana*/
			window.drawPointsInPanel(pointArray,pointArray);
			window.repaint();
			
		}
		
	}

}
