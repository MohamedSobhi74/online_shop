package com.mohamedsobhi.ecommercetask.usecases.repos;

import com.mohamedsobhi.ecommercetask.entities.home.HomeResponse;
import com.mohamedsobhi.ecommercetask.usecases.network.ApiInterface;
import com.mohamedsobhi.ecommercetask.usecases.network.RetrofitClient;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class HomeRepo {

    private ApiInterface apiInterface;

    public HomeRepo() {

        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
    }


    public Observable<HomeResponse> getHome(){

        return apiInterface.getHome();
    }
}
