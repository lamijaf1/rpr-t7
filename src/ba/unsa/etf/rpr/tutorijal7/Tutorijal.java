package ba.unsa.etf.rpr.tutorijal7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {
    public static ArrayList<Grad> ucitajGradove()throws FileNotFoundException{
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
                if (!nazivGrada.matches("[a-z A-Z]+")) throw new IllegalArgumentException("Neispravno ime grada");
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
                    if (vr.matches("[a-zA-Z]+")) throw new IllegalArgumentException("Pogresno unesene temperature!");
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
           // System.out.println("Desilo se");
        }
        return listaGradova;
    }
    public static void ucitajElemente(Element element, UN drzave) {
        System.out.print("Element "+element.getTagName()+", ");
        int brAtributa = element.getAttributes().getLength();
        System.out.print(brAtributa+" atributa");

        NodeList djeca = element.getChildNodes();

        // Ako nema djece ispisujemo sadrĹľaj
        if (djeca.getLength() == 1) {
            String sadrzaj = element.getTextContent();
            System.out.println(", sadrzaj: '" + sadrzaj + "'");
        } else {
            System.out.println("");
        }

        for(int i = 0; i < djeca.getLength(); i++) {
            Node dijete = djeca.item(i);
            if (dijete instanceof Element) {
                ucitajElemente((Element)dijete,drzave);
            }
        }

    }

    public static UN ucitajXml(ArrayList<Grad> gradovi)  {
        UN drzave = new UN();
        Document doc=null;
        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = docReader.parse(new File("drzave.xml"));
        } catch(Exception e) {
            System.out.println("Greska: "+e);
        }
         Element korijen = doc.getDocumentElement();
         ucitajElemente(korijen, drzave);
        NodeList drzaveNode =doc.getDocumentElement().getChildNodes();
        ArrayList<Drzava> spisak = new ArrayList<>();
        for(int i=0;i<drzaveNode.getLength();i++){
            Node d=drzaveNode.item(i);
            Drzava d1=new Drzava();
            if(d instanceof  Element){
                Element element=(Element)d;
               // getAttribute(attrName) - vraća vrijednost atributa čiji je naziv attrName ili prazan string ako ne postoji
                String s=element.getAttribute("brojStanovnika");
                int brSt=Integer.parseInt(s);
                d1.setBrojStanovnika(brSt);
                //getChildNodes() - vraća sve elemente koji su unutar ovog elementa
                NodeList nodelist=element.getChildNodes();
                for(int j=0;j<nodelist.getLength();j++){
                    Node djeca=nodelist.item(j);
                    if(djeca instanceof  Element){
                        Element element1=(Element)djeca;
                        if(element1.getTagName()=="naziv") d1.setNaziv(element1.getTextContent());
                        if(element1.getTagName()=="glavniGrad"){
                            Grad noviGrad=new Grad();
                            String s1=element1.getAttribute("brojStanovnika");
                            int brojSt=Integer.parseInt(s1);
                            noviGrad.setBrojStanovnika(brojSt);
                            NodeList nodelist1=element1.getChildNodes();
                            for(int k=0;k<nodelist1.getLength();k++){
                                Node djecaGrada=nodelist1.item(k);
                                if(djecaGrada instanceof  Element){
                                    Element element2=(Element)djecaGrada;
                                    if(element2.getTagName()=="naziv")noviGrad.setNaziv(element2.getTextContent());
                                    //getElementsByTagName(tagName) - vraća elemente unutar ovog čiji se tag zove kao parametar
                                    noviGrad.setTemperature(new double[0]);
                                    for(int c=0;c<gradovi.size();c++){
                                        if(noviGrad.getNaziv()==gradovi.get(c).getNaziv()) noviGrad.setTemperature(gradovi.get(c).getTemperature());
                                    }
                                    d1.setGlavniGrad(noviGrad);
                                }

                            }
                        }
                        if(element1.getTagName()=="povrsina") {
                            String s2=element1.getAttribute("jedinica");
                            double br=Double.parseDouble(s2);
                            d1.setPovrsina(br);
                        }
                    }
                }
                spisak.add(d1);
            }
        }
        drzave.setDrzava(spisak);
        return drzave;
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
        Drzava d=null;
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
            ucitajXml(gradovi);
            d=new Drzava("Bosna i Hercegovina",32434,3234234);
            zapisiXml(d);
            Drzava d1=new Drzava("Njemacka", 22223211, 121245465);
            zapisiXml(d1);


        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



