package com.training.alif.geeksfarm.marketplace.asynctask;


import android.os.AsyncTask;

import com.google.gson.Gson;
import com.training.alif.geeksfarm.marketplace.adapter.MainAdapter;
import com.training.alif.geeksfarm.marketplace.entity.ListProduct;
import com.training.alif.geeksfarm.marketplace.entity.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AsyncTaskClass extends AsyncTask<String, Void, String>{
    private WeakReference<MainAdapter> adapter;

    public AsyncTaskClass (MainAdapter adapter, List<Product> products){
        this.adapter = new WeakReference<>(adapter);
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
                Gson gson = new Gson();
                ListProduct listData = gson.fromJson(s,ListProduct.class);
                adapter.get().setProducts(listData.getProducts());
        }
    }
}
