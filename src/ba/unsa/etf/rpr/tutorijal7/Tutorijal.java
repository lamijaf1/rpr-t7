package ba.unsa.etf.rpr.tutorijal7;

import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {
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
        ulaz.useDelimiter("\n");
        try {
            while (ulaz.hasNext()) {
                String red = ulaz.nextLine();
                String[] podaci = red.split(",");
                nazivGrada= podaci[0];
                int brojac=nazivGrada.length();
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
    public static UN ucitajXml(ArrayList<Grad> gradovi) {
        UN d= null;
        try {
            XMLDecoder ulaz = new XMLDecoder(new FileInputStream("drzave.xml"));
            d= (UN) ulaz.readObject();
            ulaz.close();
        } catch(Exception e) {
            System.out.println("Greska: "+e);
        }
        ucitajGradove();
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
        try{
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
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



