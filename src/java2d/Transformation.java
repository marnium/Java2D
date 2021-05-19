package java2d;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public abstract class Transformation extends javax.swing.JPanel {

    protected static java.awt.Shape shpFigura;
    protected static Color clrD1 = Color.BLUE;
    protected static Color clrD2 = Color.YELLOW;
    protected static Color clrR = Color.RED;
    protected static BufferedImage bfiImagen = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
    protected static java.awt.Image img;
    protected static int apariencia = 0;
    protected javax.swing.JPanel jpnAreaDibujo;

    public Transformation() {
        super(new java.awt.BorderLayout());

        //Area de dibujo
        jpnAreaDibujo = new javax.swing.JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);

                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;

                //>Dibujar plano cartesiano
                int w = getWidth();
                int h = getHeight();
                int cY0 = h / 2;
                int cX0 = w / 2;
                g2d.setFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.BOLD, 14));
                g2d.setPaint(Color.BLACK);
                g2d.draw(new Line2D.Double(cX0, 0, cX0, h)); //Eje Y
                g2d.draw(new Line2D.Double(0, cY0, w, cY0)); //Eje X
                int s = 25;
                int segX = w / (s * 2);
                int segY = h / (s * 2);
                g2d.setPaint(Color.LIGHT_GRAY);
                g2d.translate(cX0, cY0);
                for (int i = 1; i <= segX; i++) {
                    g2d.draw(new Line2D.Double(s * i, cY0 * (-1), s * i, cY0));
                    g2d.draw(new Line2D.Double(s * i * (-1), cY0 * (-1), s * i * (-1), cY0));
                }
                for (int i = 1; i <= segY; i++) {
                    g2d.draw(new Line2D.Double(cX0 * (-1), s * i, cX0, s * i));
                    g2d.draw(new Line2D.Double(cX0 * (-1), s * i * (-1), cX0, s * i * (-1)));
                }
                g2d.setPaint(Color.BLACK);
                g2d.drawString("X+", cX0 - 25, -10);
                g2d.drawString("Y+", 10, (-1) * cY0 + 15);

                //>Dibujar figura
                dibujarFigura(g2d);
            }
        };

        //>Configurar Trasladar
        add(jpnAreaDibujo, java.awt.BorderLayout.CENTER);
    }

    public abstract void cambiarFigura(java.awt.Shape shp);

    public abstract void dibujarFigura(java.awt.Graphics2D g2d);

    public abstract void reiniciar();

    protected void relleno(java.awt.Graphics2D g2d) {
        g2d.setPaint(clrR);
        g2d.fill(shpFigura);
    }

    protected void degradado(java.awt.Graphics2D g2d) {
        g2d.setPaint(new java.awt.GradientPaint(-100, -100, clrD1, 100, 50, clrD2, true));
        g2d.fill(shpFigura);
    }

    protected void rellenoCnFig(java.awt.Graphics2D g2d) {
        if (img == null) {
            g2d.setPaint(Color.BLUE);
            g2d.draw(shpFigura);
        } else {
            java.awt.Graphics2D gg = bfiImagen.createGraphics();
            gg.drawImage(img, 0, 0, 50, 50, null);
            g2d.setPaint(new java.awt.TexturePaint(bfiImagen, new java.awt.Rectangle(50, 50)));
            g2d.fill(shpFigura);
        }
    }
}
