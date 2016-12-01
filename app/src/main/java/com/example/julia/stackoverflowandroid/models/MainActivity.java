package com.example.julia.stackoverflowandroid.models;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.julia.stackoverflowandroid.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackOverflowService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StackOverflowService service = retrofit.create(StackOverflowService.class);
        Call<TagsList> requestTag = service.listTags();

        requestTag.enqueue(new Callback<TagsList>() {
            public static final String TAG = "Julia";

            @Override
            public void onResponse(Call<TagsList> call, Response<TagsList> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG, "Erro:" + response.code());
                } else{
                    TagsList tagsList = response.body();

                    for(Item i : tagsList.items){
                        Log.i(TAG, String.format("%s:  %d", i.name, i.count));
                        Log.i(TAG, String.format("-------------------"));

                    }
                }
            }

            @Override
            public void onFailure(Call<TagsList> call, Throwable t) {
                Log.e(TAG,"Erro:" + t.getMessage());
            }
        });
    }
}
