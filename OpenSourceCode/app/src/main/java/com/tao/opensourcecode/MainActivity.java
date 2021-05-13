package com.tao.opensourcecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private static final String DESTINATION_URL = "https://github.com/soulrelay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void test_okhttp_sync() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(DESTINATION_URL)
                .build();
        Response response = client.newCall(request).execute();
    }

    public void test_okhttp_async() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(DESTINATION_URL)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });

    }

    public void glide_test() {
        String url = "";
        ImageView imageView;
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    public void retrofit_test() {

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(DESTINATION_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(null)
                .build();
        GitHubApiService gitHubApiService = retrofit.create(GitHubApiService.class);
        retrofit2.Call<List<Repo>> call = gitHubApiService.listRepos("tao");
        call.enqueue(new retrofit2.Callback<List<Repo>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Repo>> call, retrofit2.Response<List<Repo>> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<List<Repo>> call, Throwable t) {

            }
        });
    }


    public void test_java_jichu() {
        SparseArray<Object> sparseArray = new SparseArray<>();
        sparseArray.put(1,new Object());
        Object object;
    }
}