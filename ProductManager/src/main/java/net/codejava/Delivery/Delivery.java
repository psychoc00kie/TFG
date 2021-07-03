package net.codejava.Delivery;


import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
public class Delivery {

    private int iddelivery;
    private int status;

    public Delivery() {
    }

    public Delivery(int iddelivery, int status) {
        this.iddelivery = iddelivery;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIddelivery() {
        return iddelivery;
    }

    public void setIddelivery(int iddelivery) {
        this.iddelivery = iddelivery;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "iddelivery=" + iddelivery +
                ", status=" + status +
                '}';
    }
}
