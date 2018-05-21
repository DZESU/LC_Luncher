package kh.edu.app.myproject.lc_luncher.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import kh.edu.app.myproject.lc_luncher.OnRecyclerViewItemClickListener;
import kh.edu.app.myproject.lc_luncher.R;
import kh.edu.app.myproject.lc_luncher.datamodel.History;

/**
 * Created by user on 7/30/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private History[] histories;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public HistoryAdapter(Context context) {
        histories = new History[0];
        this.context = context;
    }

    public void setHistories(History[] histories) {
        this.histories = histories;

    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_history, parent, false);
        HistoryViewHolder historyViewHolder = new HistoryAdapter.HistoryViewHolder(view);
        return historyViewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.HistoryViewHolder holder, int position) {

        try {
            History potato = histories[position];
            holder.txt_name.setText("" + histories[position].getName());
            holder.txt_price.setText("តម្លៃ: " + histories[position].getPrice());
            holder.txt_quantity.setText("ចំនួន: " + histories[position].getQuantity());
            holder.txt_date.setText("កាលបរិច្ឆេទ: " + histories[position].getDate());

        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return histories.length;
    }

    public History getHistory(int position) {
        return histories[position];
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgThumbnail;
        private TextView txt_name;
        private TextView txt_price;
        private TextView txt_quantity;
        private TextView txt_date;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_food);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            txt_quantity = (TextView) itemView.findViewById(R.id.txt_quantity);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);

        }

    }
}
