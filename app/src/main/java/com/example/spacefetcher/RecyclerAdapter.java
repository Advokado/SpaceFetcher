package com.example.spacefetcher;

import android.content.Context;
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
    private List<upComingLaunches> my_upcomingData;



    public RecyclerAdapter(Context context, List<upComingLaunches> my_data){

        this.context = context;
        this.my_upcomingData = my_data;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView flight_numberTxt;
        TextView mission_nameTxt;
        TextView rocket_nameTxt;
        TextView customerTxt;
        TextView launch_siteTxt;
        TextView launch_dateTxt;
        ImageView image_patch;
        TextView reusedFSTxt;
        TextView reusedFairingsTxt;
        TextView fairing_recovery_attemptTxt;
        TextView recovered_fairings_resultTxt;


        public ViewHolder(View itemView) {
            super(itemView);

            flight_numberTxt =  itemView.findViewById(R.id.flightNumTxt);
            mission_nameTxt =   itemView.findViewById(R.id.missionNameTxt);
            rocket_nameTxt =    itemView.findViewById(R.id.rocketNameTxt);
            customerTxt =       itemView.findViewById(R.id.customerTxt);
            launch_dateTxt =    itemView.findViewById(R.id.launchDateTxt);
            launch_siteTxt =    itemView.findViewById(R.id.launchSiteTxt);
            image_patch =       itemView.findViewById(R.id.image_patch);
            reusedFSTxt = itemView.findViewById(R.id.reusedFSTxt);
            reusedFairingsTxt = itemView.findViewById(R.id.reused_fairingsTxt);
            fairing_recovery_attemptTxt = itemView.findViewById(R.id.fairing_recovery_attemptTxt);




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

        holder.flight_numberTxt.setText(my_upcomingData.get(position).getFlight_number());
        holder.mission_nameTxt.setText(my_upcomingData.get(position).getMission_name());
        holder.rocket_nameTxt.setText(my_upcomingData.get(position).getRocket_name());
        holder.customerTxt.setText(my_upcomingData.get(position).getCustomer());
        holder.launch_dateTxt.setText(my_upcomingData.get(position).getLaunch_date());
        holder.launch_siteTxt.setText(my_upcomingData.get(position).getLaunch_site());

        // Reused first stage
        if(my_upcomingData.get(position).getReusedFS() == "null"){
            holder.reusedFSTxt.setText("N/A");
        }else if(my_upcomingData.get(position).getReusedFS() == "true"){
            holder.reusedFSTxt.setText("First stage have visited space before");
        }else{
            holder.reusedFSTxt.setText("Stage 1 have never visited space before");
        }

        // First stage landing intent
        if(my_upcomingData.get(position).getLandingIntent() == "false"){
            holder.fairing_recovery_attemptTxt.setText("and will not be recovered");
        }else if(my_upcomingData.get(position).getLandingIntent() == "true"){
            holder.fairing_recovery_attemptTxt.setText("and will attempt to land");
        }
        else{
            holder.fairing_recovery_attemptTxt.setText("N/A");
        }

        // Reused fairings
        if(my_upcomingData.get(position).getReusedFairings() == "true"){
            holder.reusedFairingsTxt.setText("Fairings has not been to space");
        }else if(my_upcomingData.get(position).getReusedFairings() == "false"){
            holder.reusedFairingsTxt.setText("Fairings have been to space!");
        }else{
            holder.reusedFairingsTxt.setText("N/A");
        }

        // Image patch
        if(my_upcomingData.get(position).getImage_patch_link() == "null"){
            Glide.with(context).load(R.drawable.patch_not_loaded2).into(holder.image_patch);
        }else{
            Glide.with(context).load(my_upcomingData.get(position).getImage_patch_link()).into(holder.image_patch);
        }



    }

    @Override
    public int getItemCount() {
        return my_upcomingData.size();
    }


}
