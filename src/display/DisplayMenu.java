package display;

import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import actions.*;

public class DisplayMenu {
	private JMenuBar menubar;
	private IWindow window;
	JMenuItem delaunay;
	JMenuItem voronoi;
	
	public DisplayMenu(IWindow window){
		menubar = new JMenuBar();
		this.window = window;
		
		JMenu puntos = new JMenu("Region");
		puntos.setMnemonic(KeyEvent.VK_R);
		setPuntosMenu(puntos);
		menubar.add(puntos);
		
		JMenu dibujar = new JMenu("Puntos");
		dibujar.setMnemonic(KeyEvent.VK_P);
		setDibujarMenu(dibujar);
		menubar.add(dibujar);
	
		JMenu diagramas = new JMenu("Diagrama");
		diagramas.setMnemonic(KeyEvent.VK_D);
		setDiagramasMenu(diagramas);
		menubar.add(diagramas);
		
		JMenu preferencias = new JMenu("Preferencias");
		preferencias.setMnemonic(KeyEvent.VK_F);
		setPreferenciasMenu(preferencias);
		menubar.add(Box.createHorizontalGlue());
		menubar.add(preferencias);
		
	}
	
	public JMenuBar getMenuBar(){
		return menubar;
	}
    
	public void setDibujarMenu(JMenu dibujar){	
		JMenu ingresarItem = new JMenu("Ingresar puntos");
		ingresarItem.setMnemonic(KeyEvent.VK_I);

		JMenuItem archivo = new JMenuItem("Desde archivo");
		archivo.addActionListener(new SelectFilePointsAction(window));
		
		JMenuItem manual = new JMenuItem("Manualmente");
		manual.addActionListener(new ManualPointsAction(window));

		ingresarItem.add(archivo);
		ingresarItem.add(manual);
		
		dibujar.add(ingresarItem);
	
		JMenuItem azarItem = new JMenuItem("Puntos al azar");
		azarItem.setMnemonic(KeyEvent.VK_A);
		azarItem.addActionListener(new RandomPointsAction(window));
		azarItem.setToolTipText("Generar puntos aleatoriamente");
		
		dibujar.add(azarItem);
	}
	
	public void setPuntosMenu(JMenu puntos){
		JMenuItem ingresar =  new JMenuItem("Esquinas");
		ingresar.addActionListener(new CornerShapeAction(window));
		ingresar.setMnemonic(KeyEvent.VK_E);
		ingresar.setToolTipText("Generar region rectangular a partir de dos puntos");
		puntos.add(ingresar);
		
		JMenuItem ancho = new JMenuItem("Largo/Ancho");
		ancho.addActionListener(new HeightWeightShapeAction(window));
		ancho.setMnemonic(KeyEvent.VK_L);
		puntos.add(ancho);
		
		JMenuItem azar = new JMenuItem("Al azar");
		azar.addActionListener(new RandomShapeAction(window));
		azar.setMnemonic(KeyEvent.VK_Z);
		puntos.add(azar);
	}
	
	public void setDiagramasMenu(JMenu diagramas){
		delaunay =  new JMenuItem("Delaunay");
		delaunay.addActionListener(new DelaunayDiagramAction(window));
		delaunay.setMnemonic(KeyEvent.VK_Y);
		diagramas.add(delaunay);
		delaunay.setEnabled(false);
		
		voronoi =  new JMenuItem("Voronoi");
		voronoi.addActionListener(new VoronoiDiagramAction(window));
		voronoi.setMnemonic(KeyEvent.VK_V);
		diagramas.add(voronoi);
		voronoi.setEnabled(false);	
	}

	public void setPreferenciasMenu(JMenu preferencias){
		JMenuItem color =  new JMenuItem("Color de dibujo");
		color.setToolTipText("Cambia el color en que se dibujan puntos y lineas");
		color.addActionListener(new ColourChangeAction(window));
		color.setMnemonic(KeyEvent.VK_C);
		preferencias.add(color);	
		
		JMenuItem backG =  new JMenuItem("Color de fondo");
		backG.setToolTipText("Cambia el color del fondo");
		backG.addActionListener(new ColourBackGroundChangeAction(window));
		backG.setMnemonic(KeyEvent.VK_O);
		preferencias.add(backG);	
	}
	
	public void setDiagramasEnabled(){
		delaunay.setEnabled(true);
		voronoi.setEnabled(true);
	}
	
}
