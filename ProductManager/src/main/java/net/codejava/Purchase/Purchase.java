package net.codejava.Purchase;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
public class Purchase {

    private Long idpurchase;
    private Long product_id;
    private int units;
    private float purchase_price;
    private Long box_id;
    private Long courier_id;
    private String delivery_address;

    public Purchase() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdpurchase() {
        return idpurchase;
    }

    public void setIdpurchase(Long idpurchase) {
        this.idpurchase = idpurchase;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public float getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(float purchase_price) {
        this.purchase_price = purchase_price;
    }

    public Long getBox_id() {
        return box_id;
    }

    public void setBox_id(Long box_id) {
        this.box_id = box_id;
    }

    public Long getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(Long courier_id) {
        this.courier_id = courier_id;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }
}
