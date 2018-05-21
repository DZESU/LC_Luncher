package kh.edu.app.myproject.lc_luncher.datamodel;

/**
 * Created by user on 7/31/2017.
 */

public class OrderedList {

    public OrderedList(int id, String name, int price,int quantity, String thumbnail,String date, String address, String phoneNumber, String username){
        this.id = id;
        this.quantity = quantity;
        this.foodName= name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.date = date;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;

    }

    private String foodName;
    private String username;
    private String address;
    private String phoneNumber;
    private String date;
    private int price;
    private int id;
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    private int quantity;

}
