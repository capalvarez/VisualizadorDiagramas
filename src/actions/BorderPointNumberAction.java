package actions;

import generalTools.LinePointGenerator;
import generalTools.PointGenerator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.ArrayUtils;

import utilities.MyPoint;
import utilities.MyRegion;

import dataProcessors.PointStringProcess;
import display.IWindow;

public class BorderPointNumberAction extends AbstractAction {
	private IWindow window;	
	
	public BorderPointNumberAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JCheckBox forAll = new JCheckBox("Todos los lados iguales");
		forAll.setSelected(true);

		JTextField num = new JTextField(5);
				
		JPanel inputPanel = new JPanel();
		inputPanel.add(forAll);
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		inputPanel.add(new JLabel("Borde 1:"));
		
		JPanel xPanel = new JPanel();
		xPanel.add(num);
		inputPanel.add(xPanel);
						
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos en el borde por numero", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			if(forAll.isSelected()){
				int n = Integer.parseInt(num.getText());
				MyRegion current = window.getCurrentRegion();
				
				double dX = current.getWidth()/(n-1);
				double dY = current.getHeight()/(n-1);
									
				LinePointGenerator lpg = new LinePointGenerator();
				
				MyPoint[] pointArrayLine1 = lpg.getPointsX(current.getUpCorner(),current.getDownCorner(),dY,current.getLeftCorner());
				MyPoint[] pointArrayLine2 = lpg.getPointsY(current.getLeftCorner(),current.getRightCorner(),dX,current.getDownCorner());
				MyPoint[] pointArrayLine3 = lpg.getPointsX(current.getUpCorner(),current.getDownCorner(),dY,current.getRightCorner());
				MyPoint[] pointArrayLine4 = lpg.getPointsY(current.getLeftCorner(),current.getRightCorner(),dX,current.getUpCorner());
			
				MyPoint[] both = ArrayUtils.addAll(pointArrayLine1, pointArrayLine2);
				MyPoint[] both2 = ArrayUtils.addAll(pointArrayLine3,pointArrayLine4);
				MyPoint[] both3 = ArrayUtils.addAll(both,both2);
							
				MyPoint[] currentPoints = window.getCurrentPoints();
				MyPoint[] finalPoints;
				
				if(currentPoints!=null){
					finalPoints = ArrayUtils.addAll(currentPoints,both3);
				}else{
					finalPoints = both3;
				}
				
				window.drawPointsInPanel(finalPoints,finalPoints);
				window.repaint();
				
			}else{
				
			}
			
			
			
		}
		
		
	
	}
	
	
	
}
