package com.example.spacefetcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<MyData> data_list;
    RequestQueue mqueue;



    TextView mission_nameTxt;
    TextView rocket_nameTxt;
    TextView launch_dateTxt;
    TextView launch_siteTxt;
    TextView customerTxt;
    TextView flight_number;


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



        jsonParse();
    }


    public void jsonParse() {
        String url = "https://api.spacexdata.com/v3/launches";
        final int y = 20;

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++)
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String flight_number = jsonObject.getString("flight_number");
                        String mission_name = jsonObject.getString("mission_name");

                        // Launch date
                        String launch_dateRaw = jsonObject.getString("launch_date_local");
                        String launch_date = launch_dateRaw.substring(0, launch_dateRaw.length()-15).replace(" \"", "");

                        Date todaysDate = Calendar.getInstance().getTime();




                        // Mission image Patch
                        JSONObject imagePatchObj = jsonObject.getJSONObject("links");
                        String image_patch_link = imagePatchObj.getString("mission_patch_small");


                        // Launch site
                        JSONObject launchSiteObj = jsonObject.getJSONObject("launch_site");
                        String launch_site = launchSiteObj.getString("site_name");


                        // Fetch nested objects withing "rocket" array
                        JSONObject rocket = jsonObject.getJSONObject("rocket");
                        String rocket_name = rocket.getString("rocket_name");

                        // Second stage
                        JSONObject second_stage = rocket.getJSONObject("second_stage");
                        JSONArray payload = second_stage.getJSONArray("payloads");

                        // Customer
                        JSONObject customerObj = payload.getJSONObject(0);
                        String customerRaw = customerObj.getString("customers");
                        String customer = customerRaw.substring(2, customerRaw.length()-2).replace("\"", "");



                        Collections.sort(data_list, new Comparator<MyData>() {
                            @Override
                            public int compare(MyData o1, MyData o2) {

                                return o2.getLaunch_date().compareTo(o1.getLaunch_date());
                            }
                        });



                        MyData data = new MyData(image_patch_link, flight_number, mission_name, rocket_name, customer, launch_site, launch_date);
                        data_list.add(data);


                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
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
