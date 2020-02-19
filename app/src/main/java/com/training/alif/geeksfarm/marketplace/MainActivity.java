package com.training.alif.geeksfarm.marketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.training.alif.geeksfarm.marketplace.adapter.MainAdapter;
import com.training.alif.geeksfarm.marketplace.asynctask.AsyncTaskClass;
import com.training.alif.geeksfarm.marketplace.entity.Category;
import com.training.alif.geeksfarm.marketplace.entity.Merchant;
import com.training.alif.geeksfarm.marketplace.entity.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMarket;
    private List<Product> pd = new ArrayList<>();
    private MainAdapter MA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMarket = findViewById(R.id.rv_market_id);
        MA = new MainAdapter();
        rvMarket.setAdapter(MA);
        rvMarket.setLayoutManager(new GridLayoutManager(this,2));

        String basePoint = "http://192.168.6.221:81";
        String endPoint = "/api/products";
        String url = basePoint+endPoint;
        new AsyncTaskClass(MA,pd).execute(url);
    }
    public static Category setCategory(JSONObject obj){
        try {
            int id = obj.getInt("categoryId");
            String name = obj.getString("categoryName");
            return new Category(id, name);

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
    public static Merchant setMerch(JSONObject obj){
        try{
            int id = obj.getInt("merchantId");
            String name = obj.getString("merchantName");
            String slug = obj.getString("merchantSlug");
            return new Merchant(id,name,slug);

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
