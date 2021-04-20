package net.codejava.Repositories.Purchase;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
public class Purchase {

    private Long idpurchase;
    private Long buyer_id;
    private Long product_id;
    private int units;
    private float purchase_price;
    private Long box_id;
    private Long courier_id;
    private String delivery_address;

    public Purchase() {
    }

    public Purchase( Long idpurchase,Long buyer_id, Long product_id, int units, float purchase_price, Long box_id, Long courier_id, String delivery_address) {

        this.idpurchase = idpurchase;
        this.buyer_id = buyer_id;
        this.product_id = product_id;
        this.units = units;
        this.purchase_price = purchase_price;
        this.box_id = box_id;
        this.courier_id = courier_id;
        this.delivery_address = delivery_address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdpurchase() {
        return idpurchase;
    }

    public Long getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(Long buyer_id) {
        this.buyer_id = buyer_id;
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

    @Override
    public String toString() {
        return "Purchase{" +
                "idpurchase=" + idpurchase +
                ", buyer_id=" + buyer_id +
                ", product_id=" + product_id +
                ", units=" + units +
                ", purchase_price=" + purchase_price +
                ", box_id=" + box_id +
                ", courier_id=" + courier_id +
                ", delivery_address='" + delivery_address + '\'' +
                '}';
    }
}
