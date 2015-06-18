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
	JMenu ingresarItem;
	JMenuItem azarItem;
	JMenu uniformeItem;
	JMenuItem imprimir;
	
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
		
		JMenu print = new JMenu("Imprimir");
		print.setMnemonic(KeyEvent.VK_I);
		setPrintMenu(print);
		menubar.add(Box.createHorizontalGlue());
		menubar.add(print);
				
		JMenu reset = new JMenu("Limpiar");
		reset.setMnemonic(KeyEvent.VK_L);
		setResetMenu(reset);
		menubar.add(reset);
		
		JMenu preferencias = new JMenu("Preferencias");
		preferencias.setMnemonic(KeyEvent.VK_F);
		setPreferenciasMenu(preferencias);
		menubar.add(preferencias);	
		
	}
	
	public JMenuBar getMenuBar(){
		return menubar;
	}
    
	public void setDibujarMenu(JMenu dibujar){	
		ingresarItem = new JMenu("Ingresar puntos");
		ingresarItem.setMnemonic(KeyEvent.VK_I);

		JMenuItem archivo = new JMenuItem("Desde archivo");
		archivo.addActionListener(new SelectFilePointsAction(window));
		
		JMenuItem manual = new JMenuItem("Manualmente");
		manual.addActionListener(new ManualPointsAction(window));

		ingresarItem.add(archivo);
		ingresarItem.add(manual);
		
		dibujar.add(ingresarItem);
		ingresarItem.setEnabled(false);
		
		azarItem = new JMenuItem("Puntos al azar");
		azarItem.setMnemonic(KeyEvent.VK_A);
		azarItem.addActionListener(new RandomPointsAction(window));
		azarItem.setToolTipText("Generar puntos aleatoriamente");
		
		dibujar.add(azarItem);
		azarItem.setEnabled(false);
		
		uniformeItem = new JMenu("Generacion uniforme");
		uniformeItem.setMnemonic(KeyEvent.VK_U);
		
		JMenuItem distancia = new JMenuItem("Ingresar separacion");
		distancia.addActionListener(new UniformSepPointsAction(window));
		
		JMenuItem numero = new JMenuItem("Ingresar numero de puntos");
		numero.addActionListener(new UniformNumPointsAction(window));

		uniformeItem.add(distancia);
		uniformeItem.add(numero);
				
		dibujar.add(uniformeItem);
		uniformeItem.setEnabled(false);
		
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
		JMenuItem color =  new JMenuItem("Color de Voronoi");
		color.setToolTipText("Cambia el color en que se dibujan puntos y lineas");
		color.addActionListener(new ColourChangeAction(window));
		color.setMnemonic(KeyEvent.VK_C);
		preferencias.add(color);
		
		JMenuItem Dcolor =  new JMenuItem("Color de Delaunay");
		Dcolor.setToolTipText("Cambia el color en que se dibujan puntos y lineas");
		Dcolor.addActionListener(new DelaunayColorChangeAction(window));
		Dcolor.setMnemonic(KeyEvent.VK_D);
		preferencias.add(Dcolor);		
		
		JMenuItem backG =  new JMenuItem("Color de fondo");
		backG.setToolTipText("Cambia el color del fondo");
		backG.addActionListener(new ColourBackGroundChangeAction(window));
		backG.setMnemonic(KeyEvent.VK_O);
		preferencias.add(backG);
		
		JMenuItem pointS =  new JMenuItem("Tamaño de puntos");
		pointS.addActionListener(new PointSizeChangeAction(window));
		pointS.setMnemonic(KeyEvent.VK_T);
		preferencias.add(pointS);
		
		JMenuItem coordSys =  new JMenuItem("Mostrar sistema de coordenadas");
		coordSys.addActionListener(new CoordinateSystemAction(window));
		coordSys.setMnemonic(KeyEvent.VK_C);
		preferencias.add(coordSys);
		
		JMenuItem origin =  new JMenuItem("Mostrar origen");
		origin.addActionListener(new OriginSystemAction(window));
		origin.setMnemonic(KeyEvent.VK_G);
		preferencias.add(origin);

		
		
		
	}
	
	public void setResetMenu(JMenu reset){
		JMenuItem borrarDiagrama = new JMenuItem("Borrar diagrama");
		borrarDiagrama.setToolTipText("Borra diagrama, dejando puntos y region");
		borrarDiagrama.addActionListener(new DiagramDeleteAction(window));
		borrarDiagrama.setMnemonic(KeyEvent.VK_D);
		reset.add(borrarDiagrama);	
		
		JMenuItem borrarPuntos = new JMenuItem("Borrar puntos");
		borrarPuntos.setToolTipText("Borra el diagrama y los puntos, dejando la region");
		borrarPuntos.addActionListener(new PointDeleteAction(window));
		borrarPuntos.setMnemonic(KeyEvent.VK_P);
		reset.add(borrarPuntos);
				
		JMenuItem resetear = new JMenuItem("Resetear");
		resetear.setToolTipText("Resetea completamente la ventana");
		resetear.addActionListener(new ResetAction(window));
		resetear.setMnemonic(KeyEvent.VK_R);
		reset.add(resetear);
	}
	
	public void setPrintMenu(JMenu print){
		imprimir = new JMenuItem("Imprimir archivo");
		imprimir.setToolTipText("Crea archivo de output");
		imprimir.addActionListener(new OutputFileAction(window));
		imprimir.setMnemonic(KeyEvent.VK_P);
		imprimir.setEnabled(false);
		print.add(imprimir);		
	}
	
	public void setPrintEnabled(boolean shown){
		imprimir.setEnabled(shown);
	}
	
	public void setDiagramasEnabled(boolean shown){
		delaunay.setEnabled(shown);
		voronoi.setEnabled(shown);
	}
	
	public void setPuntosEnabled(boolean shown){
		ingresarItem.setEnabled(shown);
		azarItem.setEnabled(shown);
		uniformeItem.setEnabled(shown);
	}
	
}
