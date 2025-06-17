package com.example.samadlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

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

public class Api extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiAdapter adapter;
    ArrayList<Models> models = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        recyclerView = findViewById(R.id.showdata);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getApiHit();
    }


    private void getApiHit(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://fakestoreapi.com/products";

        JsonArrayRequest
                jsonArrayRequest
                = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(response.toString());

                        try {
                            for (int i = 0 ; i < response.length() ; i++){

                                JSONObject object = response.getJSONObject(i);

                                Models models1 = new Models();

                                models1.setId(object.getString("id"));
                                models1.setTitle(object.getString("title"));
                                models1.setBody(object.getString("description"));
                                models1.setImage(object.getString("image"));

                                models.add(models1);

                                adapter = new ApiAdapter(getApplicationContext(), models);
                                recyclerView.setAdapter(adapter);

                            }
                        }catch (JSONException exception)
                        {
                            throw new RuntimeException(exception);
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }
                });
        requestQueue.add(jsonArrayRequest);




    }




}