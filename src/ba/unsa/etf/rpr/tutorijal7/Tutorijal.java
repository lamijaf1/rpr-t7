package ba.unsa.etf.rpr.tutorijal7;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {
    public static ArrayList<Double> Temperature(String temperature) {
        ArrayList<Double> temp = new ArrayList<Double>();
        for (int i = 0; i < temperature.length(); i++) {
            String vr = "";
            if (temperature.charAt(i) == ',') i++;
            while (temperature.charAt(i) != ',' && i != temperature.length()) {
                vr += temperature.charAt(i);
                i++;
            }

            double broj = Double.parseDouble(vr);
            temp.add(broj);
        }
        int k=0;
        for(k=temperature.length()-1;temperature.charAt(k)!=',';k--){}
        String zadnji="";
        while(k!=temperature.length()){
            zadnji+=temperature.charAt(k);
            k++;
        }
        double zadnjiBr=Double.parseDouble(zadnji);
        temp.add(zadnjiBr);
        return temp;
    }

    public static ArrayList<Grad> ucitajGradove() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileReader("mjerenje.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Datoteka mjerenje.txt ne postoji ili se ne može otvoriti");
        }
        ArrayList<Grad> listaGradova=new ArrayList<>();
        String nazivGrada = "";
        ArrayList<Double> tr=new ArrayList<>();
        try {
            while (ulaz.hasNext()) {
                String red = ulaz.nextLine();
                int brojac = 0;
                while (red.charAt(brojac) != ',') {
                    nazivGrada += red.charAt(brojac);
                    brojac++;
                }
                String t = red.substring(brojac + 1);
                for (int i = 0; i < t.length(); i++) {
                    String vr = "";
                    if (t.charAt(i) == ',') i++;
                    while (t.charAt(i) != ',' && i != t.length()-1) {
                        vr += t.charAt(i);
                        i++;
                    }
                    if(i==t.length()-1)vr+=t.charAt(i);
                    if(i==t.length())vr+=t.charAt(i);
                    double broj = Double.parseDouble(vr);
                    tr.add(broj);
                }
                double niz[]=new double[tr.size()];
                for(int d=0;d< tr.size();d++){
                    niz[d]=tr.get(d);
                }
                Grad noviGrad=new Grad();
                noviGrad.setNaziv(nazivGrada);
                noviGrad.setTemperature(niz);
                noviGrad.setBrojStanovnika(0);
                listaGradova.add(noviGrad);
                nazivGrada="";
                tr=new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Desilo se");
        }
        return listaGradova;
    }
    public static Drzava ucitajXml() {
        Drzava d= null;
        try {
            XMLDecoder ulaz = new XMLDecoder(new FileInputStream("drzave.xml"));
            d= (Drzava) ulaz.readObject();
            ulaz.close();
        } catch(Exception e) {
            System.out.println("Greska: "+e);
        }
        return d;
    }

    public static  void zapisiXml(Drzava d) {
        try {
            XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
            izlaz.writeObject(d);
            izlaz.close();
        } catch(Exception e) {
            System.out.println("Greska: "+e);
        }
    }



    public static void main(String[] args) {
        ArrayList<Grad> gradovi=ucitajGradove();
        for(int i=0;i<gradovi.size();i++){
            System.out.print(gradovi.get(i).getNaziv()+" ");
            double[] niz=gradovi.get(i).getTemperature();
            for(int j=0;j<niz.length;j++){
               if(j!=niz.length-1) System.out.print(niz[j]+",");
               else System.out.println(niz[j]);
            }
        }
    }
}


