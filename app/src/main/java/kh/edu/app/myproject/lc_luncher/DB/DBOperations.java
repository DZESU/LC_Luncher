package kh.edu.app.myproject.lc_luncher.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import kh.edu.app.myproject.lc_luncher.datamodel.Casts;
import kh.edu.app.myproject.lc_luncher.datamodel.Food;

/**
 * Created by Mork Pin on 6/20/2017.
 */

public class DBOperations extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LC.db";
    public static final String TABLE_NAME = "tblLC";
    public static int totalPrice = 0;
    private Context context;

    public DBOperations(Context context) {

        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        Log.d("Database Operations", "Database is created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + "(USERNAME TEXT, DOB TEXT, GENDER TEXT, PHONE_NUMBER INT, PASSWORD TEXT );";
        db.execSQL(sql);
        db.execSQL("CREATE TABLE \"TABLE_FOOD\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , \"_food_name\" TEXT, \"_price\" TEXT, \"_thumbnail\" TEXT)");
        db.execSQL("CREATE TABLE \"TABLE_CAST\" (\"_id\" INTEGER PRIMARY KEY NOT NULL , \"_food_name\" TEXT, \"_price\" INTEGER,\"_quantity\" INTEGER, \"_thumbnail\" TEXT)");
        Log.d("Database Operations", "Table is created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }
/*
    public void AddToOrderedList(Casts casts, String username, String phoneNumber) {

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log.d("pory", User.getUserName() + "       " + User.getPhoneNumber());
        Log.d("Pory check ", username + "       " + phoneNumber);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues row = new ContentValues();

        row.put("_food_name", casts.getName());
        row.put("_price", casts.getPrice());
        row.put("_quantity", casts.getQuantity());
        row.put("_thumbnail", casts.getThumbnail());
        row.put("_date", currentDateTimeString);
        row.put("_username", username);
        row.put("_phone_number", phoneNumber);
        //row.put("_address",address);
        db.insert("TABLE_ORDERED_LIST", null, row);
    }

    public void AddToHistory(Casts casts) {

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log.d("pory", currentDateTimeString);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues row = new ContentValues();

        row.put("_name", casts.getName());
        row.put("_price", casts.getPrice());
        row.put("_quantity", casts.getQuantity());
        row.put("_thumbnail", casts.getThumbnail());
        row.put("_date", currentDateTimeString);
        //row.put("_address",address);
        db.insert("TABLE_HISTORY", null, row);
    }

    public History[] getAllHistory() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("TABLE_HISTORY", null, null, null, null, null, "_id desc");
        History[] histories = new History[cursor.getCount()];
        int index = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int quantity = cursor.getInt(3);
            String date = cursor.getString(4);
            String address = cursor.getString(5);
            String thumbnailUrl = cursor.getString(6);
            History history = new History(id, name, price, quantity, thumbnailUrl, date, address);

            histories[index] = history;
            index++;
        }
        return histories;
    }
*/
    public Casts[] getAllCasts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("TABLE_CAST", null, null, null, null, null, "_id desc");

        Casts[] castes = new Casts[cursor.getCount()];
        try{
            int index = 0;
            while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int quantity = cursor.getInt(3);
            String thumbnailUrl = cursor.getString(4);
            Casts cast = new Casts(id, name, price, quantity, thumbnailUrl);
            cast.setTotalPrice(price);
            castes[index] = cast;
            index++;
        }
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        return castes;
    }

    public boolean AddtoCast(Food food,int quantiy) {
        SQLiteDatabase db = getReadableDatabase();
        int checkID=-1;
        try {
            String[] col = {"_name"};
            String arg = "WHERE _name = ?";
            Cursor cursor = db.rawQuery("SELECT _id from TABLE_CAST where _id = '" + food.getId() + "'", null);
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                checkID = cursor.getInt(0);
            }
            cursor.close();
        } catch (Exception e) {
            Log.d("Pory","DB Error");
            e.printStackTrace();
        }
        Log.d("Pory", food.getId() + "     " + checkID+"");

        if(checkID == food.getId()){
            return false;
        }else{
            db = getWritableDatabase();
            ContentValues row = new ContentValues();
            row.put("_id", food.getId());
            row.put("_food_name", food.getFood());
            row.put("_price", food.getPrice());
            row.put("_quantity", quantiy);
            row.put("_thumbnail",food.getThumnbail());

            long result = db.insert("TABLE_CAST", null, row);
            Log.d("Pory", result + "");
            return true;
        }
    }

    public int UpdateQuantityCast(int food_id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _quantity from TABLE_CAST where _id= '" + food_id + "'", null);
        cursor.moveToNext();
        Log.d("Pory ", "cursor number: " + cursor.getInt(0));
        return cursor.getInt(0);
    }

    public void UpdataCast(int quantity, int food_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("_quantity", quantity);
        db.update("TABLE_CAST", row, "_id='" + food_id + "'", null);

    }

    public void DeleteFromCast(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("TABLE_CAST", "_id=" + id, null);
        Log.d("Pory","deleted from db");
    }

    public void DeleteFromTable(String tableName, String arg) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, arg, null);
    }

/*
    public Food[] getAllFood(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tableName, null, null, null, null, null, "_id desc");
        final Food[] foods = new Food[cursor.getCount()];
        int index = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String foodName = cursor.getString(1);
            int price = cursor.getInt(2);
            String thumbnailUrl = cursor.getString(3);
            Food food = new Food(id, foodName, price, thumbnailUrl,"");
            foods[index] = food;
            index++;
        }
        cursor.close();



        return foods;
    }

    public boolean insertData(String username, String dob, String gender, String Phonenumber, String password) {
        SQLiteDatabase SQ = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("_username", username);
        cv.put("_dob", dob);
        cv.put("_gender", gender);
        cv.put("_phone_number", Phonenumber);
        cv.put("_password", password);
        long result = SQ.insert(TABLE_NAME, null, cv);
        Log.d("Database Operations", "one row is inserted");
        if (result == -1)
            return false;
        else
            return true;
    }

    public void UpdateData(String username, String dob, String gender, String Phonenumber, String password) {


        SQLiteDatabase db = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("_username", username);
        row.put("_dob", dob);
        row.put("_gender", gender);
        row.put("_phone_number", Phonenumber);
        row.put("_password", password);
        db.update("tblLC", row, "_id=" + User.getId(), null);

    }

    public User login(String Phonenumber, String pw) {
        SQLiteDatabase SQ = getReadableDatabase();
        String condition = "_phone_number = ? and _password= ?";
        String[] conditionArguments = {Phonenumber, pw};
        Cursor cursor = SQ.query("tblLC", null, condition, conditionArguments, null, null, null);
        Log.d("ck", "asd");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            String usernName = cursor.getString(1);
            String dob = cursor.getString(2);
            String gender = cursor.getString(3);
            String phonenumber = cursor.getString(4);
            String password = cursor.getString(5);
            User user = new User(id, usernName, dob, gender, phonenumber, password);
            cursor.close();
            return user;
        } else {
            cursor.close();
            return null;
        }
    }

    public OrderedList[] getAllOrderedList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("TABLE_ORDERED_LIST", null, null, null, null, null, "_id desc");
        OrderedList[] orderedLists = new OrderedList[cursor.getCount()];
        int index = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String foodName = cursor.getString(1);
            int price = cursor.getInt(2);
            int quantity = cursor.getInt(3);
            String date = cursor.getString(4);
            String username = cursor.getString(5);
            String phoneNumber = cursor.getString(6);
            String address = cursor.getString(7);
            String thumbnailUrl = cursor.getString(8);
            OrderedList orderedList = new OrderedList(id, foodName, price, quantity, thumbnailUrl, date, address, phoneNumber, username);
            orderedLists[index] = orderedList;
            index++;
        }
        cursor.close();
        return orderedLists;
    }
*/

}
