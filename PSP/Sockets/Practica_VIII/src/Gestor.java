import utilidades.Colors;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
public class Gestor {
    String path;
    File fl;
    Scanner scf;
    FileWriter fw;
    Colors cl = new Colors();


    public Gestor(String path){
        this.path = path;
        try{
            fl = new File(path);
            scf = new Scanner(new FileReader(fl));
            fw = new FileWriter(fl, false);
        }catch (Exception e){
            cl.errMsg(e.getMessage());
        }
    }

    public HashMap pull() {
        HashMap<String, String> map = new HashMap<>();
        if (scf != null){
            while (scf.hasNextLine()){
                String ln = scf.nextLine();
                String log[] = ln.split("#");
                map.put(log[0], log[1]);
            }
        }else {
            cl.warMsg("El archivo esta vacio");
        }
        return map;
    }

    public void push(HashMap capitales){
        capitales.forEach((pais, capital) -> {
            String ln = pais + "#" + capital;
            try {
                fw.write(ln + "\n");
            } catch (IOException e) {
                cl.errMsg(e.getMessage());
            }
        });
    }
}
