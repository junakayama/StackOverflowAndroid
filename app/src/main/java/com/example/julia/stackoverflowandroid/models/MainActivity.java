package com.example.julia.stackoverflowandroid.models;

import android.content.Intent;
import android.support.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity {
    
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.list_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackOverflowService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StackOverflowService service = retrofit.create(StackOverflowService.class);
        Call<TagsList> requestTag = service.listTags("Android");

        requestTag.enqueue(new Callback<TagsList>() {
            public static final String TAG = "Julia";

            @Override
            public void onResponse(Call<TagsList> call, Response<TagsList> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG, "Erro:" + response.code());
                } else{
                    TagsList tagsList = response.body();
                    carregaLista(tagsList);

//                    for(Item i : tagsList.items){
//                        Log.i(TAG, String.format("%s:  %d", i.name, i.count));
//                        Log.i(TAG, String.format("-------------------"));
//
//                    }
                }
            }

            @Override
            public void onFailure(Call<TagsList> call, Throwable t) {
                Log.e(TAG,"Erro:" + t.getMessage());
            }
        });




//        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                PontoTuristico pontoTuristico = (PontoTuristico)lista.getItemAtPosition(i);
//                Intent intent = new Intent(MainActivity.this,InformacoesActivity.class);
//                intent.putExtra("pontoTuristico",pontoTuristico);
//                startActivity(intent);
//            }
//        });
    }
    @NonNull
    private ListView carregaLista(TagsList tagsList) {
        List<Item> items = tagsList.items;
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1, items);
        lista.setAdapter(adapter);
        return lista;
    }
}
