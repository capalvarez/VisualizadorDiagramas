package actions;

import generalTools.PointGenerator;
import generalTools.RowPointGenerator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyPoint;
import utilities.MyScale;
import utilities.regions.MyRegion;

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
	
		String[] options = { "Cartesiano", "Cilindrico"};
		final JComboBox<String> optionList = new JComboBox<String>(options);
		optionList.setSelectedIndex(0);	
		
		final JPanel inputPanel = new JPanel();
		inputPanel.add(optionList);
		
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		final JLabel firstLabel = new JLabel("Separacion en X:"); 
		inputPanel.add(firstLabel);
		
		JPanel xPanel = new JPanel();
		xPanel.add(deltaX);
		inputPanel.add(xPanel);
		
		final JLabel secondLabel = new JLabel("Separacion en Y:");
		inputPanel.add(secondLabel);
		JPanel yPanel = new JPanel();
		yPanel.add(deltaY);
		inputPanel.add(yPanel);
		
		final JCheckBox secondRow = new JCheckBox("Segunda fila desfasada");
		secondRow.setSelected(false);   
		inputPanel.add(secondRow);
		
		optionList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(optionList.getSelectedIndex()==1){					
					firstLabel.setText("Separación en radio:");
					secondLabel.setText("Separación en ángulo:");
				}else{
					firstLabel.setText("Separación en X:");
					secondLabel.setText("Separación en Y:");
				}
				
				inputPanel.repaint();
	       		inputPanel.revalidate();	
			}
		});
		
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos uniforme por separacion", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			double dX = Double.parseDouble(deltaX.getText());
			double dY = Double.parseDouble(deltaY.getText());		
			
			MyRegion current = window.getCurrentRegion();
		
			MyPoint[] pointArray = current.generateUniformByDistance(dX, dY, secondRow.isSelected());
						
			/*Dibujar los puntos y dejarlos guardados en la ventana*/
			window.drawPointsInPanel(pointArray,pointArray);
			window.repaint();
			
		}
	}

}
