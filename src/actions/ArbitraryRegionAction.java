package actions;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import utilities.MyPoint;
import utilities.MyScale;
import utilities.regions.ArbitraryRegion;
import utilities.regions.MyRegion;

import actions.ManualPointsAction.InputDialog;

import dataProcessors.PointInitProcess;
import dataProcessors.PointStringProcess;
import display.IWindow;

public class ArbitraryRegionAction extends AbstractAction {
	private IWindow window;	
	
	public ArbitraryRegionAction(IWindow w){
		window = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		InputDialog input = new InputDialog(window);
		input.setSize(500, 174);	
	}
	
	public class InputDialog extends JDialog{

		public InputDialog(JFrame parent){
			super(parent, "Ingreso manual de puntos");
			try{
				JPanel messagePane = new JPanel();
				BorderLayout borderLayout1 = new BorderLayout();
				JScrollPane theScrollPane = new JScrollPane();				
				String[] columnNames = {"x","y","z"};		
				int numRows = 1;
				final DefaultTableModel model = new DefaultTableModel(numRows, columnNames.length) ;
				model.setColumnIdentifiers(columnNames);
				JTable table = new JTable(model);

				messagePane.setLayout(borderLayout1);
				messagePane.add(new JLabel("Para puntos en 2D, dejar columna z en blanco"),BorderLayout.NORTH);

				getContentPane().add(messagePane);
				messagePane.add(theScrollPane, BorderLayout.CENTER);
				theScrollPane.getViewport().add(table, null);

				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());
								
				final JButton button = new JButton("OK");
				button.addActionListener(new AbstractAction(){

					@Override
					public void actionPerformed(ActionEvent e) {
						button.requestFocus();
						Vector data = model.getDataVector();
						String[] pointsArray = processPoints(data);					
						
						PointStringProcess pp = new PointStringProcess(pointsArray);
						MyPoint[] points = pp.getPointList();
						
						PointInitProcess pip = new PointInitProcess(points,window);
							
						MyScale scale = pip.getScale();																
						MyRegion region = new ArbitraryRegion(points,scale);					
						
						window.drawRegionInPanel(region,scale);
						window.repaint();
						setVisible(false);
						dispose();
					}
					
					private String[] processPoints(Vector data){
						List<String> points = new ArrayList<String>();
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
								points.add(x+" "+y); 
							 }			 
						 }
						 
						String[] pointArray = new String[points.size()];
						pointArray = points.toArray(pointArray);
							
						return pointArray;					
					}
				});
				
				buttonPanel.add(button);
								
				JButton button2 = new JButton("Cancel");
				button2.addActionListener(new AbstractAction(){
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}					
				});
				buttonPanel.add(button2);
				messagePane.add(buttonPanel,BorderLayout.SOUTH);

				JPanel addRow = new JPanel();
				addRow.setLayout(new FlowLayout());
				
				JButton addButton = new JButton("Agregar");
				addButton.addActionListener(new AbstractAction(){
					@Override
					public void actionPerformed(ActionEvent event) {
						model.insertRow(model.getRowCount(), new Object[] {null, null, null} );
					}	
				});
				
				addRow.add(addButton);
				messagePane.add(addRow,BorderLayout.WEST);
							
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
