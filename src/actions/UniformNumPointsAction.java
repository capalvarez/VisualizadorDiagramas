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

import display.IWindow;

public class UniformNumPointsAction extends AbstractAction {
	private IWindow window;	
	
	public UniformNumPointsAction(IWindow w){
		window = w;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField numX = new JTextField(5);
		JTextField numY = new JTextField(5);
	
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		inputPanel.add(new JLabel("Numero de puntos en X:"));
		
		JPanel xPanel = new JPanel();
		xPanel.add(numX);
		inputPanel.add(xPanel);
		
		inputPanel.add(new JLabel("Numero de puntos en Y:"));
		JPanel yPanel = new JPanel();
		yPanel.add(numY);
		inputPanel.add(yPanel);
		
		final JCheckBox secondRow = new JCheckBox("Segunda fila desfasada");
		secondRow.setSelected(false);   
		inputPanel.add(secondRow);
		
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos uniforme por numero", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			int nX = Integer.parseInt(numX.getText());
			int nY = Integer.parseInt(numY.getText());	
			
			MyRegion current = window.getCurrentRegion();
			double dX = current.getWidth()/(nX-1);
			double dY = current.getHeight()/(nY-1);
			MyPoint[] pointArray;
			
			if(!secondRow.isSelected()){				
				/*Generar los puntos*/
				pointArray = (new PointGenerator(nX,dX,nY,dY,current.getUpCorner(),current.getLeftCorner())).getPoints();		
			}else{
				ArrayList<MyPoint> finalList = new ArrayList<MyPoint>();
							
				for(int i=0; i<nY;i++){
					double yValue = i*dY + current.getUpCorner(); 
					ArrayList<MyPoint> pointList;
					/*Generar los puntos*/
					if(i%2==0){
						/*Filas pares se comportan normalmente*/
						pointList = (new RowPointGenerator(current.getLeftCorner(),current.getRightCorner(),dX,yValue)).getPoints();	
					}else{
						double initX = current.getLeftCorner() + dX/2;
						double endX = current.getRightCorner() - dX/2;
						
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
