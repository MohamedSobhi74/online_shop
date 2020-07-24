package com.mohamedsobhi.ecommercetask.usecases;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.lifecycle.MutableLiveData;

import com.mohamedsobhi.ecommercetask.entities.home.HomeResponse;
import com.mohamedsobhi.ecommercetask.usecases.repos.HomeRepo;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeUsecases {

    private HomeRepo homeRepo;
    private MutableLiveData<HomeResponse>  homeResponseMutableLiveData;

    public HomeUsecases(MutableLiveData<HomeResponse> homeResponseMutableLiveData) {

        homeRepo = new HomeRepo();
        this.homeResponseMutableLiveData = homeResponseMutableLiveData;
    }

    public void getHome (){

        homeRepo.getHome().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomeResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeResponse homeResponse) {

                homeResponseMutableLiveData.postValue(homeResponse);
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
