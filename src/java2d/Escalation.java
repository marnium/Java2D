package java2d;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Escalation extends Transformation {

    private JTextField jtfFacEscX;
    private JTextField jtfFacEscY;
    private double facEscX = 1.0;
    private double facEscY = 1.0;

    public Escalation(java.awt.Shape shp) {
        shpFigura = shp;

        //Panel opciones
        javax.swing.JPanel jpnOpciones = new javax.swing.JPanel();
        jpnOpciones.add(new javax.swing.JLabel("Factor de escala X = "));
        jtfFacEscX = new JTextField("1.0");
        jtfFacEscX.setColumns(10);
        jtfFacEscY = new JTextField("1.0");
        jtfFacEscY.setColumns(10);
        jpnOpciones.add(jtfFacEscX);
        jpnOpciones.add(new javax.swing.JLabel(", Y = "));
        jpnOpciones.add(jtfFacEscY);
        javax.swing.JButton jbtEscalation = new javax.swing.JButton("Escalar");
        jbtEscalation.addActionListener((java.awt.event.ActionEvent e) -> {
            if (jtfFacEscX.getText().equals("") || jtfFacEscY.getText().equals("")) {
                JOptionPane.showMessageDialog(Escalation.this, "Especifique la escala",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    facEscX = Double.parseDouble(jtfFacEscX.getText());
                    facEscY = Double.parseDouble(jtfFacEscY.getText());
                    jpnAreaDibujo.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Escalation.this, "Ingrese solo números",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        jpnOpciones.add(jbtEscalation);

        add(jpnOpciones, java.awt.BorderLayout.NORTH);
    }

    @Override
    public void cambiarFigura(java.awt.Shape shp) {
        shpFigura = shp;
        reiniciar();
        jpnAreaDibujo.repaint();
    }

    @Override
    public void dibujarFigura(java.awt.Graphics2D g2d) {
        g2d.scale(facEscX, facEscY);
        switch (apariencia) {
            case 0:
                sinRelleno(g2d);
                break;
            case 1:
                relleno(g2d);
                break;
            case 2:
                degradado(g2d);
                break;
            case 3:
                rellenoCnFig(g2d);
                break;
        }
    }

    private void sinRelleno(java.awt.Graphics2D g2d) {
        g2d.setPaint(Color.BLUE);
        g2d.draw(shpFigura);
    }

    @Override
    public void reiniciar() {
        facEscX = facEscY = 1.0;
        jtfFacEscX.setText("1.0");
        jtfFacEscY.setText("1.0");
    }
}
