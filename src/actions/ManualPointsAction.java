package actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import display.IWindow;
import utilities.MyPoint;

public class ManualPointsAction extends AbstractAction{
	private IWindow window;	
	
	public ManualPointsAction(IWindow w){
		window = w;
	}
		
	@Override
	public void actionPerformed(ActionEvent event) {
		/*Pedir input al usuario*/	
		InputDialog input = new InputDialog(window);
		input.setSize(500, 300);	
	}
	
	public class InputDialog extends JDialog{

		public InputDialog(JFrame parent){
			super(parent, "Ingreso manual de puntos");
			try{
				JPanel messagePane = new JPanel();
				BorderLayout borderLayout1 = new BorderLayout();
				JScrollPane theScrollPane = new JScrollPane();				
				String[] columnNames = {"x","y","z"};		
				int numRows = 5;
				final DefaultTableModel model = new DefaultTableModel(numRows, columnNames.length) ;
				model.setColumnIdentifiers(columnNames);
				JTable table = new JTable(model);

				messagePane.setLayout(borderLayout1);
				messagePane.add(new JLabel("Para puntos en 2D, dejar columna z en blanco"),BorderLayout.NORTH);

				getContentPane().add(messagePane);
				messagePane.add(theScrollPane, BorderLayout.CENTER);
				theScrollPane.getViewport().add(table, null);

				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new GridLayout(0,2));
								
				final JButton button = new JButton("OK");
				button.addActionListener(new AbstractAction(){

					@Override
					public void actionPerformed(ActionEvent e) {
						button.requestFocus();
						Vector data = model.getDataVector();
						MyPoint[] pointsArray = processPoints(data);
						window.drawDiagramInPanel(pointsArray);
						window.repaint();	
					}
					
					private MyPoint[] processPoints(Vector data){
						List<MyPoint> points = new ArrayList<MyPoint>();
						Iterator it = data.iterator();
						
						while(it.hasNext()){
							 Vector row = (Vector)it.next();
							 Iterator i = row.iterator();
							
							 Object x = i.next();
							 Object y = i.next();
							 Object z = i.next();
												 
							 if(x==null || y==null){
								 continue;
							 }
							
							 if(z==null){
								points.add(new MyPoint(Integer.parseInt((String)x),Integer.parseInt((String)y))); 
							 }			 
						 }
						 
						 MyPoint[] pointArray = new MyPoint[points.size()];
						 pointArray = points.toArray(pointArray);
							
						return pointArray;					
					}
				});
				
				buttonPanel.add(button);
				button.setPreferredSize(new Dimension(20, 20));	
				
				JButton button2 = new JButton("Cancel");
				button2.addActionListener(new AbstractAction(){
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}					
				});
				buttonPanel.add(button2);
				button2.setPreferredSize(new Dimension(20, 20));
				
				messagePane.add(buttonPanel,BorderLayout.SOUTH);
				
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
