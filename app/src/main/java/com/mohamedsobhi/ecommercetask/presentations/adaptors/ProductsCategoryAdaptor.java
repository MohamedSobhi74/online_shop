package com.mohamedsobhi.ecommercetask.presentations.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedsobhi.ecommercetask.R;
import com.mohamedsobhi.ecommercetask.entities.home.HomeResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsCategoryAdaptor extends RecyclerView.Adapter<ProductsCategoryAdaptor.ProductsCategoryViewHolder> {

    Context context;
    HomeResponse homeResponse;
    int count;

    public ProductsCategoryAdaptor(Context context, HomeResponse homeResponse,int count) {
        this.context = context;
        this.homeResponse = homeResponse;
        this.count = count;
    }

    @NonNull
    @Override
    public ProductsCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_recycler_item, parent, false);
        return new ProductsCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsCategoryViewHolder holder, int position) {

        if (count>1){
            holder.productCategory.setText(homeResponse.getData().getCategories().get(position).getName());

        }else {
            holder.productCategory.setText("قد تعجبك ايضا");

        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1,RecyclerView.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        //linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        ProductAdaptor productAdaptor = new ProductAdaptor(context,homeResponse.getData().getProducts());
        holder.productsRecycler.setLayoutManager(linearLayoutManager);
        holder.productsRecycler.setHasFixedSize(true);
        holder.productsRecycler.scrollTo(0,0);
        holder.productsRecycler.setAdapter(productAdaptor);

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class ProductsCategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_category)
        TextView productCategory;
        @BindView(R.id.all_product)
        TextView allProduct;
        @BindView(R.id.products_recycler)
        RecyclerView productsRecycler;

        public ProductsCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
