package kh.edu.app.myproject.lc_luncher.DB;

/**
 * Created by Mork Pin on 7/6/2017.
 */

public class User {
    public User(int id, String userName, String DOB, String gender, String phoneNumber, String passWord) {
        this.userName = userName;
        this.DOB = DOB;
        this.id = id;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;

    }

    private static String userName;
    private static String DOB;
    private static String gender;
    private static String phoneNumber;
    private static String passWord;
    private static int id;


    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }



    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static String getDOB() {
        return DOB;
    }

    public static void setDOB(String DOB) {
        User.DOB = DOB;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        User.gender = gender;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        User.phoneNumber = phoneNumber;
    }

    public static String getPassWord() {
        return passWord;
    }

    public static void setPassWord(String passWord) {
        User.passWord = passWord;
    }



}
