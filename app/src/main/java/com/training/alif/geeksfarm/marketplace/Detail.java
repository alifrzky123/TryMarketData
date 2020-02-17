package com.training.alif.geeksfarm.marketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.training.alif.geeksfarm.marketplace.entity.Product;

public class Detail extends AppCompatActivity {
    public static String EXTRA_DATA = "EXTRA PRODUCT DATA";
    private ImageView image;
    private TextView name, qty, category, merchant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        Product product = getIntent().getParcelableExtra(EXTRA_DATA);
        if(product != null){
            Toast.makeText(this, "ada data", Toast.LENGTH_SHORT).show();
            String img = product.getImg();
            String names = product.getName();
            int Qty = product.getQty();
            String _qty = Integer.toString(Qty);
            String cat = product.getCat().getName();
            String _merchant = product.getMerch().getName();

            name.setText(names);
            qty.setText(_qty);
            category.setText(cat);
            merchant.setText(_merchant);
        }
    }

    public void init(){
        image = findViewById(R.id.img_detail);
        name = findViewById(R.id.tv_name);
        qty = findViewById(R.id.tv_qty);
        category = findViewById(R.id.tv_prod);
        merchant = findViewById(R.id.tv_merch);
    }
}
