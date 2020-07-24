package com.mohamedsobhi.ecommercetask.usecases.network;

import com.mohamedsobhi.ecommercetask.entities.home.HomeResponse;
import com.mohamedsobhi.ecommercetask.entities.productDetails.ProductDetailsResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("home")
    Observable<HomeResponse> getHome();

    @GET("product/{id}")
    Observable<ProductDetailsResponse> getProductDetails(@Path("id") String productId);
}
