/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class Colors {

    public final String WHITE_ANSI = "\u001B[37m";
    public final String RESET_ANSI = "\u001B[0m";//"";
    public final String BLACK_ANSI = "\u001B[30m";
    public final String RED_ANSI = "\u001B[31m";
    public final String GREEN_ANSI = "\u001B[32m";
    public final String CYAN_ANSI = "\u001B[36m";
    public final String YELLOW_ANSI = "\u001B[33m";
    public final String BLUE_ANSI = "\u001B[34m";
    public final String PURPLE_ANSI = "\u001B[35m";

    public final String BLACK_BACKGROUND_ANSI = "\u001B[40m";
    public final String RED_BACKGROUND_ANSI = "\u001B[41m";
    public final String GREEN_BACKGROUND_ANSI = "\u001B[42m";
    public final String YELLOW_BACKGROUND_ANSI = "\u001B[43m";
    public final String BLUE_BACKGROUND_ANSI = "\u001B[44m";
    public final String PURPLE_BACKGROUND_ANSI = "\u001B[45m";
    public final String CYAN_BACKGROUND_ANSI = "\u001B[46m";
    public final String WHITE_BACKGROUND_ANSI = "\u001B[47m";

    public void errMsg(String msg) {
        System.out.println(RED_ANSI + "Error: " + msg + RESET_ANSI);
    }

    public void warMsg(String msg) {
        System.out.println(CYAN_ANSI + "War: " + msg + RESET_ANSI);
    }

    public void okMsg(String msg) {
        System.out.println(GREEN_ANSI + "OK: " + msg + RESET_ANSI);
    }

}
