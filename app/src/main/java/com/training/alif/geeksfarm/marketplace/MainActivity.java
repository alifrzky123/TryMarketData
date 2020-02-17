package com.training.alif.geeksfarm.marketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.training.alif.geeksfarm.marketplace.adapter.MainAdapter;
import com.training.alif.geeksfarm.marketplace.entity.Category;
import com.training.alif.geeksfarm.marketplace.entity.Merchant;
import com.training.alif.geeksfarm.marketplace.entity.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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

        deserializeJSON();
        MA.setProducts(pd);

    }


    private String loadJsonFromRaw(){
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.data);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void deserializeJSON(){
        String json = loadJsonFromRaw();
        try {
            JSONObject marketJson = new JSONObject(json);
            JSONArray dataJson = marketJson.getJSONArray("data");
            for (int index = 0;index<dataJson.length();index++){
                JSONObject dataObj = dataJson.getJSONObject(index);
                JSONObject merch = dataObj.getJSONObject("merchant");
                JSONObject category = dataObj.getJSONObject("productCategory");

                int ID = dataObj.getInt("productId");
                String name = dataObj.getString("productName");
                String slug = dataObj.getString("productSlug");
                int qty = dataObj.getInt("productQty");
                String img = dataObj.getString("productImage");

                Merchant merchant = setMerch(merch);
                Category cat = setCategory(category);

                Product product = new Product(ID,qty,name,slug,img,merchant,cat);
                pd.add(product);
            }
        }
        catch (JSONException err){
            err.printStackTrace();
            Toast.makeText(this, "gagal",Toast.LENGTH_LONG).show();

        }

    }
    private Category setCategory(JSONObject obj){
        try {
            int id = obj.getInt("categoryId");
            String name = obj.getString("categoryName");
            return new Category(id, name);

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
    private Merchant setMerch(JSONObject obj){
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
