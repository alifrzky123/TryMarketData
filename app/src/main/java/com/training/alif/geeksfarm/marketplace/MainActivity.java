package com.training.alif.geeksfarm.marketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvMarket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMarket = findViewById(R.id.rv_market_id);
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

                int ID = dataObj.getInt("productId");
                String name = dataObj.getString("productName");
                for (int i = 0;i<dataObj.length();i++){
                    JSONObject obj = dataJson.getJSONObject(i);

                    String nameMerch = obj.getString("merchantName");
                }
            }


        }
        catch (JSONException err){
            err.printStackTrace();
            Toast.makeText(this, "gagal",Toast.LENGTH_LONG).show();

        }

    }
}
