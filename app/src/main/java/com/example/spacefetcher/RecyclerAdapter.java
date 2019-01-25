package com.example.spacefetcher;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;


    public RecyclerAdapter(Context context, List<MyData> my_data){

        this.context = context;
        this.my_data = my_data;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mission_nameTxt;
        TextView rocket_nameTxt;
        TextView launch_siteTxt;
        TextView launch_dateTxt;
        TextView customerTxt;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);


            mission_nameTxt = (TextView) itemView.findViewById(R.id.missionNameTxt);
            //rocket_nameTxt = itemView.findViewById(R.id.rocketNameTxt);
            //launch_dateTxt = itemView.findViewById(R.id.launchDateTxt);
            //launch_siteTxt = itemView.findViewById(R.id.launchSiteTxt);
            //customerTxt = itemView.findViewById(R.id.customerTxt);




        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mission_nameTxt.setText(my_data.get(position).getMission_name());
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }
}
