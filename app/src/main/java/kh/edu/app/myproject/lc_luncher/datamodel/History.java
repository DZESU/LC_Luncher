package kh.edu.app.myproject.lc_luncher.datamodel;

/**
 * Created by user on 7/30/2017.
 */

public class History {

    public History(int id, String name, int price,int quantity, String thumbnail,String date){
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.date = date;

    }

    private String name;
    private String thumbnail;
    private int price;
    private  int quantity;

    private  int id;
    private String date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "name='" + name + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", id=" + id +
                ", date='" + date + '\'' +
                '}';
    }
}
