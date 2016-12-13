package com.example.julia.stackoverflowandroid.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.julia.stackoverflowandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by julia on 12/12/16.
 */

public class Adapter extends ArrayAdapter<Question> {

    //DECLARACAOS
    List<Question> questions;
    Context c;
    LayoutInflater inflater;
    public Adapter(Context context, int resource, int r, List<Question> questions) {
        super(context, resource, r, questions);

        this.c = context;
        this.questions = questions;
    }

    public class ViewHolder {
        TextView title;
        TextView user;
        ImageView img;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.model, null);
        }

        final ViewHolder holder = new ViewHolder();

        holder.title = (TextView) convertView.findViewById(R.id.title);
        holder.user = (TextView) convertView.findViewById(R.id.user);
        holder.img = (ImageView) convertView.findViewById(R.id.imageView);

        Picasso.with(c).load(questions.get(position).getOwner().getProfile_image()).into(holder.img);
        holder.title.setText(questions.get(position).getTitle());
        holder.user.setText(questions.get(position).getOwner().getDisplay_name());

        return super.getView(position, convertView, parent);
    }
}
