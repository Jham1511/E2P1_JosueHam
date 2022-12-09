package e2p1_josueham;

import java.util.*;

public class MaquinaEstados {

    private ArrayList<String> estados = new ArrayList<>();
    private ArrayList<String> estados_aceptacion = new ArrayList<>();
    private ArrayList<String> aristas = new ArrayList<>();
    private String estado_actual;

    public MaquinaEstados(String estados, String aristas) {
        this.estados = splitStr(estados, ';');
        extractAcceptNodes();
        this.aristas = splitStr(aristas, ';');
        this.estado_actual = this.estados.get(0);
    }

    public ArrayList<String> splitStr(String cad, char delim) {
        String[] separar = cad.split(Character.toString(delim));
        ArrayList<String> cadenas = new ArrayList<>();

        for (int i = 0; i < separar.length; i++) {
            cadenas.add(separar[i]);
        }
        return cadenas;
    }//Fin metodo splitStr

    public MaquinaEstados() {
    }

    public void extractAcceptNodes() {
        String sub = "";

        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).contains(".")) {
                sub = estados.get(i).substring(1);
                estados_aceptacion.add(sub);
                estados.set(i, sub);
            }
            
        }
    }//Fin accept nodes

    public String computeStr(String str) {
        String output = "";
        estado_actual = estados.get(0);
        for (int i = 0; i < str.length(); i++) {
            String ar = getArista(estado_actual + ',' + str.charAt(i));
            if (!ar.equals("")) {
                estado_actual = ar.split(",")[2];
                output += ar.split(",")[0] + ':' + str.charAt(i) + "->" + ar.split(",")[2] + '\n';
            } else {
                output += "Rechazada\n";
                return output;
            }
        }
        if (estados_aceptacion.contains(estado_actual)){
            output += "Aceptada\n";
                return output;
        } else {
            output += "Rechazada\n";
                return output;
        }
    }//Fin metodo compute

    public String getArista(String str) {

        for (int i = 0; i < aristas.size(); i++) {
            if (aristas.get(i).contains(str)) {
                return aristas.get(i);
            }
        }

        return "";
    }
}//Fin de la clase
