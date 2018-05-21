package kh.edu.app.myproject.lc_luncher.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import kh.edu.app.myproject.lc_luncher.Adapter.CastsAdapter;
import kh.edu.app.myproject.lc_luncher.DB.DBOperations;
import kh.edu.app.myproject.lc_luncher.DB.User;
import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.OnRecyclerViewItemClickListener;
import kh.edu.app.myproject.lc_luncher.R;
import kh.edu.app.myproject.lc_luncher.datamodel.Casts;

/**
 * A simple {@link Fragment} subclass.
 */
public class CastsFragment extends AppCompatActivity implements OnRecyclerViewItemClickListener {


    public CastsFragment() {
        Log.d("Pory","CastFragment constructor");
        // Required empty public constructor
    }

    Casts[] castses;

    DBOperations dbOperations;
    RecyclerView recyclerView;
    CastsAdapter adapter;
    TextView txt_totalPrice;

    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_casts);

        Toolbar tlbMain = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(tlbMain);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cart");

        recyclerView = (RecyclerView) findViewById(R.id.rclview_food);

        Log.d("Pory","CastFragment OnCreate");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

         dbOperations = new DBOperations(this);
         castses = dbOperations.getAllCasts();

        adapter = new CastsAdapter(this);
        adapter.setOnRecyclerViewItemClickListener(this);
        adapter.setCastes(castses);
        recyclerView.setAdapter(adapter);
        phone = getIntent().getStringExtra("phone");

        Log.d("Pory","Phone at cast "+ phone);

        txt_totalPrice = (TextView) findViewById(R.id.txt_total);
        TextView btn_buy = (TextView) findViewById(R.id.btn_buy);


        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://10.0.2.2/order_products.php";
                //final Context context =this;
                dbOperations = new DBOperations(CastsFragment.this);

                for(final Casts casts : castses) {
                    Log.d("Pory",casts.getName()+"  "+casts.getPrice()+"    "+casts.getQuantity()+" "+casts.getThumbnail()+" "+User.getPassWord() + "   " +phone+"  "+User.getUserName());
                    final Toast toast = new Toast(CastsFragment.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Pory",response);
                           if(response.contains("ok")){
                               Toast.makeText(CastsFragment.this,"Keep Calm and wait your Order",Toast.LENGTH_LONG).show();
                               dbOperations.DeleteFromCast(casts.getId());
                               recyclerView.setVisibility(View.INVISIBLE);
                               txt_totalPrice.setText("0៛");
                           }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Pory",error.getMessage().toString());

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();

                            params.put("user_id",User.getId()+"");
                            params.put("product_id",casts.getId()+"");
                            params.put("quantity",casts.getQuantity()+"");
                            params.put("date",getDateTime()+"");
                            params.put("status","0");

                            return params;
                        }
                    };
                    MySingleton.getInstance(CastsFragment.this).addToRequestQueue(stringRequest);
//                    dbOperations.AddToHistory(casts);
//                    dbOperations.AddToOrderedList(casts, User.getUserName(),User.getPhoneNumber());
//                    dbOperations.DeleteFromTable("TABLE_CAST",null);
//                    Toast.makeText(getActivity(), "Keep calm and wait for your order", Toast.LENGTH_LONG).show();
                }
            }
        });


        int total = CalculateTotal();
        //Log.d("Pory","check total "+CalculateTotal(index));
        txt_totalPrice.setText(total+"៛");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Log.d("Pory","Check Date: "+dateFormat.format(date));
        return dateFormat.format(date);
    }

    private int CalculateTotal(){
        int total=0;
        int count = adapter.getItemCount();
        //Log.d("Pory","check index   "+count);
        while (count>0){
            Log.d("Pory","check quantity   "+adapter.getCasts(count-1).getQuantity());
            Log.d("Pory","price "+adapter.getCasts(count-1).getPrice()+"   quanity =  "+ adapter.getCasts(count-1).getQuantity());
            total+= adapter.getCasts(count-1).getPrice()*adapter.getCasts(count-1).getQuantity();
            Log.d("Pory","total = "+total);
            count--;
        }
        //Log.d("Pory","check total = "+total);
        return total;
    }

    @Override
    public void onRecyclerViewItemClick(int position,int idView) {

        txt_totalPrice.setText(CalculateTotal()+"៛");

    }
}
