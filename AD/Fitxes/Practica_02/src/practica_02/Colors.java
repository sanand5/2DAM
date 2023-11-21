/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_02;

/**
 *
 * @author 2DAM_Sanz_Andreu
 */
public class Colors {

    public static final String WHITE_ANSI = "\u001B[37m";
    public static final String RESET_ANSI = "\u001B[0m";//"";
    public static final String BLACK_ANSI = "\u001B[30m";
    public static final String RED_ANSI = "\u001B[31m";
    public static final String GREEN_ANSI = "\u001B[32m";
    public static final String CYAN_ANSI = "\u001B[36m";
    public static final String YELLOW_ANSI = "\u001B[33m";
    public static final String BLUE_ANSI = "\u001B[34m";
    public static final String PURPLE_ANSI = "\u001B[35m";

    public static final String BLACK_BACKGROUND_ANSI = "\u001B[40m";
    public static final String RED_BACKGROUND_ANSI = "\u001B[41m";
    public static final String GREEN_BACKGROUND_ANSI = "\u001B[42m";
    public static final String YELLOW_BACKGROUND_ANSI = "\u001B[43m";
    public static final String BLUE_BACKGROUND_ANSI = "\u001B[44m";
    public static final String PURPLE_BACKGROUND_ANSI = "\u001B[45m";
    public static final String CYAN_BACKGROUND_ANSI = "\u001B[46m";
    public static final String WHITE_BACKGROUND_ANSI = "\u001B[47m";

    public static void errMsg(String msg) {
        System.out.println(RED_ANSI + "Error: " + msg + RESET_ANSI);
    }

    public static void warMsg(String msg) {
        System.out.println(CYAN_ANSI + "War: " + msg + RESET_ANSI);
    }

    public static void okMsg(String msg) {
        System.out.println(GREEN_ANSI + "OK: " + msg + RESET_ANSI);
    }

}
