
package com.mohamedsobhi.ecommercetask.entities.home;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("banner")
    @Expose
    private List<Banner> banner = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;

    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

}
