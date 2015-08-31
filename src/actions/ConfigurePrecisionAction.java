package actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import display.IWindow;

public class ConfigurePrecisionAction extends AbstractAction{
	private IWindow window;	
	
	public ConfigurePrecisionAction(IWindow w){
		window = w;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/*Pedir input de usuario*/
		JTextField field = new JTextField(5);
	
		JPanel inputPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);
		inputPanel.setLayout(layout);
		
		inputPanel.add(new JLabel("Ingrese precision a utilizar" + "\n" + "(del orden de 0.999 en general funciona bien)"));
		inputPanel.add(field);

		int result = JOptionPane.showConfirmDialog(null, inputPanel, 
					"Configurar precision", JOptionPane.OK_CANCEL_OPTION);
	
		/*Solamente hacemos algo si el usuario confirmo el ingreso de los puntos*/
		if (result == JOptionPane.OK_OPTION) {
			double precision = Double.parseDouble(field.getText());
			window.setPrecision(precision);
		}	
	}
}
