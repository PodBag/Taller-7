package uniandes.dpoo.taller7.interfaz4;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Lienzo extends JPanel {
    private int tamano;
    private boolean[][] tablero;
    private Image bombillo;

    public Lienzo(int tamanoInicial) {
        this.tamano = tamanoInicial;
        this.tablero = new boolean[tamanoInicial][tamanoInicial];
        // Cargar la imagen del bombillo
        this.bombillo = new ImageIcon("data/luz.png").getImage();
    }

    public void setTamano(int nuevoTamano) {
        this.tamano = nuevoTamano;
        this.tablero = new boolean[nuevoTamano][nuevoTamano];
        repaint(); 
    }

    public int getTamano() {
        return tamano;
    }

    public void setTablero(boolean[][] nuevoTablero) {
        this.tablero = nuevoTablero;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int ancho = getWidth();
        int alto = getHeight();
        int tamanoCelda = Math.min(ancho, alto) / tamano;

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (tablero[i][j]) {
                    g2d.setColor(Color.YELLOW);
                } else {
                    g2d.setColor(Color.LIGHT_GRAY);
                }
                g2d.fillRect(j * tamanoCelda, i * tamanoCelda, tamanoCelda, tamanoCelda);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(j * tamanoCelda, i * tamanoCelda, tamanoCelda, tamanoCelda);

                // Dibujar la imagen del bombillo
                g2d.drawImage(bombillo, j * tamanoCelda, i * tamanoCelda, tamanoCelda, tamanoCelda, this);
            }
        }
    }
}
