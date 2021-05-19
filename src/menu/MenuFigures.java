package menu;

import javax.swing.ImageIcon;
import java.awt.Color;

public class MenuFigures {

    private final Menu menuFiguras;

    public MenuFigures(ChangeItemMenuListener cml) {
        final String[] str_Menus = {"Triángulo", "Rectángulo", "Polígono", "Círculo", "Texto"};
        ImageIcon imiMenus[] = new ImageIcon[str_Menus.length];
        final String str_NombresImg[] = {"triangulo.png", "rectangulo.png", "poligono.png", "circulo.png",
            "texto.png"};
        for (int i = 0; i < imiMenus.length; ++i) {
            imiMenus[i] = new ImageIcon(getClass().getResource("resource/" + str_NombresImg[i]));
        }

        menuFiguras = new Menu(str_Menus, imiMenus, new Color(0, 188, 212), new Color(205, 220, 57),
                new Color(156, 39, 176), true);
        menuFiguras.agregarManejador(cml);
    }

    public Menu getMenu() {
        return menuFiguras;
    }

    public void activarMenu(int indice) {
        menuFiguras.activarMenu(indice);
    }
}
