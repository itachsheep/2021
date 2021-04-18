package com.tao.opensourcecode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private static final String DESTINATION_ADDRESS = "https://github.com/soulrelay";

    public void test_okhttp_sync() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(DESTINATION_ADDRESS)
                .build();
        Response response = client.newCall(request).execute();
    }


    //private static final String DESTINATION_ADDRESS = "https://github.com/soulrelay";
    public void test_okhttp_async() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(DESTINATION_ADDRESS)
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
}