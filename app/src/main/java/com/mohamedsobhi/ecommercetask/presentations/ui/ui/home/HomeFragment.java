package com.mohamedsobhi.ecommercetask.presentations.ui.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedsobhi.ecommercetask.R;
import com.mohamedsobhi.ecommercetask.entities.home.Banner;
import com.mohamedsobhi.ecommercetask.entities.home.Category;
import com.mohamedsobhi.ecommercetask.entities.home.HomeResponse;
import com.mohamedsobhi.ecommercetask.entities.home.Product;
import com.mohamedsobhi.ecommercetask.presentations.adaptors.CategoryAdaptor;
import com.mohamedsobhi.ecommercetask.presentations.adaptors.ProductsCategoryAdaptor;
import com.mohamedsobhi.ecommercetask.presentations.viewModels.HomeViewModel;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.banner_slider1)
    ImageCarousel bannerSlider1;
    @BindView(R.id.product_category_recycler)
    RecyclerView productCategoryRecycler;
    @BindView(R.id.category_recycler)
    RecyclerView categoryRecycler;

    HomeViewModel homeViewModel;
    List<Banner> bannersList;
    List<CarouselItem> bannersUriList;
    ProductsCategoryAdaptor productsCategoryAdaptor;
    CategoryAdaptor categoryAdaptor;
    HomeResponse homeResponse;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, root);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        productCategoryRecycler.setLayoutManager(linearLayoutManager);

        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        bannersList = new ArrayList<>();

        homeViewModel.homeResponseMutableLiveData.observe(getViewLifecycleOwner(), homeResponse -> {

            //bannersList.addAll(homeResponse.getData().getBanner());
            createBanner(homeResponse.getData().getProducts());
            //this.homeResponse = homeResponse;
            productsCategoryAdaptor = new ProductsCategoryAdaptor(getActivity(), homeResponse,2);
            productCategoryRecycler.setAdapter(productsCategoryAdaptor);
            productsCategoryAdaptor.notifyDataSetChanged();

            initializeCategories(homeResponse.getData().getCategories());

        });

        return root;
    }

    private void initializeCategories(List<Category> categories) {

        categoryAdaptor = new CategoryAdaptor(getActivity(), categories);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(linearLayoutManager);
        categoryRecycler.setAdapter(categoryAdaptor);


    }

    private void createBanner(List<Product> productList) {

        bannersUriList = new ArrayList<>();
        for (Product product : productList) {
            bannersUriList.add(new CarouselItem(product.getImage()));
        }

        bannerSlider1.addData(bannersUriList);
        bannerSlider1.start();
    }
}