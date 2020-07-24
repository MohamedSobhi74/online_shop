package com.mohamedsobhi.ecommercetask.presentations.adaptors;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamedsobhi.ecommercetask.R;
import com.mohamedsobhi.ecommercetask.entities.home.Product;
import com.mohamedsobhi.ecommercetask.presentations.ui.ProductDetailsActivity;
import com.varunest.sparkbutton.SparkButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ProductViewHolder> {

    Context context;
    List<Product> productsList;



    public ProductAdaptor(Context context, List<Product> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = productsList.get(position);
        holder.productName.setText(product.getName());
        holder.firstPrice.setText(product.getPrice() + " جنية");
        holder.finalPrice.setText(product.getPrice() + " جنية");

        Glide.with(context).load(product.getImage()).into(holder.productImage);

        holder.productItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("productId",String.valueOf(product.getId()));
                Log.e("iddd 55",String.valueOf(product.getId()));

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_image)
        ImageView productImage;
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.first_price)
        TextView firstPrice;
        @BindView(R.id.final_price)
        TextView finalPrice;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.add_to_cart)
        SparkButton addToCart;
        @BindView(R.id.add_to_favourite)
        SparkButton addToFavourite;
        @BindView(R.id.product_item)
        CardView productItem;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
