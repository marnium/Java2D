package menu;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.JLabel;

public class Menu extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private final Color clrActivo;
    private final Color clrInactivo;
    private final Color clrCaptura;
    private ChangeItemMenuListener cmlManejador;
    private final ChangeItemMenuEvent cmeManejador;
    private ItemMenu[] imn_Menu;
    private ItemMenu imnActivo;

    public Menu(String[] str_items, ImageIcon[] imi_items, Color ca, Color ci, Color cc, boolean horizontal) {
        super(new java.awt.BorderLayout());

        clrActivo = ca;
        clrInactivo = ci;
        clrCaptura = cc;

        //>Evento
        cmeManejador = new ChangeItemMenuEvent();

        //>Menu
        java.awt.LayoutManager lym;
        if (horizontal) {
            lym = new FlowLayout(FlowLayout.LEFT, 12, 0);
        } else {
            lym = new java.awt.GridLayout(imi_items.length, 1, 0, 12);
        }
        agregarMenu(str_items, imi_items, lym);
    }

    public void agregarManejador(ChangeItemMenuListener cml) {
        cmlManejador = cml;
    }

    public void activarMenu(int indice) {
        imnActivo = imn_Menu[indice];
        imnActivo.activar();
    }

    private void agregarMenu(String[] str_itm, ImageIcon[] imi_itm, java.awt.LayoutManager lym) {
        //>Manejador de Menu
        MouseListener mslMenu = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ItemMenu imnActual = (ItemMenu) e.getSource();
                if (!imnActual.estaActivo()) {
                    imnActivo.desactivar();
                    imnActual.activar();
                    imnActivo = imnActual;
                    cmeManejador.setEvent(imnActual.indice);
                    cmlManejador.changeItemMenu(cmeManejador);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ItemMenu imnActual = (ItemMenu) e.getSource();
                if (!imnActual.estaActivo()) {
                    imnActual.setBackground(clrCaptura);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ItemMenu imnActual = (ItemMenu) e.getSource();
                if (!imnActual.estaActivo()) {
                    imnActual.setBackground(clrInactivo);
                }
            }
        };

        javax.swing.JPanel jpnMenu = new javax.swing.JPanel(lym);
        imn_Menu = new ItemMenu[str_itm.length];
        for (int i = 0; i < imn_Menu.length; ++i) {
            imn_Menu[i] = new ItemMenu(str_itm[i], imi_itm[i], i);
            imn_Menu[i].setBackground(clrInactivo);
            imn_Menu[i].addMouseListener(mslMenu);
            jpnMenu.add(imn_Menu[i]);
        }
        imnActivo = imn_Menu[0];
        jpnMenu.setBackground(clrInactivo);
        add(jpnMenu, java.awt.BorderLayout.CENTER);
    }

    class ItemMenu extends javax.swing.JPanel {

        private static final long serialVersionUID = 1L;
        public final int indice;
        private boolean activo;

        public ItemMenu(String strNombre, ImageIcon imi, int indice) {
            setBorder(javax.swing.BorderFactory.createSoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            add(new JLabel(strNombre, imi, JLabel.RIGHT));
            this.indice = indice;
        }

        public void activar() {
            setBackground(clrActivo);
            activo = true;
        }

        public void desactivar() {
            setBackground(clrInactivo);
            activo = false;
        }

        public boolean estaActivo() {
            return activo;
        }
    ;
}
}
