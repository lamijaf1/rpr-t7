package ba.unsa.etf.rpr.tutorijal7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {
    public ArrayList<Double> Temperature(String temperature) {
        ArrayList<Double> temp = new ArrayList<Double>();
        int brojac = 0;
        for (int i = 0; i < temperature.length(); i++) {
            String vr = "";
            while (temperature.charAt(i) != ',' && i < temperature.length()) {
                vr += temperature.charAt(i);
                i++;
            }
            double broj = Double.parseDouble(vr);
            temp.add(broj);
        }
        return temp;
    }

    ArrayList<Grad> ucitajGradove() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileReader("mjerenje.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Datoteka mjerenje.txt ne postoji ili se ne može otvoriti");
        }
        ArrayList<Grad> listaGradova=new ArrayList<>();
        String nazivGrada = "";
        ArrayList<Double> temp;
        try {
            while (ulaz.hasNext()) {
                String red = ulaz.nextLine();
                int brojac = 0;
                while (red.charAt(brojac) != ',') {
                    nazivGrada += red.charAt(brojac);
                    brojac++;
                }
                String temperature = red.substring(brojac + 1, red.length());
                temp = Temperature(temperature);
                double niz[]=new double[temp.size()];
                for(int i=0;i< temp.size();i++){
                    niz[i]=temp.get(i);
                }
                Grad noviGrad=new Grad();
                noviGrad.setNaziv(nazivGrada);
                noviGrad.setTemperature(niz);
                listaGradova.add(noviGrad);
                nazivGrada="";
            }
        } catch (Exception e) {
        }
        return listaGradova;
    }


    public static void main(String[] args) {
        // write your code here
    }
}


