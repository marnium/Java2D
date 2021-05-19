package java2d;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JColorChooser;

public class Degraded extends Transformation {

    private final javax.swing.JRadioButton jrbActive;
    private boolean isActiveDegraded = false;

    public Degraded(java.awt.Shape shape) {
        shpFigura = shape;

        //>Panel opciones
        javax.swing.JPanel jpnOpc = new javax.swing.JPanel();
        jpnOpc.add(new javax.swing.JLabel("Colores para degradado: "));
        javax.swing.JButton jbtClr1 = new javax.swing.JButton("Color 1");
        jbtClr1.setBackground(clrD1);
        jbtClr1.addActionListener((ActionEvent e) -> {
            Color color = JColorChooser.showDialog(Degraded.this, "Selecciona color 1", clrD1);
            if (color != null) {
                clrD1 = color;
                jbtClr1.setBackground(clrD1);
                jpnAreaDibujo.repaint();
            }
        });
        jpnOpc.add(jbtClr1);
        jpnOpc.add(new javax.swing.JLabel(", "));
        javax.swing.JButton jbtClr2 = new javax.swing.JButton("Color 2");
        jbtClr2.setBackground(clrD2);
        jbtClr2.addActionListener((ActionEvent e) -> {
            Color color = JColorChooser.showDialog(Degraded.this, "Selecciona color 2", clrD2);
            if (color != null) {
                clrD2 = color;
                jbtClr2.setBackground(clrD2);
                jpnAreaDibujo.repaint();
            }
        });
        jpnOpc.add(jbtClr2);
        jpnOpc.add(jrbActive = new javax.swing.JRadioButton("Mantener Degradado para las transformaciones", isActiveDegraded));
        jrbActive.addItemListener((java.awt.event.ItemEvent e) -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                apariencia = 2;
                isActiveDegraded = true;
            } else if (isActiveDegraded) {
                apariencia = 0;
            }
        });

        add(jpnOpc, java.awt.BorderLayout.NORTH);
    }

    @Override
    public void cambiarFigura(java.awt.Shape shp) {
        shpFigura = shp;
        jpnAreaDibujo.repaint();
    }

    @Override
    public void dibujarFigura(java.awt.Graphics2D g2d) {
        if (apariencia != 2) {
            isActiveDegraded = false;
            jrbActive.setSelected(isActiveDegraded);
        }
        degradado(g2d);
    }

    @Override
    public void reiniciar() {
    }
}
