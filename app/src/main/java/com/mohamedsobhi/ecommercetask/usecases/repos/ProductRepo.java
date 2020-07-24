package com.mohamedsobhi.ecommercetask.usecases.repos;

import com.mohamedsobhi.ecommercetask.entities.home.HomeResponse;
import com.mohamedsobhi.ecommercetask.entities.productDetails.ProductDetailsResponse;
import com.mohamedsobhi.ecommercetask.usecases.network.ApiInterface;
import com.mohamedsobhi.ecommercetask.usecases.network.RetrofitClient;

import io.reactivex.Observable;

public class ProductRepo {

    private ApiInterface apiInterface;

    public ProductRepo() {

        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
    }


    public Observable<ProductDetailsResponse> getProductDetails(String id){

        return apiInterface.getProductDetails(id);
    }
}