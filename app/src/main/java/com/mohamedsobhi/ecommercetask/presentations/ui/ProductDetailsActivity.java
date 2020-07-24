package com.mohamedsobhi.ecommercetask.presentations.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedsobhi.ecommercetask.R;
import com.mohamedsobhi.ecommercetask.entities.home.Banner;
import com.mohamedsobhi.ecommercetask.entities.productDetails.Color;
import com.mohamedsobhi.ecommercetask.entities.productDetails.Image;
import com.mohamedsobhi.ecommercetask.entities.productDetails.ProductDetailsResponse;
import com.mohamedsobhi.ecommercetask.presentations.adaptors.ColorAdaptor;
import com.mohamedsobhi.ecommercetask.presentations.adaptors.ProductsCategoryAdaptor;
import com.mohamedsobhi.ecommercetask.presentations.viewModels.HomeViewModel;
import com.mohamedsobhi.ecommercetask.presentations.viewModels.ProductViewModel;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_product_name)
    TextView toolbarProductName;

    @BindView(R.id.banner_slider1)
    ImageCarousel bannerSlider1;

    @BindView(R.id.product_name)
    TextView productName;
    @BindView(R.id.product_price)
    TextView productPrice;
    @BindView(R.id.rate_count)
    TextView rateCount;
    @BindView(R.id.rate_view)
    LinearLayout rateView;
    @BindView(R.id.all_comments)
    TextView allComments;
    @BindView(R.id.add_to_cart)
    LinearLayout addToCart;
    @BindView(R.id.product_desc)
    TextView productDesc;
    @BindView(R.id.desc_view)
    LinearLayout descView;
    @BindView(R.id.product_category_recycler)
    RecyclerView productCategoryRecycler;
    List<Banner> bannersList;
    List<CarouselItem> bannersUriList;
    @BindView(R.id.toolbar_back)
    ImageView toolbarBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.product_favourite)
    ImageView productFavourite;
    @BindView(R.id.view1)
    RelativeLayout view1;
    @BindView(R.id.color_title)
    TextView colorTitle;
    @BindView(R.id.color_recycler)
    RecyclerView colorRecycler;

    ProductViewModel productViewModel;
    String productId;
    HomeViewModel homeViewModel;
    ProductsCategoryAdaptor productsCategoryAdaptor;
   ColorAdaptor colorAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        productId = getIntent().getStringExtra("productId");
        Log.e("iddd", productId);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductDetails(productId);

        productViewModel.productDetailsResponseMutableLiveData.observe(this, productDetailsResponse -> {

            initializeView(productDetailsResponse);
        });

        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }

    private void initializeView(ProductDetailsResponse productDetailsResponse) {

        toolbarProductName.setText(productDetailsResponse.getData().getProduct().getName());
        productName.setText(productDetailsResponse.getData().getProduct().getName());
        productPrice.setText(productDetailsResponse.getData().getProduct().getPrice() + " جنيه");
        productDesc.setText(productDetailsResponse.getData().getProduct().getBio());
        rateCount.setText((int) productDetailsResponse.getData().getProduct().getRate() + "");

        createBanner(productDetailsResponse.getData().getProduct().getImages());
        initializeColorsRecycler(productDetailsResponse.getData().getProduct().getColors());
        initializeSuggestedRecycler();
    }

    private void initializeColorsRecycler(List<Color> colors) {

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        colors.add(new Color(0,"احمر"));
        colors.add(new Color(1,"اخضر"));
        colors.add(new Color(2,"ابيض"));
        colorRecycler.setLayoutManager(linearLayoutManager);
        colorAdaptor = new ColorAdaptor(this, colors);
        colorRecycler.setAdapter(colorAdaptor);


    }

    private void initializeSuggestedRecycler() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        productCategoryRecycler.setLayoutManager(linearLayoutManager);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.homeResponseMutableLiveData.observe(this, homeResponse -> {

            productsCategoryAdaptor = new ProductsCategoryAdaptor(this, homeResponse, 1);
            productCategoryRecycler.setAdapter(productsCategoryAdaptor);
            productsCategoryAdaptor.notifyDataSetChanged();


        });
    }

    private void createBanner(List<Image> imageList) {

        bannersUriList = new ArrayList<>();
        for (Image image : imageList) {
            bannersUriList.add(new CarouselItem(image.getImage()));
        }

        bannerSlider1.addData(bannersUriList);
        bannerSlider1.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

}