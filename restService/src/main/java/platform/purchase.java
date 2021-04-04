package platform;

import java.util.Date;

public class purchase {

    int id;
    Date date;
    int _client;
    int _product;
    int units;
    double price;

    public purchase(int id, int _client, int _product, int units, double price) {

        this.id = id;
        this.date = new Date();
        this._client = _client;
        this._product = _product;
        this.units = units;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int get_client() {
        return _client;
    }

    public void set_client(int _client) {
        this._client = _client;
    }

    public int get_product() {
        return _product;
    }

    public void set_product(int _product) {
        this._product = _product;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "purchase{" +
                "id=" + id +
                ", date=" + date +
                ", _client=" + _client +
                ", _prouduct=" + _product +
                ", units=" + units +
                ", price=" + price +
                '}';
    }
}
