package com.example.vagiftdefrik.programforstudent2;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;



public class MobileID extends AppCompatActivity {


    private TextView btn_id, btn_pass;
    String UserID;
    String Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mobile_id);

        Intent intent = getIntent();
        UserID = intent.getStringExtra("login");
        Name= intent.getStringExtra("name");


        btn_id =  findViewById(R.id.btn_id);
        btn_pass =  findViewById(R.id.btn_pass);


        btn_id.setText(UserID);
        btn_pass.setText(Name);



    }











}

