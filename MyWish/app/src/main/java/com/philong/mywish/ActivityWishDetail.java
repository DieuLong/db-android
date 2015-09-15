package com.philong.mywish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;

public class ActivityWishDetail extends AppCompatActivity {

    private TextView txtTitleDetail, txtDateDetail, txtContentDetail;
    private DatabaseHandler db;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_wish_detail);

        db = new DatabaseHandler(this);
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("data");


        txtTitleDetail = (TextView)findViewById(R.id.txtTitleDetail);
        txtContentDetail = (TextView)findViewById(R.id.txtContentDetail);
        txtDateDetail = (TextView)findViewById(R.id.txtDateDetail);

        txtTitleDetail.setText(bundle.getString("title"));
        txtContentDetail.setText(bundle.getString("content"));
        txtDateDetail.setText(bundle.getString("date"));
        id = bundle.getInt("id");


    }

    public void btnDelete_Click(View v){
        db.deleteWish(id);
        Toast.makeText(getApplicationContext(), "Wish delete", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ActivityWishDetail.this, ActivityListWish.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();


    }




}
