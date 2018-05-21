package kh.edu.app.myproject.lc_luncher.Fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kh.edu.app.myproject.lc_luncher.Adapter.FoodAdapter;
import kh.edu.app.myproject.lc_luncher.DB.DBOperations;
import kh.edu.app.myproject.lc_luncher.DB.User;
import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.OnRecyclerViewItemClickListener;
import kh.edu.app.myproject.lc_luncher.R;
import kh.edu.app.myproject.lc_luncher.datamodel.Food;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodsFragment extends Fragment implements OnRecyclerViewItemClickListener{

    RecyclerView recyclerView;
    FoodAdapter adapter;
    String category;

    public FoodsFragment(String category) {
        // Required empty pub
        Log.d("Pory","Check FoodFragment Constructor");
        this.category = category;
        // lic constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_foods, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rclview_food);

        Log.d("Pory","Check FoodFragment OnCreate");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FoodAdapter(getActivity());

        adapter.setOnRecyclerViewItemClickListener(this);

        loadDataFromServer();
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void loadDataFromServer() {
                Log.d("Pory","Start Listener");
        String url = "http://10.0.2.2/foodslist.php?tableName=tbl_food&category="+category;
        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("Pory","OnResponse");
                    Food[] foods = new Food[response.length()];
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("_id");
                       String foodName = jsonObject.getString("_food_name");
                        Log.d("Pory","OnResponse Check Food Name = "+foodName);
                        String thumbnail = jsonObject.getString("_thumbnail");
                        int price = jsonObject.getInt("_price");
                        String content = jsonObject.getString("_content");
                        Log.d("Pory","OnResponse Check = "+price);
                        Log.d("Pory","OnResponse Check = "+thumbnail);
                        Log.d("Pory","OnResponse Check = "+id);
                        Log.d("Pory","OnResponse Check  = "+content);


                        Food food = new Food(id,foodName,price,thumbnail,content);
                        foods[i]=food;

                    }adapter.setFoods(foods);
                }

                catch (JSONException e) {
                    Log.d("Pory","ErrorRespon 0 "+e.getLocalizedMessage());

                }
            }
        }; Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Loading data from server error", Toast.LENGTH_LONG).show();
                Log.d("Pory","ErrorRespon "+error.getLocalizedMessage());

            }
        };
        JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onRecyclerViewItemClick(int position,int idView) {
        //Log.d("Pory", "Cast button clicked");
        if (idView == R.id.btn_cast) {
            Food food = adapter.getFood(position);
            DBOperations dbOperations= new DBOperations(getActivity());

            boolean result = dbOperations.AddtoCast(food,1);
            Log.d("Pory","add to cast   "+result);
           Intent intent = new Intent(getActivity(), CastsFragment.class);
            intent.putExtra("phone", User.getPhoneNumber());
            startActivity(intent);

        } else {
            Food food = adapter.getFood(position);
            Intent intent = new Intent(getActivity(), FoodContentFragment.class);
            intent.putExtra("name", food.getFood());
            intent.putExtra("price", food.getPrice());
            intent.putExtra("id", food.getId());

            intent.putExtra("content", food.getContent());
            intent.putExtra("imageFood", food.getThumnbail());
            startActivity(intent);
        }
    }
}
