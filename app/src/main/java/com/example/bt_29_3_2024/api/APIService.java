package com.example.bt_29_3_2024.api;

import com.example.bt_29_3_2024.model.Category;
import com.example.bt_29_3_2024.model.ProductDetail;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();

    @FormUrlEncoded
    @POST("getcategory.php")
    Call<ResponseBody> getSubCategory(
            @Field("idcategory") int idcategory
    );
    @GET("product/{productId}")
    Call<ProductDetail> getProductDetail(@Path("productId") int productId);
}
