package java2d;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Figure extends Transformation {

    private JFileChooser jfcImagen;
    private final javax.swing.JRadioButton jrbActive;
    private boolean isActiveFigure = false;

    public Figure(java.awt.Shape shp) {
        shpFigura = shp;

        //Panel opciones
        javax.swing.JPanel jpnOpc = new javax.swing.JPanel();
        jpnOpc.add(new javax.swing.JLabel("Rellenar figura con imagen, "));
        javax.swing.JButton jbtImagen = new javax.swing.JButton("Elegir imagen");
        jpnOpc.add(jbtImagen);
        jbtImagen.addActionListener((java.awt.event.ActionEvent e) -> {
            int returnVal = jfcImagen.showOpenDialog(Figure.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String nameImage = jfcImagen.getSelectedFile().getAbsolutePath();
                javax.swing.ImageIcon imi = new javax.swing.ImageIcon(nameImage);
                img = imi.getImage();
                repaint();
            }
        });
        jpnOpc.add(jrbActive = new javax.swing.JRadioButton("Mantener Figura para las transformaciones", isActiveFigure));
        jrbActive.addItemListener((java.awt.event.ItemEvent e) -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                isActiveFigure = true;
                apariencia = 3;
            } else if (isActiveFigure) {
                apariencia = 0;
            }
        });
        jfcImagen = new JFileChooser();

        //>Filtros de imagen
        FileNameExtensionFilter fneFiltro = new FileNameExtensionFilter("Imagen (jpg, gif, png)", "jpg", "gif", "png");
        jfcImagen.setFileFilter(fneFiltro);

        //>Agregar panel de opcion al panel principal
        add(jpnOpc, java.awt.BorderLayout.NORTH);
    }

    @Override
    public void cambiarFigura(java.awt.Shape shp) {
        shpFigura = shp;
        jpnAreaDibujo.repaint();
    }

    @Override
    public void dibujarFigura(java.awt.Graphics2D g2d) {
        if (apariencia != 3) {
            isActiveFigure = false;
            jrbActive.setSelected(isActiveFigure);
        }
        rellenoCnFig(g2d);
    }

    @Override
    public void reiniciar() {
    }
}
