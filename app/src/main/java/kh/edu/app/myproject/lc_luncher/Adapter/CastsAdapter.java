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
import kh.edu.app.myproject.lc_luncher.datamodel.Casts;

import static kh.edu.app.myproject.lc_luncher.R.id.btn_miun;

/**
 * Created by user on 7/29/2017.
 */

public class CastsAdapter extends RecyclerView.Adapter<CastsAdapter.CastsViewHolder> {


    private Casts[] castes;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    public CastsAdapter(Context context){
        castes = new Casts[0];
        this.context = context;
    }
    public  void setCastes(Casts[] castes){
        this.castes = castes;
    }

    @Override
    public CastsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cast,parent,false);
        CastsViewHolder castsViewHolder= new CastsViewHolder(view);
        return castsViewHolder;
    }

    @Override
    public void onBindViewHolder(final CastsViewHolder holder, final int position) {

        final int pos = position;

        try{

//            Log.d("pory","Food name: "+castes[position].getName());
//            Log.d("pory"," Price: "+castes[position].getPrice());
            holder.name.setText(""+castes[position].getName());
            holder.price.setText(""+castes[position].getPrice()+"៛");
            holder.txt_quantity.setText(""+castes[position].getQuantity()+"ចាន");
            holder.imgThumbnail.setImageUrl(castes[position].getThumbnail(), MySingleton.getInstance(context).getImageLoader());

            final DBOperations dbOperations = new DBOperations(context);
            holder.btn_plus.setOnClickListener(new View.OnClickListener() {
                String name;
                int quantity;
                int food_id;
                @Override
                public void onClick(View view) {
                    //Log.d("pory", "btn plus clicked");
                    name = castes[position].getName();
                    quantity =  castes[position].getQuantity();
                    food_id = castes[position].getId();
                    switch (view.getId()){
                        case R.id.btn_plus:
                            quantity+=1;
                            dbOperations.UpdataCast(quantity,food_id);
                            castes[position].setQuantity(dbOperations.UpdateQuantityCast(food_id));
                            Log.d("Pory","plus button clicked at:   "+position+"and quantity = "+quantity);
                            holder.txt_quantity.setText(""+castes[position].getQuantity()+"ចាន");
                            break;
                    }
                    onRecyclerViewItemClickListener.onRecyclerViewItemClick(position,view.getId());

                    Log.d("pory", "btn plus clicked and quantity = "+quantity);
                }
            });
            holder.btn_miun.setOnClickListener(new View.OnClickListener() {
                String name;
                int food_id;
                int quantity;
                @Override
                public void onClick(View view) {
                    //Log.d("pory", "btn minue clicked");
                    name = castes[pos].getName();
                    quantity =  castes[pos].getQuantity();
                    food_id = castes[pos].getId();
                    switch (view.getId()){
                        case btn_miun:
                            quantity-=1;
                            dbOperations.UpdataCast(quantity,food_id);
                            castes[pos].setQuantity(dbOperations.UpdateQuantityCast(food_id));
                            if(castes[pos].getQuantity()<=0) {
                                dbOperations.DeleteFromCast(castes[pos].getId());
                                castes = dbOperations.getAllCasts();
                                notifyItemRemoved(pos);
                            }
                            else{
                                //Log.d("Pory", "mine button clicked at:   " + pos+ "and quantity = " + quantity);
                                holder.txt_quantity.setText("" + castes[pos].getQuantity() + "ចាន");
                            }
                            break;

                    }
                    onRecyclerViewItemClickListener.onRecyclerViewItemClick(pos,view.getId());
                    Log.d("pory", "btn plus clicked and quantity = "+quantity);
                }
            });


        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        if(castes.length>=0)
        return castes.length;
        else
            return 0;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public Casts getCasts(int position){
        return castes[position];
    }

    class CastsViewHolder extends  RecyclerView.ViewHolder{

        private NetworkImageView imgThumbnail;
        private TextView name;
        private TextView price;
        private TextView txt_quantity;
        private ImageView btn_plus;
        private ImageView btn_miun;
        public CastsViewHolder(View itemView )
        {
            super(itemView);
            imgThumbnail = (NetworkImageView) itemView.findViewById(R.id.icon_food);
            name = (TextView) itemView.findViewById(R.id.txt_name);
            price = (TextView) itemView.findViewById(R.id.txt_price);
            txt_quantity = (TextView) itemView.findViewById(R.id.txt_quantity);
            btn_miun = (ImageView) itemView.findViewById(R.id.btn_miun);
            btn_plus = (ImageView) itemView.findViewById(R.id.btn_plus);
//            final DBOperations dbOperations = new DBOperations(context);
//            btn_plus.setOnClickListener(new View.OnClickListener() {
//                String name;
//                int quantity;
//                @Override
//                public void onClick(View view) {
//                    Log.d("pory", "btn plus clicked");
//                    onRecyclerViewItemClickListener.onRecyclerViewItemClick(getAdapterPosition(),view.getId());
//                    name = castes[getAdapterPosition()].getName();
//                    quantity =  castes[getAdapterPosition()].getQuantity();
//                    switch (view.getId()){
//
//                        case R.id.btn_plus:
//                            quantity+=1;
//                            dbOperations.UpdataCast(quantity,name);
//                            castes[getAdapterPosition()].setQuantity(dbOperations.UpdateQuantityCast(name));
//                            Log.d("Pory","plus button clicked at:   "+getAdapterPosition()+"and quantity = "+quantity);
//                            txt_quantity.setText(""+castes[getAdapterPosition()].getQuantity()+"ចាន");
//                            break;
//                    }
//
//                }
//            });
//            btn_miun.setOnClickListener(new View.OnClickListener() {
//                String name;
//
//                int quantity;
//                @Override
//                public void onClick(View view) {
//                    Log.d("pory", "btn minue clicked");
//                    onRecyclerViewItemClickListener.onRecyclerViewItemClick(getAdapterPosition(),view.getId());
//                    name = castes[getAdapterPosition()].getName();
//                    quantity =  castes[getAdapterPosition()].getQuantity();
//
//
//                    switch (view.getId()){
//                        case R.id.btn_miun:
//                            quantity-=1;
//                            dbOperations.UpdataCast(quantity,name);
//                            castes[getAdapterPosition()].setQuantity(dbOperations.UpdateQuantityCast(name));
//                            if(castes[getAdapterPosition()].getQuantity()==0) {
//                                dbOperations.DeleteFromCast(castes[getAdapterPosition()].getId());
//
//                                castes = dbOperations.getAllCasts();
//                                notifyItemRemoved();
//                            }
//                            else{
//                                Log.d("Pory", "mine button clicked at:   " + getAdapterPosition() + "and quantity = " + quantity);
//                                txt_quantity.setText("" + castes[getAdapterPosition()].getQuantity() + "ចាន");
//                            }
//                            break;
//
//                    }
//                }
//            });

        }



    }

}
