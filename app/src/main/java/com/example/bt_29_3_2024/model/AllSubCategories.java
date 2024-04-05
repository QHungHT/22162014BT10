package com.example.bt_29_3_2024.model;

import android.util.Log;

import com.example.bt_29_3_2024.api.APIService;
import com.example.bt_29_3_2024.api.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllSubCategories {
    public static List<SubCategory> ans = new ArrayList<>();
    private final int idCategory;
    APIService apiService;

    int cnt = 0;

    public AllSubCategories(int idCategory) {
        this.idCategory = idCategory;
    }

    public void getAllSubCategories() {

        apiService = RetrofitClient.getRetrofit()
                                   .create(APIService.class);

        apiService.getSubCategory(idCategory)
                  .enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(
                              Call<ResponseBody> call, Response<ResponseBody> response
                      ) {
                          if (response.isSuccessful()) {
                              try {
                                  String json = response.body()
                                                        .string();

                                  Log.e("Response", "onResponse1: " + json + response.body()
                                                                                     .string());

                                  parseJson(json);
                              } catch (Exception e) {
                                  e.printStackTrace();
                              }
                          } else {
                              Log.e("Error", "onResponse2: " + response.code());
                          }
                      }

                      @Override
                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                          Log.e("Error", "onFailure: " + t.getMessage());
                      }
                  });
    }

    public void parseJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            ans.clear(); // Xóa dữ liệu cũ trước khi thêm mới

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String strMeal = jsonObject.getString("strMeal");
                String strMealThumb = jsonObject.getString("strMealThumb");
                String idMeal = jsonObject.getString("idMeal");
                String idCategory = jsonObject.getString("idcategory");

                SubCategory subCategory = new SubCategory(id, strMeal, strMealThumb, idMeal, idCategory);
                ans.add(subCategory);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error", "Error parsing JSON: " + e.getMessage());
        }
    }


}
