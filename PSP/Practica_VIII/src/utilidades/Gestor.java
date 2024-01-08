package utilidades;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
public class Gestor {
    String path;
    File fl;
    FileWriter fw;
    Scanner scf;
    Colors cl = new Colors();


    public Gestor(String path) {
        this.path = path;
        try{
            fl = new File(path);
            if (!fl.exists()){
                fl.createNewFile();
            }
        }catch (NullPointerException | IOException e){
            cl.errMsg(e.getMessage());
        }
    }

    public HashMap pull(){
        HashMap<String, String> map = new HashMap<>();
        try {
            scf = new Scanner(new FileReader(fl));
            int cont = 0;
                while (scf.hasNextLine()) {
                    cont++;
                    String ln = scf.nextLine();
                    try {
                        String log[] = ln.split("#");
                        map.put(log[0].toLowerCase(), log[1].toLowerCase());
                    }catch (Exception e){
                        cl.warMsg("La linea "+cont+" esta vacia o no tiene el formato correcto");
                    }
                }
        }catch (FileNotFoundException e){
            cl.errMsg(e.getMessage());
        }finally {
            scf.close();
        }
        return map;
    }

    public void push(HashMap capitales){
        try {
            FileWriter fw = new FileWriter(fl, false);
            capitales.forEach((pais, capital) -> {
                    String ln = pais + "#" + capital;
                try {
                    fw.write(ln.toLowerCase() + "\n");
                } catch (IOException e) {
                    cl.errMsg(e.getMessage());
                }
            });
            fw.close();
        } catch (IOException e) {
            cl.errMsg(e.getMessage());
        }
    }
}
