package ba.unsa.etf.rpr.tutorijal7;

import java.io.Serializable;

public class Drzava implements Serializable {
    private  String naziv;
    private  int brojStanovnika;
    private double povrsina;
    private String jedinica;
    private  Grad glavniGrad;
    public Drzava(){
        glavniGrad=new Grad();
    }
    public Drzava(String naziv, int brojStanovnika, double povrsina){
        this.setNaziv(naziv);
        this.setBrojStanovnika(brojStanovnika);
        this.setPovrsina(povrsina);
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
    public double getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(double povrsina) {
        this.povrsina = povrsina;
    }
}
