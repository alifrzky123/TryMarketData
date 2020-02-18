package com.training.alif.geeksfarm.marketplace.asynctask;


import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.Toast;

import com.training.alif.geeksfarm.marketplace.MainActivity;
import com.training.alif.geeksfarm.marketplace.adapter.MainAdapter;
import com.training.alif.geeksfarm.marketplace.entity.Category;
import com.training.alif.geeksfarm.marketplace.entity.Merchant;
import com.training.alif.geeksfarm.marketplace.entity.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AsyncTaskClass extends AsyncTask<String, Void, String>{

    private WeakReference<List<Product>> products;
    private WeakReference<MainAdapter> adapter;

    public AsyncTaskClass (MainAdapter adapter, List<Product> products){
        this.adapter = new WeakReference<>(adapter);
        this.products = new WeakReference<>(products);
    }

    @Override
    protected String doInBackground(String... strings) {
        String json = null;
        try{
            URL url = new URL(strings[0]);
            HttpURLConnection conn = null;

            try{
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                InputStream in = conn.getInputStream();

                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                json = stringBuilder.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(conn != null){
                    conn.disconnect();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return json;
    }

    @Override
    protected void onPostExecute(String s) {

        if(!s.equals("")){
            try {

                JSONObject dataObj = new JSONObject(s);
                JSONArray dataArr = dataObj.getJSONArray("data");

                for(int i=0; i<dataArr.length(); i++){

                    JSONObject product = dataArr.getJSONObject(i);
                    JSONObject merchant = product.getJSONObject("merchant");
                    JSONObject category = product.getJSONObject("category");

                    int id = product.getInt("productId");
                    String name = product.getString("productName");
                    String slug = product.getString("productSlug");
                    int qty = product.getInt("productQty");
                    String image = product.getString("productImage");

                    Merchant _merchant = MainActivity.setMerch(merchant);
                    Category _category = MainActivity.setCategory(category);

                    Product _product = new Product(id, qty, name, slug, image, _merchant, _category);
                    products.get().add(_product);
                }

                adapter.get().setProducts(products.get());

            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }
}
