package ba.unsa.etf.rpr.tutorijal7;

import java.io.Serializable;

public class Grad implements Serializable {
    //naziv (string), broj stanovnika (int) i temperature (običan niz od 1000 elemenata tipa double).
    private String naziv;
    private int brojStanovnika;
    private double[] temperature;
    public Grad(){
        setTemperature(new double[1000]);
    }
    public Grad(String naziv, int brojStanovnika, double[] temperature){
        this.naziv=naziv;
        this.brojStanovnika=brojStanovnika;
        this.temperature=temperature;
   }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }
}
