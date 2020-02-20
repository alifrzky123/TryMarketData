package com.training.alif.geeksfarm.marketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.training.alif.geeksfarm.marketplace.adapter.MainAdapter;
import com.training.alif.geeksfarm.marketplace.asynctask.AsyncTaskClass;
import com.training.alif.geeksfarm.marketplace.entity.Category;
import com.training.alif.geeksfarm.marketplace.entity.ListProduct;
import com.training.alif.geeksfarm.marketplace.entity.Merchant;
import com.training.alif.geeksfarm.marketplace.entity.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.GenericSignatureFormatError;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMarket;
    private List<Product> pd = new ArrayList<>();
    private MainAdapter MA;
    private RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMarket = findViewById(R.id.rv_market_id);
        MA = new MainAdapter();
        rvMarket.setAdapter(MA);
        rvMarket.setLayoutManager(new GridLayoutManager(this,2));
        rq = Volley.newRequestQueue(getApplicationContext());

        String basePoint = "http://192.168.6.221:81";
        String endPoint = "/api/products";
        String url = basePoint+endPoint;

        StringRequest listProductReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Handle Response
                    Gson GSONData = new Gson();
                    ListProduct lp = GSONData.fromJson(response,ListProduct.class);
                    MA.setProducts(lp.getProducts());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error",error.getMessage());
            }
        });
    rq.add(listProductReq);
    }
}
