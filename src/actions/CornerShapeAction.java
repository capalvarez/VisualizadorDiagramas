package actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.regions.MyRegion;
import utilities.regions.RectangleRegion;

import dataProcessors.PointInitProcess;
import display.IWindow;

public class CornerShapeAction extends AbstractAction {
	private IWindow window;	
	
	public CornerShapeAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		/*Pedir input de usuario*/
		JTextField xFieldP1 = new JTextField(5);
		JTextField yFieldP1 = new JTextField(5);
		JTextField xFieldP2 = new JTextField(5);
		JTextField yFieldP2 = new JTextField(5);

		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		inputPanel.add(new JLabel("Punto 1"));
		
		JPanel x1Panel = new JPanel();
		x1Panel.add(new JLabel("x:"));
		x1Panel.add(xFieldP1);
		inputPanel.add(x1Panel);
		
		JPanel y1Panel = new JPanel();
		y1Panel.add(new JLabel("y:"));
		y1Panel.add(yFieldP1);
		inputPanel.add(y1Panel);
		
		inputPanel.add(new JLabel("Punto 2"));
		
		JPanel x2Panel = new JPanel();
		x2Panel.add(new JLabel("x:"));
		x2Panel.add(xFieldP2);
		inputPanel.add(x2Panel);
		
		JPanel y2Panel = new JPanel();
		y2Panel.add(new JLabel("y:"));
		y2Panel.add(yFieldP2);
		inputPanel.add(y2Panel);

		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
					"Región desde dos puntos", JOptionPane.OK_CANCEL_OPTION);
			
		/*Solamente hacemos algo si el usuario confirmo el ingreso de los puntos*/
		if (result == JOptionPane.OK_OPTION) {
			MyPoint p1 = new MyPoint(Double.parseDouble(xFieldP1.getText()),Double.parseDouble(yFieldP1.getText()));
			MyPoint p2 = new MyPoint(Double.parseDouble(xFieldP2.getText()),Double.parseDouble(yFieldP2.getText()));
			MyPoint[] pointArray = {p1,p2};
			
			PointInitProcess pip = new PointInitProcess(pointArray,window);
			MyPoint[] regionPoints = pip.getPointList();
			MyScale scale = pip.getScale();
			
			MyRegion region = new RectangleRegion(p1,p2,scale);	
		
			window.drawRegionInPanel(region,scale);
			window.repaint();
		}
	}
	
}
