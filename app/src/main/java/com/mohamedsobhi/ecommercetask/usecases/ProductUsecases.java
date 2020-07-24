package com.mohamedsobhi.ecommercetask.usecases;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mohamedsobhi.ecommercetask.entities.home.HomeResponse;
import com.mohamedsobhi.ecommercetask.entities.productDetails.ProductDetailsResponse;
import com.mohamedsobhi.ecommercetask.usecases.repos.HomeRepo;
import com.mohamedsobhi.ecommercetask.usecases.repos.ProductRepo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductUsecases {

    private ProductRepo productRepo;
    public MutableLiveData<ProductDetailsResponse> productDetailsResponseMutableLiveData;

    public ProductUsecases(MutableLiveData<ProductDetailsResponse> productDetailsResponseMutableLiveData) {

        productRepo = new ProductRepo();
        this.productDetailsResponseMutableLiveData = productDetailsResponseMutableLiveData;
    }

    public void getProductDetails(String id){

        productRepo.getProductDetails(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProductDetailsResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductDetailsResponse productDetailsResponse) {

                productDetailsResponseMutableLiveData.postValue(productDetailsResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("home complete",e.getMessage());

            }

            @Override
            public void onComplete() {

                Log.e("home complete","done");
            }
        });
    }
}