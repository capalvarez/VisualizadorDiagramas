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
import utilities.regions.MyRegion;

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

		String[] options = { "Cartesiano", "Cilindrico"};
		final JComboBox<String> optionList = new JComboBox<String>(options);
		optionList.setSelectedIndex(0);		
				
		final JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.add(optionList);
		
		inputPanel.setLayout(layout);
		final JLabel firstLabel = new JLabel("Numero de puntos en X:"); 
		
		inputPanel.add(firstLabel);
		
		JPanel xPanel = new JPanel();
		xPanel.add(numX);
		inputPanel.add(xPanel);
		
		final JLabel secondLabel = new JLabel("Numero de puntos en Y:"); 
		inputPanel.add(secondLabel);
		
		JPanel yPanel = new JPanel();
		yPanel.add(numY);
		inputPanel.add(yPanel);
		
		final JCheckBox secondRow = new JCheckBox("Segunda fila desfasada");
		secondRow.setSelected(false);   
		inputPanel.add(secondRow);
		
		optionList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(optionList.getSelectedIndex()==1){					
					firstLabel.setText("N de puntos en radio:");
					secondLabel.setText("N de puntos en ángulo:");
				}else{
					firstLabel.setText("Numero de puntos en X:");
					secondLabel.setText("Numero de puntos en Y:");
				}
				
				inputPanel.repaint();
	       		inputPanel.revalidate();	
			}
		});
		
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Puntos uniforme por numero", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			int nX = Integer.parseInt(numX.getText());
			int nY = Integer.parseInt(numY.getText());	
			
			MyRegion current = window.getCurrentRegion();
			MyPoint[] pointArray = current.generateUniformByNumber(nX, nY, secondRow.isSelected());
						
			/*Dibujar los puntos y dejarlos guardados en la ventana*/
			window.drawPointsInPanel(pointArray,pointArray);
			window.repaint();
	
		}
	}

}
