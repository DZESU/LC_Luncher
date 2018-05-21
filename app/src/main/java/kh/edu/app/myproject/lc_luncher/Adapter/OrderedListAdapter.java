package kh.edu.app.myproject.lc_luncher.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kh.edu.app.myproject.lc_luncher.DB.DBOperations;
import kh.edu.app.myproject.lc_luncher.OnRecyclerViewItemClickListener;
import kh.edu.app.myproject.lc_luncher.R;
import kh.edu.app.myproject.lc_luncher.datamodel.OrderedList;

/**
 * Created by user on 7/31/2017.
 */

public class OrderedListAdapter extends RecyclerView.Adapter<OrderedListAdapter.OrderedListViewHolder> {

    private OrderedList[] orderedLists;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public OrderedListAdapter(Context context) {
        orderedLists = new OrderedList[0];
        this.context = context;
    }

    public void setOrderedLists(OrderedList[] orderedLists) {
        this.orderedLists = orderedLists;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
    @Override
    public OrderedListAdapter.OrderedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_admin_list_order, parent, false);
        OrderedListAdapter.OrderedListViewHolder orderedListViewHolder= new OrderedListAdapter.OrderedListViewHolder(view);
        return orderedListViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderedListAdapter.OrderedListViewHolder holder, int position) {

        try {
            Log.d("fuck this shit3","Price "+orderedLists[position].getPrice());
            Log.d("fuck this shit3","Quantity "+orderedLists[position].getQuantity());
            holder.txt_user.setText(""+orderedLists[position].getUsername());
            holder.txt_name.setText("" + orderedLists[position].getFoodName());

            holder.txt_quantity.setText("ចំនួន: " + orderedLists[position].getQuantity());
            holder.txt_date.setText("កាលបរិច្ឆេទ: " + orderedLists[position].getDate());
            holder.txt_phnoneNumber.setText(""+orderedLists[position].getPhoneNumber());
//            holder.txt_address.setText(""+orderedLists[position].getAddress());
            holder.txt_price.setText("តម្លៃ: " + orderedLists[position].getPrice());
            Log.d("fuck this shit",orderedLists[position].getUsername());
            Log.d("fuck this shit1",orderedLists[position].getUsername());
            Log.d("fuck this shit2",orderedLists[position].getUsername());
            Log.d("fuck this shit3",orderedLists[position].getUsername());

        }
        catch (Exception e) {
            Log.d("Error",e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return orderedLists.length;
    }
    public OrderedList getOrderedList(int position) {
        return orderedLists[position];
    }

    class OrderedListViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        TextView txt_price;
        TextView txt_quantity;
        TextView txt_address;
        TextView txt_phnoneNumber;
        TextView txt_user;
        TextView txt_date;
        Button btn_delivered;

        public OrderedListViewHolder(View itemView) {
            super(itemView);
            txt_user = (TextView) itemView.findViewById(R.id.txt_username);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_quantity = (TextView) itemView.findViewById(R.id.txt_quantity);
            txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            txt_phnoneNumber = (TextView) itemView.findViewById(R.id.txt_phoneNumber);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_address = (TextView) itemView.findViewById(R.id.txt_arddess);
            btn_delivered = (Button) itemView.findViewById(R.id.btn_delivered);

            btn_delivered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBOperations db = new DBOperations(context);
                    db.DeleteFromTable("TABLE_ORDERED_LIST","_id="+orderedLists[getAdapterPosition()].getId());
                    Log.d("fuck button shit3","clicked");
                }
            });

        }


    }

}
