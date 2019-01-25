package com.example.spacefetcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<MyData> data_list;
    RequestQueue mqueue;


    TextView test;
    TextView mission_nameTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mqueue = Volley.newRequestQueue(this);
        data_list = new ArrayList<>();

        jsonParse();

        recyclerView = findViewById(R.id.rv);
        adapter = new RecyclerAdapter(this,data_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        test = findViewById(R.id.testTxt);
        mission_nameTxt = findViewById(R.id.missionNameTxt);

    }

    public void jsonParse() {
        String url = "https://api.spacexdata.com/v3/launches";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < 5; i++)
                    try {
                        JSONObject missionObj = response.getJSONObject(i);
                        String mission_name = missionObj.getString("mission_name");


                        MyData data = new MyData(mission_name);
                        data_list.add(data);


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



/*

JSONObject missionObject = response.getJSONObject(i);
                        String rocket_name = missionObject.getString("mission_name");



                        data_list.add(new MyData("tjääääna"));


                        //MyData data = new MyData(rocket_name);
                        //data_list.add(data);
 */
