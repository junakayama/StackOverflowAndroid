package com.example.julia.stackoverflowandroid.models;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.julia.stackoverflowandroid.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsActivity extends AppCompatActivity {

    ListView lv;
    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        lv = (ListView) findViewById(R.id.list_view);

        Intent intent = getIntent();
        tag = (String) intent.getSerializableExtra("tag");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackOverflowService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StackOverflowService service = retrofit.create(StackOverflowService.class);
        Call<QuestionsList> requestTag = service.listQuestions(tag);


        requestTag.enqueue(new Callback<QuestionsList>() {
            public static final String TAG = "Julia";

            @Override
            public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG, "Erro:" + response.code());
                } else{
                    QuestionsList questionsList = response.body();
                    carregaLista(questionsList);

                }
            }

            @Override
            public void onFailure(Call<QuestionsList> call, Throwable t) {
                Log.e(TAG,"Erro:" + t.getMessage());
            }
        });
    }

    public ListView carregaLista(QuestionsList questionsList){
        List<Question> items = questionsList.items;
        Adapter adapter = new Adapter(this, R.layout.model, R.id.title, items);
        lv.setAdapter(adapter);
        return lv;
    }
}
