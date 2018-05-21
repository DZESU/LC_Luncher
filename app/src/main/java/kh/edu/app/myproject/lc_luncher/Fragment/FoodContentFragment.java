package kh.edu.app.myproject.lc_luncher.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodContentFragment extends AppCompatActivity {


    private String foodName,imageUrl,content;
    private int id,price;

    public FoodContentFragment() {
//        int id, String foodName,int price,String imageUrl,String content
        // Required empty public constructor
        this.id = id;
        this.foodName = foodName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.content = content;
    }


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_food_content);

        TextView txt_name = (TextView) findViewById(R.id.txt_name);
        TextView txt_price = (TextView) findViewById(R.id.txt_price);
        TextView txt_content = (TextView) findViewById(R.id.txt_content);
        NetworkImageView img_food = (NetworkImageView) findViewById(R.id.img_food);

        txt_content.setText(getIntent().getStringExtra("content"));
        Log.d("Pory","Content check"+getIntent().getStringExtra("content"));
        txt_name.setText(getIntent().getStringExtra("name"));
        txt_price.setText(getIntent().getIntExtra("price",0)+"៛");
        img_food.setImageUrl(getIntent().getStringExtra("imageFood"), MySingleton.getInstance(this).getImageLoader());



    }

}
