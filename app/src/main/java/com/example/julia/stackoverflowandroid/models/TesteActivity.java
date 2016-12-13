package com.example.julia.stackoverflowandroid.models;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.julia.stackoverflowandroid.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class TesteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        Intent intent = getIntent();
        String url = (String) intent.getSerializableExtra("url");
        String t = (String) intent.getSerializableExtra("title");
        String u = (String) intent.getSerializableExtra("user");

        ImageView img = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(url).into(img);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(t);


        TextView user = (TextView) findViewById(R.id.user);
        user.setText(u);

    }
}
