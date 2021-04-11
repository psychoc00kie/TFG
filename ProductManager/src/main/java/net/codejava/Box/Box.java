package net.codejava.Box;


import javax.persistence.*;

@Entity
@Table(name = "box")
public class Box {

    private Long id_user;
    private Long id_owner;
    private String address;
    private String ip;

    public Box() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getId_owner() {
        return id_owner;
    }

    public void setId_owner(Long id_owner) {
        this.id_owner = id_owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
