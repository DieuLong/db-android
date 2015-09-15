package com.philong.mywish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import data.DatabaseHandler;
import model.Wish;

public class ActivityListWish extends AppCompatActivity {

    private DatabaseHandler db;
    private ListView lsvWish;
    private ArrayList<Wish> listWish;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list_wish);
        lsvWish = (ListView) findViewById(R.id.lsvWish);
        listWish = new ArrayList<Wish>();
        db = new DatabaseHandler(this);
        listWish = db.getAll();
        adapter = new MyAdapter(this, R.layout.activity_custom_wish, listWish);
        lsvWish.setAdapter(adapter);

        // Gửi dữ liệu qua activity wish detail
        lsvWish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ActivityListWish.this, ActivityWishDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", listWish.get(position).getTitle());
                bundle.putString("content", listWish.get(position).getContent());
                bundle.putString("date", listWish.get(position).getDate());
                bundle.putInt("id", listWish.get(position).getId());
                i.putExtra("data", bundle);
                startActivity(i);
            }
        });


        // Cập nhật lại adapter sau khi xóa wish
        adapter.notifyDataSetChanged();


    }
}