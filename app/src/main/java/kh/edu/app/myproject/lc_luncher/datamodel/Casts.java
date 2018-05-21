package kh.edu.app.myproject.lc_luncher.datamodel;

/**
 * Created by user on 7/29/2017.
 */

public class Casts {

    public Casts(int id, String name, int price,int quantity, String thumnbail){
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.thumbnail = thumnbail;

    }

    private int id;


    public static int getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(int totalPrice) {
        Casts.totalPrice = totalPrice;
    }

    private static int totalPrice;
    private String name;
    private String thumbnail;
    private int price;
    private  int quantity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


}
