package uniandes.dpoo.taller7.interfaz1;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Vista extends JFrame{
	public Vista() {
		setTitle("Juego de LigthsOut");
		setSize(500, 600); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 
		this.setVisible(true);
	}
	
	public static void main () {
		new Vista();
	}
}
