package java2d;

import java.awt.BorderLayout;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import menu.*;

public class Main extends javax.swing.JFrame {
    private Transformation tfmActive;
    private final MenuFigures menuFigures;
    private final MenuTransformation menuTransformations;
    private final Transformation[] transformations;
    private java.awt.Shape[] shapes;
    private java.awt.Shape shapeCurrent;

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }

    public Main() {
        super("Java 2D");

        //Crear Figuras
        shapes = new java.awt.Shape[5];
        shapes[0] = createTriangle();
        shapes[1] = createRectangle();
        shapes[2] = createPolygon();
        shapes[3] = createCircle();
        shapes[4] = createText();
        shapeCurrent = shapes[0];

        //Agregar menu figuras
        menuFigures = new MenuFigures((ChangeItemMenuEvent e) -> {
            shapeCurrent = shapes[e.getIndice()];
            tfmActive.cambiarFigura(shapeCurrent);
            tfmActive.jpnAreaDibujo.repaint();
        });
        add(menuFigures.getMenu(), BorderLayout.NORTH);
        menuFigures.activarMenu(0);

        //Agregar menu transformaciones
        menuTransformations = new MenuTransformation((ChangeItemMenuEvent e) -> {
            showPanel(e.getIndice());
        });
        add(menuTransformations.getMenu(), BorderLayout.WEST);
        menuTransformations.activarMenu(0);

        //Agregar panel de dibujo
        transformations = new Transformation[6];
        transformations[0] = new Translation(shapeCurrent);
        transformations[1] = new Escalation(shapeCurrent);
        transformations[2] = new Rotation(shapeCurrent);
        transformations[3] = new Fill(shapeCurrent);
        transformations[4] = new Degraded(shapeCurrent);
        transformations[5] = new Figure(shapeCurrent);
        tfmActive = transformations[0];
        add(tfmActive, BorderLayout.CENTER);

        //Configurar ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,
                java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
    }

    private java.awt.Shape createTriangle() {
        int[] corX = new int[3];
        int[] corY = new int[3];
        corX[0] = 0;
        corY[0] = -100;
        corX[1] = -100;
        corY[1] = 73;
        corX[2] = 100;
        corY[2] = 73;
        return new java.awt.Polygon(corX, corY, 3);
    }

    private java.awt.Shape createRectangle() {
        return new Rectangle2D.Double(-100.0, -50.0, 200.0, 100.0);
    }

    private java.awt.Shape createPolygon() {
        int[] corX = new int[5];
        int[] corY = new int[5];
        corX[0] = 0;
        corY[0] = -100;
        corX[1] = -95;
        corY[1] = -30;
        corX[2] = -58;
        corY[2] = 80;
        corX[3] = 58;
        corY[3] = 80;
        corX[4] = 95;
        corY[4] = -30;
        return new java.awt.Polygon(corX, corY, 5);
    }

    private java.awt.Shape createCircle() {
        return new Ellipse2D.Double(-100, -100, 200, 200);
    }

    private java.awt.Shape createText() {
        java.awt.Font f = new java.awt.Font("Times", java.awt.Font.BOLD, 50);
        FontRenderContext frc = new FontRenderContext(new java.awt.geom.AffineTransform(), true, false);
        TextLayout tl = new TextLayout("Java 2D", f, frc);
        return tl.getOutline(new java.awt.geom.AffineTransform());
    }

    private void showPanel(int i) {
        this.getContentPane().remove(tfmActive);
        tfmActive = transformations[i];
        tfmActive.reiniciar();
        this.add(tfmActive, BorderLayout.CENTER);
        this.validate();
        tfmActive.repaint();
    }
}
