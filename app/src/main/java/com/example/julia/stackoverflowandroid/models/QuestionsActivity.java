package com.example.julia.stackoverflowandroid.models;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.julia.stackoverflowandroid.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsActivity extends AppCompatActivity {

    private String tag;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        lista = (ListView) findViewById(R.id.list_view);

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

//                    for(Item i : tagsList.items){
//                        Log.i(TAG, String.format("%s:  %d", i.name, i.count));
//                        Log.i(TAG, String.format("-------------------"));
//
//                    }
                }
            }

            @Override
            public void onFailure(Call<QuestionsList> call, Throwable t) {
                Log.e(TAG,"Erro:" + t.getMessage());
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Question question = (Question)lista.getItemAtPosition(i);
                String url = question.getOwner().getProfile_image();
                String title = question.getTitle();
                String user = question.getOwner().getDisplay_name();
                String score = String.valueOf(question.getScore());
                Intent intent = new Intent(QuestionsActivity.this,TesteActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                intent.putExtra("user", user);
                intent.putExtra("score", score);

                startActivity(intent);
            }
        });
    }

    public ListView carregaLista(QuestionsList questionsList){
        List<Question> items = questionsList.items;
        ArrayAdapter<Question> adapter = new ArrayAdapter<Question>(this,android.R.layout.simple_list_item_1, items);
        lista.setAdapter(adapter);
        return lista;
    }
}
