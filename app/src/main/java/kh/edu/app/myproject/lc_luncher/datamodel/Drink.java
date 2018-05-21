package kh.edu.app.myproject.lc_luncher.datamodel;

/**
 * Created by user on 7/29/2017.
 */

public class Drink {private int id;
    private String drink;
    private int price;

    public Drink(int id, String drink, int price, String thumnbail){
        this.id = id;
        this.drink = drink;
        this.price = price;
        this.thumnbail = thumnbail;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFood() {
        return drink;
    }

    public void setFood(String food) {
        this.drink = food;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getThumnbail() {
        return thumnbail;
    }

    public void setThumnbail(String thumnbail) {
        this.thumnbail = thumnbail;
    }

    private String thumnbail;
}
