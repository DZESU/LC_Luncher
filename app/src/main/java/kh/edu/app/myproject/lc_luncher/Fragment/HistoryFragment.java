package kh.edu.app.myproject.lc_luncher.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kh.edu.app.myproject.lc_luncher.Adapter.HistoryAdapter;
import kh.edu.app.myproject.lc_luncher.DB.User;
import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.R;
import kh.edu.app.myproject.lc_luncher.datamodel.History;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends AppCompatActivity {


    public HistoryFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    HistoryAdapter adapter;
    TextView txt_totalPrice;

    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_history);

        Toolbar tlbMain = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(tlbMain);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("History");

//        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = (RecyclerView) findViewById(R.id.rclview_history);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       // DBOperations dbOperations = new DBOperations(this);
        //Casts[] castses = dbOperations.getAllCasts();
        //History[] histories = dbOperations.getAllHistory();
        adapter = new HistoryAdapter(this);

       loadDataFromServer();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }

    private void loadDataFromServer(){

        String url = "http://10.0.2.2/viewhistory.php?status=0&user_id="+User.getId();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                History[] histories = new History[response.length()];
                for(int i=0; i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("_id_order");
                        String name = jsonObject.optString("_food_name");
                        int price = jsonObject.getInt("_price");
                        int quantity = jsonObject.getInt("_quantity");
                        String thumbnail = jsonObject.getString("_thumbnail");
                        String date = jsonObject.getString("_date");
                        History history = new History(id,name,price,quantity,thumbnail,date);
                        histories[i]=history;
                        Log.d("Pory", "Check loaddata from history "+ history.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("Pory", "Check loaddata from history length "+ histories.length);
                Log.d("Pory", "Check loaddata UserID = "+ User.getId());
                adapter.setHistories(histories);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("pory",error.getMessage());

            }
        });

        MySingleton.getInstance(HistoryFragment.this).addToRequestQueue(jsonArrayRequest);

    }


}
