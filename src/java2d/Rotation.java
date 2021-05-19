package java2d;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Rotation extends Transformation {

    private JTextField jtfAngle;
    private javax.swing.JSlider jslAngle;
    private double conversionUnit;
    private double angle = 0;

    public Rotation(java.awt.Shape shp) {
        //Asignar figura inicial
        shpFigura = shp;

        //Area de opciones
        JPanel jpnOpciones = new JPanel(new BorderLayout());
        JPanel jpnAngulo = new JPanel();
        JPanel jpnCambio = new JPanel(new BorderLayout());
        jpnAngulo.add(new javax.swing.JLabel("Angulo = "));
        jtfAngle = new JTextField("0");
        jtfAngle.setColumns(10);
        jtfAngle.addActionListener((java.awt.event.ActionEvent e) -> {
            if (jtfAngle.getText().isEmpty()) {
                JOptionPane.showMessageDialog(Rotation.this, "Especifique el angulo de rotación",
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    int ang = Integer.parseInt(jtfAngle.getText());
                    if (ang < 0) {
                        ang *= (-1);
                        ang %= 360;
                        ang = 360 - ang;
                    } else if (ang > 0) {
                        ang %= 360;
                        if (ang == 0) {
                            ang = 360;
                        }
                    }
                    jslAngle.setValue(ang);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Rotation.this, "Ingrese solo numeros enteros",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        jpnAngulo.add(jtfAngle);
        jpnAngulo.add(new javax.swing.JLabel("Grados"));
        jslAngle = new javax.swing.JSlider(javax.swing.SwingConstants.HORIZONTAL, 0, 360, 0);
        jslAngle.setMajorTickSpacing(10);
        jslAngle.setPaintTicks(true);
        jslAngle.addChangeListener((javax.swing.event.ChangeEvent e) -> {
            int ang = jslAngle.getValue();
            jtfAngle.setText(String.valueOf(ang));
            angle = conversionUnit * ang;
            jpnAreaDibujo.repaint();
        });
        jpnCambio.add(jslAngle);
        jpnOpciones.add(jpnAngulo, BorderLayout.WEST);
        jpnOpciones.add(jpnCambio, BorderLayout.CENTER);

        //>Unidad de conversion de grados a radianes
        conversionUnit = (2 * Math.PI * (-1)) / 360;

        //>Configurar Trasladar
        add(jpnOpciones, BorderLayout.NORTH);
    }

    @Override
    public void cambiarFigura(java.awt.Shape shp) {
        shpFigura = shp;
        reiniciar();
        jpnAreaDibujo.repaint();
    }

    @Override
    public void dibujarFigura(java.awt.Graphics2D g2d) {
        g2d.rotate(angle);
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

    public void sinRelleno(java.awt.Graphics2D g2d) {
        g2d.setPaint(Color.BLUE);
        g2d.draw(shpFigura);
    }

    @Override
    public void reiniciar() {
        jslAngle.setValue(0);
    }
}
