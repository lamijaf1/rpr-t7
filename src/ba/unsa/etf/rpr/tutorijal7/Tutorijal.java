package ba.unsa.etf.rpr.tutorijal7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {
    ArrayList<Grad> ucitajGradove(){
        Scanner ulaz;
        try {
            ulaz = new Scanner(new FileReader("mjerenje.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Datoteka mjerenje.txt ne postoji ili se ne može otvoriti");
        }
        ArrayList<Grad> listaGradova;


    }
    public static void main(String[] args) {
	// write your code here
    }
}
