package ba.unsa.etf.rpr.tutorijal7;

import java.io.Serializable;
import java.util.ArrayList;

public class UN implements Serializable {
    private ArrayList<Drzava> drzava;
    public UN(){
        setDrzava(new ArrayList<Drzava>());
    }
    public UN(ArrayList<Drzava> drzava){
        this.setDrzava(drzava);
    }

    public ArrayList<Drzava> getDrzava() {
        return drzava;
    }
    public void setDrzava(ArrayList<Drzava> drzava) {
        this.drzava = drzava;
    }
}
