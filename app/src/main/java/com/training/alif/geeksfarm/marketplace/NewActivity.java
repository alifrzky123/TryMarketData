package com.training.alif.geeksfarm.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.training.alif.geeksfarm.marketplace.adapter.MainAdapter;
import com.training.alif.geeksfarm.marketplace.entity.ListProduct;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class NewActivity extends AppCompatActivity {
    EditText etName,etQty,etMerch,etCat;
    Button btnSend;
    String basePoint = "http://210.210.154.65:4444";//Ini merupakan method GET dalam web
    String endPoint = "/api/products";
    String url = basePoint+endPoint;
    RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        init();
        rq = Volley.newRequestQueue(this);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Volley();
            }
        });
    }

    public void init(){
        etName = findViewById(R.id.et_name);
        etQty = findViewById(R.id.et_qty);
        etCat = findViewById(R.id.et_cat);
        etMerch = findViewById(R.id.et_merch);
        btnSend = findViewById(R.id.btn_send);
    }

    public void Volley(){
        final String name = etName.getText().toString();
        final String qty = etQty.getText().toString();
        final String cat = etCat.getText().toString();
        final String merch = etMerch.getText().toString();
        StringRequest listProductReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Handle Response

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error",error.getMessage());
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> data = new HashMap<String, String>();
                data.put("productName",name);
                data.put("productQty",qty);
                data.put("categoryId",cat);
                data.put("merchantId",merch);
                return data;
            }
        };
        rq.add(listProductReq);
    }
}
