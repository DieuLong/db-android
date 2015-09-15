package com.philong.mywish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import data.DatabaseHandler;
import model.Wish;

public class MainActivity extends AppCompatActivity {

    private EditText edtTitle, edtContent;
    private DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitle = (EditText)findViewById(R.id.edtTitle);
        edtContent = (EditText)findViewById(R.id.edtContent);
        db = new DatabaseHandler(this);



    }

    public void btnSave_Click(View v){
        Wish wish = new Wish();
        wish.setTitle(edtTitle.getText().toString());
        wish.setContent(edtContent.getText().toString());
        db.insertWish(wish);
        edtTitle.setText("");
        edtTitle.requestFocus();
        edtContent.setText("");
    }

    public void btnView_Click(View v){
        Intent intent = new Intent(MainActivity.this, ActivityListWish.class);
        startActivity(intent);
    }


}
