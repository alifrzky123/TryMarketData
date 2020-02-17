package com.training.alif.geeksfarm.marketplace.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.training.alif.geeksfarm.marketplace.R;
import com.training.alif.geeksfarm.marketplace.Detail;
import com.training.alif.geeksfarm.marketplace.entity.Product;

import java.util.List;

import static com.training.alif.geeksfarm.marketplace.Detail.EXTRA_DATA;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private List<Product> listProduct;

    @NonNull
    @Override
    public MainAdapter.MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MainHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainHolder holder, int position) {
        if (listProduct != null) {
            holder.onBind(listProduct.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return listProduct != null ? listProduct.size() : 0;
    }

    public void setProducts(List<Product> products) {
        this.listProduct = products;
        notifyDataSetChanged();
    }

    public class MainHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private Context context;
        private TextView productName, merchantName;
        private Product product;

        public MainHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_product);
            productName = itemView.findViewById(R.id.tv_product_name);
            merchantName = itemView.findViewById(R.id.tv_merchant_name);
            context = itemView.getContext();
            itemView.setOnClickListener(listener);

        }

        void onBind(Product product) {
            this.product = product;
            productName.setText(product.getName());
            merchantName.setText(product.getMerch().getName());
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, product.getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent (context, Detail.class);
                i.putExtra(EXTRA_DATA, product);
                context.startActivity(i);

            }
        };
    }
}