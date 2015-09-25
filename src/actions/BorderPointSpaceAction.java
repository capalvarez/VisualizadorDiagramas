package actions;

import generalTools.LinePointGenerator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang3.ArrayUtils;

import utilities.MyPoint;
import utilities.regions.MyRegion;

import display.IWindow;

public class BorderPointSpaceAction extends AbstractAction {
	private IWindow window;	
	
	public BorderPointSpaceAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0){
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
				"Puntos en el borde por separacion", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			if(!forAll.isSelected()){
				double delta = Double.parseDouble(num1.getText());
				double[] distances = {delta,delta};
				MyPoint[] borderPoints = window.getCurrentRegion().generateBorderByDistance(distances, forAll.isSelected());	
							
				MyPoint[] currentPoints = window.getCurrentPoints();
				MyPoint[] finalPoints;
				
				if(currentPoints!=null){
					finalPoints = ArrayUtils.addAll(currentPoints,borderPoints);
				}else{
					finalPoints = borderPoints;
				}
				
				window.drawPointsInPanel(finalPoints,finalPoints);
				window.repaint();
				
			}else{
				double delta1 = Double.parseDouble(num1.getText());
				double delta2 = Double.parseDouble(num2.getText());
				double delta3 = Double.parseDouble(num3.getText());
				double delta4 = Double.parseDouble(num4.getText());
				
				double[] distances = {delta1,delta2,delta3,delta4};
				MyPoint[] borderPoints = window.getCurrentRegion().generateBorderByDistance(distances, forAll.isSelected());
							
				MyPoint[] currentPoints = window.getCurrentPoints();
				MyPoint[] finalPoints;
				
				if(currentPoints!=null){
					finalPoints = ArrayUtils.addAll(currentPoints,borderPoints);
				}else{
					finalPoints = borderPoints;
				}
				
				window.drawPointsInPanel(finalPoints,finalPoints);
				window.repaint();
			}

		}
	}
}