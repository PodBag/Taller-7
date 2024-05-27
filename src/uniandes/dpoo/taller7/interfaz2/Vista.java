package uniandes.dpoo.taller7.interfaz2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class Vista extends JFrame {
	private JPanel base;
	private JPanel pAbajo;
	private JPanel pArriba;
	private JPanel pBotones;
	
	public Vista() {
		setTitle("Juego de LightsOut");
		setSize(500, 600); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 
		Base();
		this.setVisible(true);
	}
	
	public void Base () {
		base = new JPanel();
		base.setLayout(new BorderLayout());
		this.getContentPane().add(base);
		
		pArriba = new JPanel();
		pArriba.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel tamanio = new JLabel("Tamaño: ");
		pArriba.add(tamanio);
		
		String[] mediciones = {"5x5", "6x6", "7x7", "8x8"};
		JComboBox<String> medidas = new JComboBox<>(mediciones);	
		pArriba.add(medidas);
		
		JLabel dificultad = new JLabel("Dificultad: ");
		pArriba.add(dificultad);
		
		JRadioButton botonFacil = new JRadioButton("Fácil");
		JRadioButton botonMedio = new JRadioButton("Medio");
		JRadioButton botonDificil = new JRadioButton("Difícil");
		
		ButtonGroup dificultades = new ButtonGroup();
		dificultades.add(botonFacil);
		dificultades.add(botonMedio);
		dificultades.add(botonDificil);
		
		pArriba.add(botonFacil);
		pArriba.add(botonMedio);
		pArriba.add(botonDificil);
		
		base.add(pArriba, BorderLayout.NORTH);
		
		pBotones = new JPanel();
		pBotones.setLayout(new GridLayout(4, 1, 10, 10));
		JButton nuevo = new JButton("NUEVO");
		JButton reiniciar = new JButton("REINICIAR");
		JButton top10 = new JButton("TOP-10");
		JButton cambiarJugador = new JButton("CAMBIAR JUGADOR");
		
		pBotones.add(nuevo);
		pBotones.add(reiniciar);
		pBotones.add(top10);
		pBotones.add(cambiarJugador);
		
		base.add(pBotones, BorderLayout.EAST);
		
		pAbajo = new JPanel();
		pAbajo.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel jugadasLabel = new JLabel("Jugadas: ");
		pAbajo.add(jugadasLabel);
		JTextField jugadasText = new JTextField("0", 5) ;
		pAbajo.add(jugadasText);
		
		JLabel jugadorLabel = new JLabel("Jugador: ");
		pAbajo.add(jugadorLabel);
		JTextField jugadorText = new JTextField(10) ;
		pAbajo.add(jugadorText);
		
		base.add(pAbajo, BorderLayout.SOUTH);
	}
	
	public static void main (String[] args) {
		new Vista();
	}
}
