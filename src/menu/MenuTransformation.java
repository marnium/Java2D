package menu;

import javax.swing.ImageIcon;
import java.awt.Color;

public class MenuTransformation {

    Menu menuTransformacion;

    public MenuTransformation(ChangeItemMenuListener cml) {
        final String[] str_Menus = {"Traslación", "Escalamiento", "Rotación", "Relleno",
            "Degradado", "Figura"};
        ImageIcon imiMenus[] = new ImageIcon[str_Menus.length];
        final String str_NombresImg[] = {"traslacion.png", "escalamiento.png", "rotacion.png", "relleno.png",
            "degradado.png", "figura.png"};
        for (int i = 0; i < imiMenus.length; ++i) {
            imiMenus[i] = new ImageIcon(getClass().getResource("resource/" + str_NombresImg[i]));
        }

        menuTransformacion = new Menu(str_Menus, imiMenus, new Color(236, 236, 236), new Color(0, 188, 212),
                new Color(156, 39, 176), false);
        menuTransformacion.agregarManejador(cml);
    }

    public Menu getMenu() {
        return menuTransformacion;
    }

    public void activarMenu(int indice) {
        menuTransformacion.activarMenu(indice);
    }
}
