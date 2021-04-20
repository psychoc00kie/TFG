package net.codejava.Repositories.Courier;

import javax.persistence.*;

@Entity
@Table(name = "courier")
public class Courier {

    private int idcourier;
    private int zipcode;
    private String name;

    public Courier(){

    }

    public Courier(int idcourier, int zipcode, String name) {
        this.idcourier = idcourier;
        this.zipcode = zipcode;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdcourier() {
        return idcourier;
    }

    public void setIdcourier(int idcourier) {
        this.idcourier = idcourier;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "idcourier=" + idcourier +
                ", zipcode=" + zipcode +
                ", name='" + name + '\'' +
                '}';
    }
}

