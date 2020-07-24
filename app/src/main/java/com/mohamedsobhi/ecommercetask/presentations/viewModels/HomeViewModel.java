package com.mohamedsobhi.ecommercetask.presentations.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mohamedsobhi.ecommercetask.entities.home.HomeResponse;
import com.mohamedsobhi.ecommercetask.usecases.HomeUsecases;
import com.mohamedsobhi.ecommercetask.usecases.network.ApiInterface;

public class HomeViewModel extends ViewModel {


    public MutableLiveData<HomeResponse> homeResponseMutableLiveData;
    private HomeUsecases homeUsecases;

    public HomeViewModel() {

        homeResponseMutableLiveData = new MutableLiveData<>();
        homeUsecases = new HomeUsecases(homeResponseMutableLiveData);

        getHome();
    }

    private void getHome() {
        homeUsecases.getHome();
    }


}
