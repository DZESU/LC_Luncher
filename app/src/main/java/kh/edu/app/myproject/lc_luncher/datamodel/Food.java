package kh.edu.app.myproject.lc_luncher.datamodel;

/**
 * Created by user on 7/25/2017.
 */

public class Food {

    private int id;
    private String food,content;
    private int price;
    public enum Category {
        FOOD,
        DRINK,
        DESSERT;
    }

    public Food(int id, String food, int price, String thumnbail,String content){

        this.id = id;
        this.food = food;
        this.price = price;
        this.content = content;
        this.thumnbail = thumnbail;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String thumnbail;



}
