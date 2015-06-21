package actions;

import generalTools.LinePointGenerator;

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
import utilities.MyRegion;

import display.IWindow;

public class BorderPointSpaceAction extends AbstractAction {
	private IWindow window;	
	
	public BorderPointSpaceAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0){
		final JCheckBox forAll = new JCheckBox("Ingresar por lado");
		forAll.setSelected(false);
		
		final JTextField num1 = new JTextField(5);
		final JTextField num2 = new JTextField(5);
		final JTextField num3 = new JTextField(5);
		final JTextField num4 = new JTextField(5);
		
		final JPanel inputPanel = new JPanel();
		inputPanel.add(forAll);
		
		forAll.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {
		        	inputPanel.removeAll(); 
		        	inputPanel.add(forAll);
		        	
		        	inputPanel.add(new JLabel());
		        	
		        	GridLayout layout = new GridLayout(0,2);
		    		inputPanel.setLayout(layout);
		        	
		      		inputPanel.add(new JLabel("Borde 1:"));
		    		
		    		JPanel panel1 = new JPanel();
		    		panel1.add(num1);
		    		inputPanel.add(panel1);
		    		
		    		inputPanel.add(new JLabel("Borde 2:"));
		    		
		    		JPanel panel2 = new JPanel();
		    		panel2.add(num2);
		    		inputPanel.add(panel2);
		    		
		    		inputPanel.add(new JLabel("Borde 3:"));
		    		
		    		JPanel panel3 = new JPanel();
		    		panel2.add(num3);
		    		inputPanel.add(panel3);
		    		
		    		inputPanel.add(new JLabel("Borde 4:"));
		    		
		    		JPanel panel4 = new JPanel();
		    		panel4.add(num4);
		    		inputPanel.add(panel4);
		    		
		    		inputPanel.repaint();
		       		inputPanel.revalidate();
	
		        } else {
		        	inputPanel.removeAll(); 
		        	inputPanel.add(forAll);
		      
		        	GridLayout layout = new GridLayout(0,1);
		    		inputPanel.setLayout(layout);
		        	
		        	inputPanel.add(new JLabel("Borde:"));
		    		
		    		JPanel xPanel = new JPanel();
		    		xPanel.add(num1);
		    		inputPanel.add(xPanel);
		    		
		       		inputPanel.repaint();
		    		inputPanel.revalidate();
		        };
		    }
		});
		
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		inputPanel.add(new JLabel("Borde:"));
		
		JPanel xPanel = new JPanel();
		xPanel.add(num1);
		inputPanel.add(xPanel);
					
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos en el borde por separacion", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			if(forAll.isSelected()){
				double delta = Double.parseDouble(num1.getText());
				MyRegion current = window.getCurrentRegion();
																	
				LinePointGenerator lpg = new LinePointGenerator();
				
				MyPoint[] pointArrayLine1 = lpg.getPointsX(current.getUpCorner(),current.getDownCorner(),delta,current.getLeftCorner());
				MyPoint[] pointArrayLine2 = lpg.getPointsY(current.getLeftCorner(),current.getRightCorner(),delta,current.getDownCorner());
				MyPoint[] pointArrayLine3 = lpg.getPointsX(current.getUpCorner(),current.getDownCorner(),delta,current.getRightCorner());
				MyPoint[] pointArrayLine4 = lpg.getPointsY(current.getLeftCorner(),current.getRightCorner(),delta,current.getUpCorner());
			
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