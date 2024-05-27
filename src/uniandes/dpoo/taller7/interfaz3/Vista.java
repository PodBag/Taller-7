package uniandes.dpoo.taller7.interfaz3;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Vista extends JFrame {
    private JPanel base;
    private JPanel pAbajo;
    private JPanel pArriba;
    private Lienzo lienzo;
    private JPanel pBotones;

    public Vista() {
        setTitle("Juego de LightsOut");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Base();
        this.setVisible(true);
    }

    public void Base() {
        base = new JPanel();
        base.setLayout(new BorderLayout());
        this.getContentPane().add(base);

        pArriba = new JPanel();
        pArriba.setLayout(new GridLayout(1, 7, 5, 5));
        
        JLabel tamanioLabel = new JLabel("Tamaño: ");
        pArriba.add(tamanioLabel);
        
        String[] mediciones = {"5x5", "6x6", "7x7", "8x8"};
        JComboBox<String> medidas = new JComboBox<>(mediciones);
        pArriba.add(medidas);
        
        medidas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) medidas.getSelectedItem();
                int nuevoTamano = Integer.parseInt(seleccion.substring(0, 1));
                lienzo.setTamano(nuevoTamano);
            }
        });
        
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
        
        lienzo = new Lienzo(5); 
        base.add(lienzo, BorderLayout.CENTER);
        
        pBotones = new JPanel();
        pBotones.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JButton nuevo = new JButton("NUEVO");
        JButton reiniciar = new JButton("REINICIAR");
        JButton top10 = new JButton("TOP-10");
        JButton cambiarJugador = new JButton("CAMBIAR JUGADOR");
        
        pBotones.add(nuevo, gbc);
        pBotones.add(reiniciar, gbc);
        pBotones.add(top10, gbc);
        pBotones.add(cambiarJugador, gbc);
        
        base.add(pBotones, BorderLayout.EAST);
        
        pAbajo = new JPanel();
        pAbajo.setLayout(new GridLayout(2, 2));
        
        JLabel jugadasLabel = new JLabel("Jugadas: ");
        pAbajo.add(jugadasLabel);
        JTextField jugadasText = new JTextField("0", 5);
        pAbajo.add(jugadasText);
        
        JLabel jugadorLabel = new JLabel("Jugador: ");
        pAbajo.add(jugadorLabel);
        JTextField jugadorText = new JTextField(10);
        pAbajo.add(jugadorText);
        
        base.add(pAbajo, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new Vista();
    }
}
