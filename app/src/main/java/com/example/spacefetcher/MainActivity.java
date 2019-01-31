package com.example.spacefetcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<upComingLaunches> data_list;
    RequestQueue mqueue;



    TextView mission_nameTxt;
    TextView rocket_nameTxt;
    TextView launch_dateTxt;
    TextView launch_siteTxt;
    TextView customerTxt;
    TextView flight_number;
    TextView reusedFSTxt;
    TextView reusedFairingsTxt;
    TextView fairing_recovery_attemptTxt;


    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mqueue = Volley.newRequestQueue(this);
        data_list = new ArrayList<>();

        recyclerView = findViewById(R.id.rv);
        adapter = new RecyclerAdapter(this,data_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        flight_number = findViewById(R.id.flightNumTxt);
        mission_nameTxt = findViewById(R.id.missionNameTxt);
        rocket_nameTxt = findViewById(R.id.rocketNameTxt);
        launch_dateTxt = findViewById(R.id.launchDateTxt);
        launch_siteTxt = findViewById(R.id.launchSiteTxt);
        customerTxt = findViewById(R.id.customerTxt);
        reusedFSTxt = findViewById(R.id.reusedFSTxt);
        reusedFairingsTxt = findViewById(R.id.reused_fairingsTxt);
        fairing_recovery_attemptTxt = findViewById(R.id.fairing_recovery_attemptTxt);





        test = findViewById(R.id.test);



        jsonParse();


    }


    public void jsonParse() {
        String url = "https://api.spacexdata.com/v3/launches";

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = response.length()-1; i >= 0; i--) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        // Launch date
                        String launch_dateRaw = jsonObject.getString("launch_date_unix");
                        Long timestamp = Long.parseLong(launch_dateRaw);
                        Date date = new Date(timestamp * 1000L);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
                        String formattedDate = sdf.format(date);                                        // String


                        // Flight Information
                        String flight_number = jsonObject.getString("flight_number");
                        String mission_name = jsonObject.getString("mission_name");




                        // Mission image Patch
                        JSONObject imagePatchObj = jsonObject.getJSONObject("links");
                        String image_patch_link = imagePatchObj.getString("mission_patch_small");

                        //String image_patch_link = "hej";

                        // Launch site
                        JSONObject launchSiteObj = jsonObject.getJSONObject("launch_site");
                        String launch_site = launchSiteObj.getString("site_name");

                        //String launch_site = "hej";

                        // Fetch nested objects withing "rocket" array
                        JSONObject rocket = jsonObject.getJSONObject("rocket");
                        String rocket_name = rocket.getString("rocket_name");

                        //String rocket_name = "hej";
                        // First Stage
                        JSONObject first_stage = rocket.getJSONObject("first_stage");
                        JSONArray cores = first_stage.getJSONArray("cores");


                        // Cores // Reused
                        JSONObject reusedObj = cores.getJSONObject(0);
                        String reusedFS = reusedObj.getString("reused");

                        //String reusedFS = "hej";

                        // Landing intent
                        JSONObject landingObj = cores.getJSONObject(0);
                        String landing_intent = landingObj.getString("landing_intent");

                        //String landing_intent = "hej";

                        // Second stage
                        JSONObject second_stage = rocket.getJSONObject("second_stage");
                        JSONArray payload = second_stage.getJSONArray("payloads");


                        // Fairings
                        String reused_fairings = "";
                        if(!rocket.has("fairings")){
                            JSONObject fairings = rocket.getJSONObject("fairings");
                            reused_fairings = fairings.getString("reused");

                        }else{
                            reused_fairings = "";

                        }









                        // Customer
                        JSONObject customerObj = payload.getJSONObject(0);
                        String customerRaw = customerObj.getString("customers");
                        final String customer = customerRaw.substring(2, customerRaw.length()-2).replace("\"", "");

                        //String customer = "hej";


                        upComingLaunches data = new upComingLaunches(image_patch_link, flight_number, mission_name, rocket_name,
                                customer, launch_site, formattedDate, reusedFS, reused_fairings, landing_intent);

                        data_list.add(data);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mqueue.add(request);
        }




}
