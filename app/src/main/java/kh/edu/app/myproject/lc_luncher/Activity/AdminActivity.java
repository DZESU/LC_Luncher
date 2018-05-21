package kh.edu.app.myproject.lc_luncher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.accountkit.AccountKit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kh.edu.app.myproject.lc_luncher.Adapter.OrderedListAdapter;
import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.R;
import kh.edu.app.myproject.lc_luncher.datamodel.OrderedList;

/**
 * Created by user on 7/31/2017.
 */



public class AdminActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    OrderedListAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        TextView btn_logout = (TextView)findViewById(R.id.btn_logout);
        Toolbar tlbMain = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(tlbMain);
        getSupportActionBar().setTitle(R.string.app_name);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountKit.logOut();

                launchMainActivity();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.rclview_orderlist);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//
//        DBOperations dbOperations = new DBOperations(this);
//        OrderedList[] orderedLists= dbOperations.getAllOrderedList();

        adapter = new OrderedListAdapter(this);
        loadDataFromServer();
//        adapter.setOrderedLists(orderedLists);
        recyclerView.setAdapter(adapter);

    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void loadDataFromServer(){

        String url = "http://10.0.2.2/admin_view.php?process=0&id_order=0";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                OrderedList[] orderedLists = new OrderedList[response.length()];
                for(int i=0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("_id_order");
                        String name = jsonObject.optString("_food_name");
                        int price = jsonObject.getInt("_price");
                        int quantity = jsonObject.getInt("_quantity");
                        String thumbnail = jsonObject.getString("_thumbnail");
                        String date = jsonObject.getString("_date");
                        String phone = jsonObject.getString("_phone_number");
                        String username = jsonObject.getString("_username");

                        OrderedList orderedList = new OrderedList(id,name,price,quantity,thumbnail,date,phone,username);
                        Log.d("Pory Admin","On respond "+orderedList.toString());
                        orderedLists[i]=orderedList;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } adapter.setOrderedLists(orderedLists);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("pory",error.getMessage());

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);

    }
}
