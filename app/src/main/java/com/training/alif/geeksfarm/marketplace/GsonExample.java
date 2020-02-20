package com.training.alif.geeksfarm.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.training.alif.geeksfarm.marketplace.entity.Mahasiswa;
import com.training.alif.geeksfarm.marketplace.entity.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GsonExample extends AppCompatActivity {
    RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_example);
        rq = Volley.newRequestQueue(getApplicationContext());

        String basePoint = "http://192.168.6.221:81";
        String endPoint = "/api/products";
        String url = basePoint+endPoint;

        JsonObjectRequest listProductReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Handle Response
                try{
                    ArrayList<Product> products = new ArrayList<>();
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0;i < data.length();i++){
                        Gson gsonData = new Gson();

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error",error.getMessage());
            }
        });
    }
}
