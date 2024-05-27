package uniandes.dpoo.taller7.interfaz4;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import uniandes.dpoo.taller7.modelo.Tablero;
import uniandes.dpoo.taller7.modelo.Top10;
import uniandes.dpoo.taller7.modelo.RegistroTop10;
import java.util.Collection;

@SuppressWarnings("serial")
public class Vista extends JFrame {
    private JPanel base;
    private JPanel pAbajo;
    private JPanel pArriba;
    private Lienzo lienzo;
    private JPanel pBotones;
    private Tablero tablero;
    private Top10 top10;
    private int jugadas;
    private JTextField jugadasText;
    private JTextField jugadorText;

    public Vista() {
        setTitle("Juego de LightsOut");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        top10 = new Top10();
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
                tablero = new Tablero(nuevoTamano);
                tablero.salvar_tablero();
                lienzo.setTamano(nuevoTamano);
                jugadas = 0;
                jugadasText.setText("0");
                lienzo.setTablero(tablero.darTablero());
                lienzo.repaint();
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
        lienzo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tablero != null) {
                    int tamano = lienzo.getTamano();
                    int ancho = lienzo.getWidth();
                    int alto = lienzo.getHeight();
                    int tamanoCelda = Math.min(ancho, alto) / tamano;
                    int fila = e.getY() / tamanoCelda;
                    int columna = e.getX() / tamanoCelda;

                    if (fila < tamano && columna < tamano) {
                        tablero.jugar(fila, columna);
                        lienzo.setTablero(tablero.darTablero());
                        lienzo.repaint();
                        jugadas = tablero.darJugadas();
                        jugadasText.setText(String.valueOf(jugadas));
                        if (tablero.tableroIluminado()) {
                            int puntaje = tablero.calcularPuntaje();
                            if (top10.esTop10(puntaje)) {
                                String nombre = jugadorText.getText();
                                top10.agregarRegistro(nombre, puntaje);
                                mostrarTop10();
                            }
                        }
                    }
                }
            }
        });
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
        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tamano = lienzo.getTamano();
                tablero = new Tablero(tamano);
                int dificultad = 1; // Default difficulty for example
                if (botonFacil.isSelected()) {
                    dificultad = 5;
                } else if (botonMedio.isSelected()) {
                    dificultad = 10;
                } else if (botonDificil.isSelected()) {
                    dificultad = 20;
                }
                tablero.desordenar(dificultad);
                tablero.salvar_tablero();
                jugadas = 0;
                jugadasText.setText("0");
                lienzo.setTablero(tablero.darTablero());
                lienzo.repaint();
            }
        });
        
        JButton reiniciar = new JButton("REINICIAR");
        reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablero != null) {
                    tablero.reiniciar();
                    jugadas = 0;
                    jugadasText.setText("0");
                    lienzo.setTablero(tablero.darTablero());
                    lienzo.repaint();
                }
            }
        });
        
        JButton top10Button = new JButton("TOP-10");
        top10Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTop10();
            }
        });
        
        JButton cambiarJugador = new JButton("CAMBIAR JUGADOR");
        cambiarJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoJugador = JOptionPane.showInputDialog("Ingrese su nombre:");
                if (nuevoJugador != null && !nuevoJugador.trim().isEmpty()) {
                    jugadorText.setText(nuevoJugador);
                }
            }
        });
        
        pBotones.add(nuevo, gbc);
        pBotones.add(reiniciar, gbc);
        pBotones.add(top10Button, gbc);
        pBotones.add(cambiarJugador, gbc);
        
        base.add(pBotones, BorderLayout.EAST);
        
        pAbajo = new JPanel();
        pAbajo.setLayout(new GridLayout(1, 4));
        
        JLabel jugadasLabel = new JLabel("Jugadas: ");
        pAbajo.add(jugadasLabel);
        jugadasText = new JTextField("0", 5);
        jugadasText.setEditable(false);
        pAbajo.add(jugadasText);
        
        JLabel jugadorLabel = new JLabel("Jugador: ");
        pAbajo.add(jugadorLabel);
        jugadorText = new JTextField(10);
        pAbajo.add(jugadorText);
        
        base.add(pAbajo, BorderLayout.SOUTH);
    }

    private void mostrarTop10() {
        JDialog top10Dialog = new JDialog(this, "Top 10", true);
        top10Dialog.setSize(300, 400);
        top10Dialog.setLayout(new BorderLayout());

        String[] columnNames = {"#", "Nombre", "Puntaje"};
        Collection<RegistroTop10> registros = top10.darRegistros();
        Object[][] data = new Object[registros.size()][3];
        int index = 0;
        for (RegistroTop10 registro : registros) {
            data[index][0] = index + 1;
            data[index][1] = registro.darNombre();
            data[index][2] = registro.darPuntos();
            index++;
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        top10Dialog.add(scrollPane, BorderLayout.CENTER);

        top10Dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new Vista();
    }
}
