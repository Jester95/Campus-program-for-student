package com.example.vagiftdefrik.programforstudent2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by VAGIF TDEFRIK on 09.06.2018.
 */

public class Sweap_header extends AppCompatActivity {


    private TextView id, name;
    String UserID;
    String Name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_swipe);

        Intent intent = getIntent();
        UserID = intent.getStringExtra("login");
        Name= intent.getStringExtra("name");


        id =  findViewById(R.id.btn_id);
        name =  findViewById(R.id.btn_pass);


        id.setText(UserID);
        name.setText(Name);



    }
}
