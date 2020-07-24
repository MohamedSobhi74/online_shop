package com.mohamedsobhi.ecommercetask.presentations.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mohamedsobhi.ecommercetask.entities.productDetails.ProductDetailsResponse;
import com.mohamedsobhi.ecommercetask.usecases.ProductUsecases;

public class ProductViewModel extends ViewModel {


    public MutableLiveData<ProductDetailsResponse> productDetailsResponseMutableLiveData;
    private ProductUsecases productUsecases;

    public ProductViewModel() {

        productDetailsResponseMutableLiveData = new MutableLiveData<>();
        productUsecases = new ProductUsecases(productDetailsResponseMutableLiveData);

    }

    public void getProductDetails(String id) {
        productUsecases.getProductDetails(id);
    }
}