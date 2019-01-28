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
import com.bumptech.glide.Glide;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;


    public RecyclerAdapter(Context context, List<MyData> my_data){

        this.context = context;
        this.my_data = my_data;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView flight_numberTxt;
        TextView mission_nameTxt;
        TextView rocket_nameTxt;
        TextView customerTxt;
        TextView launch_siteTxt;
        TextView launch_dateTxt;
        ImageView image_patch;


        public ViewHolder(View itemView) {
            super(itemView);

            flight_numberTxt =  itemView.findViewById(R.id.flightNumTxt);
            mission_nameTxt =   itemView.findViewById(R.id.missionNameTxt);
            rocket_nameTxt =    itemView.findViewById(R.id.rocketNameTxt);
            customerTxt =       itemView.findViewById(R.id.customerTxt);
            launch_dateTxt =    itemView.findViewById(R.id.launchDateTxt);
            launch_siteTxt =    itemView.findViewById(R.id.launchSiteTxt);
            image_patch =       itemView.findViewById(R.id.image_patch);




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

        holder.flight_numberTxt.setText(my_data.get(position).getFlight_number());
        holder.mission_nameTxt.setText(my_data.get(position).getMission_name());
        holder.rocket_nameTxt.setText(my_data.get(position).getRocket_name());
        holder.customerTxt.setText(my_data.get(position).getCustomer());




        holder.launch_dateTxt.setText(my_data.get(position).getLaunch_date());
        holder.launch_siteTxt.setText(my_data.get(position).getLaunch_site());

        Glide.with(context).load(my_data.get(position).getImage_patch_link()).into(holder.image_patch);


    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }


}
