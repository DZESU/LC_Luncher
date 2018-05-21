package kh.edu.app.myproject.lc_luncher.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import kh.edu.app.myproject.lc_luncher.DB.DBOperations;
import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.OnRecyclerViewItemClickListener;
import kh.edu.app.myproject.lc_luncher.R;
import kh.edu.app.myproject.lc_luncher.datamodel.Food;

/**
 * Created by user on 7/25/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Food[] foods;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public FoodAdapter(Context context) {
        foods = new Food[0];
        this.context = context;
    }

    public void setFoods(Food[] foods) {

        this.foods = foods;
        notifyDataSetChanged();
    }


    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_home, parent, false);
        FoodViewHolder foodViewHolder = new FoodViewHolder(view);
        return foodViewHolder;
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {

        try {
           // Log.d("pory", "Food name: " + foods[position].getFood());
           // Log.d("pory", " Price: " + foods[position].getPrice());
            holder.food.setText("" + foods[position].getFood());
            holder.price.setText("" + foods[position].getPrice() + "៛");
            holder.img_food.setImageUrl(foods[position].getThumnbail(), MySingleton.getInstance(context).getImageLoader());
            holder.imgThumbnail.setVisibility(View.INVISIBLE);
        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return foods.length;
    }


    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public Food getFood(int position) {
        return foods[position];
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgThumbnail;
        private TextView food;
        private TextView price;
        private ImageView btn_cast;
        private NetworkImageView img_food;

        public FoodViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_food);
            food = (TextView) itemView.findViewById(R.id.txt_food);
            price = (TextView) itemView.findViewById(R.id.txt_price);
            btn_cast = (ImageView) itemView.findViewById(R.id.btn_cast);
            img_food = (NetworkImageView) itemView.findViewById(R.id.img_food);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_loading);

            btn_cast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.getId() == R.id.btn_cast){
                        Log.d("Pory","test cast     "+foods[getAdapterPosition()].getFood());
                        DBOperations dbOperations = new DBOperations(context);
                        dbOperations.AddtoCast(foods[getAdapterPosition()],1);
                        onRecyclerViewItemClickListener.onRecyclerViewItemClick(getAdapterPosition(),view.getId());
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ckcc", "on view holder click");
                    if(view.getId() == R.id.btn_cast){
                        Log.d("Pory","Cast Clicked");
                    }else
                    onRecyclerViewItemClickListener.onRecyclerViewItemClick(getAdapterPosition(),view.getId());
                }
            });
        }
    }
}
