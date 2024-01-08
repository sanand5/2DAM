package app;

import utilidades.Colors;

public class app {
    public static void main(String[] args) {
        menus programa = new menus();
        try{
            programa.mainMenu();
        }catch (Exception e){
            Colors.errMsg("Error inesperado.");
        }
    }
}
