package actions;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyPoint;
import utilities.perforations.MyCircle;
import utilities.perforations.Perforation;

import display.IWindow;

public class CircularRegionAction extends AbstractAction {
	private IWindow window;	
	
	public CircularRegionAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] options = { "Completo", "Cuarto", "Medio", "Otro"};
		
		/*Pedir input al usuario*/
		JTextField xFieldO = new JTextField(5);
		JTextField yFieldO = new JTextField(5);
		JTextField InnerRadius = new JTextField(5);
		JTextField OuterRadius = new JTextField(5);
		final JLabel space = new JLabel();
		
		final JPanel anglePanel = new JPanel();
		JTextField initAngle = new JTextField(5);
		JTextField endAngle = new JTextField(5);
		anglePanel.add(new JLabel("Ángulo inicial:"));
		anglePanel.add(initAngle);
		anglePanel.add(new JLabel("Ángulo final:"));
		anglePanel.add(endAngle);
		
		final JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		
		final JComboBox<String> optionList = new JComboBox<String>(options);
		optionList.setSelectedIndex(0);		
		inputPanel.add(optionList);
				
		optionList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(optionList.getSelectedIndex()==3){					
					inputPanel.remove(space);
					inputPanel.add(anglePanel);
				}else{
					inputPanel.remove(anglePanel);
					inputPanel.add(space);
				}
				
				inputPanel.repaint();
	       		inputPanel.revalidate();
	       		
			}
		});
		
		JPanel outerRadiusPanel = new JPanel();
		outerRadiusPanel.add(new JLabel("Radio externo:"));
		outerRadiusPanel.add(OuterRadius);
		inputPanel.add(outerRadiusPanel);
		
		JPanel radiusPanel = new JPanel();
		radiusPanel.add(new JLabel("Radio interno (en blanco para un círculo):"));
		radiusPanel.add(InnerRadius);
		inputPanel.add(radiusPanel);
		
		inputPanel.add(new JLabel("Punto de origen: (0,0) por defecto"));
		
		JPanel oXPanel = new JPanel();
		oXPanel.add(new JLabel("x:"));
		oXPanel.add(xFieldO);
		inputPanel.add(oXPanel);
		
		JPanel oYPanel = new JPanel();
		oYPanel.add(new JLabel("y:"));
		oYPanel.add(yFieldO);
		inputPanel.add(oYPanel);	
		
		inputPanel.add(space);
		
		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
				"Perforacion circular", JOptionPane.OK_CANCEL_OPTION);
		
		/*Solamente hacemos algo si el usuario confirmo el ingreso de los puntos*/
		if (result == JOptionPane.OK_OPTION) {
			/*Chequear si el usuario desea un punto de origen especifico->por ahora asumir buenos usuarios (no va a ingresar
			 * en un solo espacio)*/
			double origenX = 0;
			double origenY = 0;
			
			if(xFieldO.getText().length () != 0){
				origenX = Double.parseDouble(xFieldO.getText());
				origenY = Double.parseDouble(yFieldO.getText());
			}
		
			double r = Double.parseDouble(InnerRadius.getText());
			
			MyPoint origen = new MyPoint(origenX,origenY);
			Perforation p = new MyCircle(origen,r);
			
			window.getCurrentRegion().addPerforation(p);
			window.repaint();
		}

		
		
		
		
		
		
		
		
		
		


	}

}
