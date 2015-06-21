package actions;

import generalTools.LinePointGenerator;
import generalTools.PointGenerator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		final JCheckBox forAll = new JCheckBox("Ingresar por lado");
		forAll.setSelected(true);
		
		final JTextField num1 = new JTextField(5);
		final JTextField num2 = new JTextField(5);
		final JTextField num3 = new JTextField(5);
		final JTextField num4 = new JTextField(5);
		
		final JPanel inputPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		inputPanel.setLayout(layout);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1; 
		inputPanel.add(forAll, constraints);
			
		forAll.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	inputPanel.removeAll(); 
		        
		        	GridBagConstraints constraints = new GridBagConstraints();
		    		constraints.gridx = 0; 
		    		constraints.gridy = 0; 
		    		constraints.gridwidth = 2; 
		    		constraints.gridheight = 1; 
		    		inputPanel.add(forAll, constraints);
		     
		        	constraints.gridx = 0; 
		    		constraints.gridy = 1; 
		    		constraints.gridwidth = 1; 
		    		constraints.gridheight = 1;		        	
		      		inputPanel.add(new JLabel("Lado 1:"),constraints);
		    		
		      		constraints.gridx = 1; 
		    		JPanel panel1 = new JPanel();
		    		panel1.add(num1);
		    		inputPanel.add(panel1,constraints);
		    		
		    		constraints.gridx = 0; 
		    		constraints.gridy = 2; 
		    		inputPanel.add(new JLabel("Lado 2:"),constraints);
		    		
		    		constraints.gridx = 1; 
		    		JPanel panel2 = new JPanel();
		    		panel2.add(num2);
		    		inputPanel.add(panel2,constraints);
		    		
		    		constraints.gridx = 0; 
		    		constraints.gridy = 3; 
		    		inputPanel.add(new JLabel("Lado 3:"),constraints);
		    		
		    		constraints.gridx = 1; 
		    		JPanel panel3 = new JPanel();
		    		panel3.add(num3);
		    		inputPanel.add(panel3,constraints);
		    		
		    		constraints.gridx = 0; 
		    		constraints.gridy = 4; 
		    		inputPanel.add(new JLabel("Lado 4:"),constraints);
		    		
		    		constraints.gridx = 1; 
		    		JPanel panel4 = new JPanel();
		    		panel4.add(num4);
		    		inputPanel.add(panel4,constraints);
		    		
		    		inputPanel.repaint();
		       		inputPanel.revalidate();
	
		        } else {
		        	inputPanel.removeAll(); 
		        	GridBagConstraints constraints = new GridBagConstraints();
		    		constraints.gridx = 0; 
		    		constraints.gridy = 0; 
		    		constraints.gridwidth = 2; 
		    		constraints.gridheight = 1; 
		    		inputPanel.add(forAll, constraints);
		    				
		    		constraints.gridx = 0; 
		    		constraints.gridy = 1; 
		    		constraints.gridwidth = 1; 
		    		constraints.gridheight = 1;	
		    				    		
		    		inputPanel.add(new JLabel("Lados:"),constraints);
		    		
		    		constraints.gridx = 1; 		
		    		JPanel xPanel = new JPanel();
		    		xPanel.add(num1);
		    		inputPanel.add(xPanel,constraints);
		    		
		    		inputPanel.repaint();
		    		inputPanel.revalidate();
		        };
		    }
		});
    
    	constraints.gridx = 0; 
		constraints.gridy = 1; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1;		        	
  		inputPanel.add(new JLabel("Lado 1:"),constraints);
		
  		constraints.gridx = 1; 
		JPanel panel1 = new JPanel();
		panel1.add(num1);
		inputPanel.add(panel1,constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 2; 
		inputPanel.add(new JLabel("Lado 2:"),constraints);
		
		constraints.gridx = 1; 
		JPanel panel2 = new JPanel();
		panel2.add(num2);
		inputPanel.add(panel2,constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 3; 
		inputPanel.add(new JLabel("Lado 3:"),constraints);
		
		constraints.gridx = 1; 
		JPanel panel3 = new JPanel();
		panel3.add(num3);
		inputPanel.add(panel3,constraints);
		
		constraints.gridx = 0; 
		constraints.gridy = 4; 
		inputPanel.add(new JLabel("Lado 4:"),constraints);
		
		constraints.gridx = 1; 
		JPanel panel4 = new JPanel();
		panel4.add(num4);
		inputPanel.add(panel4,constraints);		
						
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos en el borde por numero", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			if(forAll.isSelected()){
				int n = Integer.parseInt(num1.getText());
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
