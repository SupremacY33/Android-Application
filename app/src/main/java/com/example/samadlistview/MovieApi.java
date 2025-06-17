package com.example.samadlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieApi extends AppCompatActivity {

    RecyclerView recyclerView;
    tvShowAdapter adapter;
    ArrayList<tvShowModel> arrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_api);
    }

    private void init() {

        recyclerView = findViewById(R.id.showingtvrecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        getApiResponse();


    }

    private void getApiResponse(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://www.episodate.com/api/most-popular?page=1";
        StringRequest
                stringRequest
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        System.out.println(response.toString());

                        try {
                            JSONObject object = new JSONObject(response.toString());

                            JSONObject array = object.getJSONObject("tv_shows");

                            System.out.println(array.length());

                            for (int j = 0 ; j < array.length() ; j++) {

                                tvShowModel model = new tvShowModel();
                                JSONObject object1 = array.getJSONObject(String.valueOf(j));

                                model.setId(object1.getString("id"));
                                model.setName(object1.getString("name"));
                                model.setImage_thumbnail_path(object1.getString("image_thumbnail_path"));


                                arrayList.add(model);
                                adapter = new tvShowAdapter(arrayList,getApplicationContext());
                                recyclerView.setAdapter(adapter);



                            }




                        } catch (JSONException exception) {
                            throw new RuntimeException(exception);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        System.out.println(error.toString());
                    }
                });
        requestQueue.add(stringRequest);



    }
}