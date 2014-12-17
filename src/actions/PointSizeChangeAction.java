package actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import actions.ManualPointsAction.InputDialog;

import utilities.MyPoint;

import dataProcessors.PointStringProcess;
import display.IWindow;

public class PointSizeChangeAction extends AbstractAction {
	private IWindow window;	
	
	public PointSizeChangeAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		InputDialog input = new InputDialog(window);
		//input.setSize(500, 174);	
	}
	
	public class InputDialog extends JDialog{

		public InputDialog(JFrame parent){
			super(parent, "Tamaño de puntos");
			try{
				JPanel mainPane = new JPanel();
				mainPane.setLayout(new BorderLayout());
				
				JPanel messagePane = new JPanel();
				JLabel label0 = new JLabel("0");
				messagePane.add(label0);
				final JSlider slider = new JSlider(0, 15, 5);
				messagePane.add(slider);
				JLabel label15 = new JLabel("15");
				messagePane.add(label15);		
				
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());
				
				final JButton button = new JButton("OK");
				button.addActionListener(new AbstractAction(){

					@Override
					public void actionPerformed(ActionEvent e) {
						int value = slider.getValue();
						window.changePointSize(value);
						
						setVisible(false);
						dispose();
					}});
				
				buttonPanel.add(button);
								
				JButton button2 = new JButton("Cancel");
				button2.addActionListener(new AbstractAction(){
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}					
				});
				buttonPanel.add(button2);
				
				JPanel currentPanel = new JPanel();
				currentPanel.add(new JLabel("Actual: "+window.getCurrPointSize()));
				final JLabel current = new JLabel("Nuevo: "+window.getCurrPointSize());
				currentPanel.add(current);
				
				slider.addChangeListener(new ChangeListener() {		     

			            public void stateChanged(ChangeEvent event) {
			                int value = slider.getValue();
			                current.setText("Nuevo: "+value);
			            }
			        });
							
				JPanel centerPanel = new JPanel();
				centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.PAGE_AXIS));
				centerPanel.add(messagePane);
				centerPanel.add(currentPanel);
								
				mainPane.add(centerPanel,BorderLayout.CENTER);
				mainPane.add(buttonPanel,BorderLayout.SOUTH);
								
				add(mainPane);
				
				pack();
				setVisible(true);
				
			} catch(Exception ex){
				ex.printStackTrace();
		    }
		}
		
		public JRootPane createRootPane(){
			JRootPane rootPane = new JRootPane();
			KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
			Action action = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					dispose();
				}
			};
			InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			inputMap.put(stroke, "ESCAPE");
			rootPane.getActionMap().put("ESCAPE", action);
			return rootPane;
		}	
	}
}	
