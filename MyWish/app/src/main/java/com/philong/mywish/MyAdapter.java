package com.philong.mywish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Wish;

/**
 * Created by Administrator on 14/09/2015.
 */
public class MyAdapter extends ArrayAdapter<Wish> {
    private Context context;
    private int resource;
    private ArrayList<Wish> objects;

    public MyAdapter(Context context, int resource, ArrayList<Wish> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    private class MyHolder{
        private ImageView imgIcon;
        private TextView txtTitle, txtDate;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        MyHolder myHolder ;
        if( v == null){
            myHolder = new MyHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_custom_wish, null);

            myHolder.imgIcon  = (ImageView)v.findViewById(R.id.imgIcon);
            myHolder.txtTitle = (TextView)v.findViewById(R.id.txtTitle);
            myHolder.txtDate = (TextView)v.findViewById(R.id.txtDate);

            v.setTag(myHolder);

        }else{
            myHolder = (MyHolder)v.getTag();
        }
        myHolder.imgIcon.setImageResource(android.R.drawable.ic_menu_agenda);
        myHolder.txtDate.setText(String.valueOf(objects.get(position).getDate()));
        myHolder.txtTitle.setText(objects.get(position).getTitle());

        return v;
    }
}
