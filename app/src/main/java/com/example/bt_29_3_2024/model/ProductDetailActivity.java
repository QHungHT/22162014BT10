package com.example.bt_29_3_2024.model;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bt_29_3_2024.R;
import com.example.bt_29_3_2024.api.APIService;
import com.example.bt_29_3_2024.api.RetrofitClient;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private static final String TAG = "ProductDetailActivity";
    private int productId;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Nhận ID sản phẩm từ intent
        String productIdString = getIntent().getStringExtra("product_id");

        // Chuyển đổi ID sản phẩm từ String sang Integer
        int productId = -1;
        if (productIdString != null && !productIdString.isEmpty()) {
            productId = Integer.parseInt(productIdString);
        }

        // Khởi tạo API Service
        apiService = RetrofitClient.getRetrofit().create(APIService.class);

        // Gọi phương thức để lấy thông tin chi tiết của sản phẩm từ API
        getProductDetails(productId);
    }


    private void getProductDetails(int productId) {
        APIService productService = RetrofitClient.getRetrofit().create(APIService.class);
        productService.getProductDetail(productId).enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(@NonNull Call<ProductDetail> call, @NonNull Response<ProductDetail> response) {
                if (response.isSuccessful()) {
                    ProductDetail productDetail = response.body();
                    if (productDetail != null) {
                        displayProductDetail(productDetail);
                    }
                } else {
                    int statusCode = response.code();
                    Log.e(TAG, "onResponse: " + statusCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductDetail> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void displayProductDetail(ProductDetail productDetail) {
        // Lấy các thành phần giao diện từ layout XML
        TextView textViewMealName = findViewById(R.id.textView);
        TextView textViewArea = findViewById(R.id.textView2);
        TextView textViewInstructions = findViewById(R.id.textView4);
        CircleImageView imageView = findViewById(R.id.imgBeef);
        Button button = findViewById(R.id.button);

        // Thiết lập dữ liệu từ đối tượng ProductDetail
        textViewMealName.setText(productDetail.getMeal());
        textViewArea.setText(productDetail.getArea());
        textViewInstructions.setText(productDetail.getInstructions());

        // Sử dụng Picasso (hoặc một thư viện tương tự) để tải hình ảnh từ URL và đặt nó vào ImageView
        Picasso.get().load(productDetail.getStrmealthumb()).into(imageView);

        // Thiết lập giá của sản phẩm cho Button
        button.setText("Price: " + productDetail.getPrice());
    }
}
