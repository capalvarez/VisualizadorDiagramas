package boot;

import java.awt.EventQueue;

import display.DisplayWindow;

public class Boot {

	public static void main(String[] args){
			
		/*Forma de asegurarse que los eventos de swing sean seguros en concurrencia*/
		EventQueue.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		    	DisplayWindow dw = new DisplayWindow();
		    	dw.showWindow();
		    }
		});
	}
}
