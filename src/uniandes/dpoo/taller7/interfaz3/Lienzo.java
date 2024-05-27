package uniandes.dpoo.taller7.interfaz3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Lienzo extends JPanel {
    private int tamano;

    public Lienzo(int tamanoInicial) {
        this.tamano = tamanoInicial;
    }

    public void setTamano(int nuevoTamano) {
        this.tamano = nuevoTamano;
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
                g2d.setColor(Color.YELLOW);
                g2d.fillRect(i * tamanoCelda, j * tamanoCelda, tamanoCelda, tamanoCelda);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(i * tamanoCelda, j * tamanoCelda, tamanoCelda, tamanoCelda);
            }
        }
    }
}
