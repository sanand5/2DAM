/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;
/**
 *
 * @author andre
 */
public class Matriculas {

    ReadClient rc = new ReadClient();

    public void menu() {

        int menu;
        String nia = rc.pedirString("Nia del alumne: ");
        int posNia = Practica_02.alumnesList.buscarNia(nia);
        if (posNia != -1) {
            Alumne a = Practica_02.alumnesList.list.get(posNia);
            String modul = rc.pedirString("Modul a qualificar: ");
            int posModul = a.buscarModul(modul);
            if (posModul != -1) {
                Matricula matr = a.modulsList.get(posModul).matr;
                boolean repetir = true;
                while (repetir) {
                    System.out.println("""
                                   Menu Avaluar
                                   (0) Salir
                                   (1) Qualificar
                                   (2) Modificar
                                   (3) Traure bolletí de notes""");
                    menu = rc.pedirInteger("?");
                    switch (menu) {
                        case 0:
                            repetir = false;
                            System.out.println("Has ixit del menu Avaluar");
                            break;
                        case 1:
                            qualificar(matr);
                            break;
                        case 2:
                            modificar(matr);
                            break;
                        case 3:
                            mostrar();
                            break;
                        default:
                            Colors.warMsg("Deus introduir un valor valid");
                    }
                }
            } else {
                Colors.errMsg("No es posible avaluar al alumne perque no esta donat de alta en eixe modul");
            }

        } else {
            Colors.errMsg("No es posible avaluar al alumne perque no esta donat de alta");
        }

    }

    public void qualificar(Matricula matr) {
        int cant = rc.pedirInteger("Quantes notes vols afegir: ");
        for (int i = 0; i < cant; i++) {
            double nota = rc.pedirDouble("Nota a afegir: ", 0.0, 10.0);
            matr.addNota(nota);
            Colors.okMsg("La nota s'ha afegit");
        }
    }

    public void modificar(Matricula matr) {
        if (matr.mostrarNotes(true) != -1) {
            int pos = rc.pedirInteger("Quina nota vols afegir: ") - 1;
            double nota = rc.pedirDouble("Disme la nota que vols ficar: ", 0.0, 10.0);
            matr.setNota(nota, pos);
        }
    }

    public void mostrar() {
        //TODO sout "llista de matricules"
        for (int i = 0; i < Practica_02.alumnesList.list.size(); i++) {
            Practica_02.alumnesList.list.get(i).mostrar();
        }
    }
}
