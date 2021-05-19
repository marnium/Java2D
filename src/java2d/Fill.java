package java2d;

import java.awt.Color;
import javax.swing.JColorChooser;

public class Fill extends Transformation {

    private final javax.swing.JRadioButton jrbActive;
    private boolean isActiveFill = false;

    public Fill(java.awt.Shape shp) {
        shpFigura = shp;

        javax.swing.JPanel jpnOpc = new javax.swing.JPanel();
        javax.swing.JButton jbtCambiarColor = new javax.swing.JButton("Cambiar Color de relleno");
        jpnOpc.add(jbtCambiarColor);
        jpnOpc.add(jrbActive = new javax.swing.JRadioButton("Mantener rellono para las transformaciones", isActiveFill));
        jrbActive.addItemListener((java.awt.event.ItemEvent e) -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                isActiveFill = true;
                apariencia = 1;
            } else if (isActiveFill) {
                apariencia = 0;
            }
        });
        jbtCambiarColor.addActionListener((java.awt.event.ActionEvent e) -> {
            Color color = JColorChooser.showDialog(Fill.this, "Selecciona un color", clrR);
            if (color != null) {
                clrR = color;
                jpnAreaDibujo.repaint();
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
        if (apariencia != 1) {
            isActiveFill = false;
            jrbActive.setSelected(isActiveFill);
        }
        relleno(g2d);
    }

    @Override
    public void reiniciar() {
    }
}
