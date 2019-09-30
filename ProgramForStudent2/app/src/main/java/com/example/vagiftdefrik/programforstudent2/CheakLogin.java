package com.example.vagiftdefrik.programforstudent2;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CheakLogin extends AppCompatActivity {
    private TextView textView;
    String Id, Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String fName = intent.getStringExtra("login"); /// poluchaet danie c MainActivity
        String fPassword = intent.getStringExtra("password"); /// poluchaet danie c MainActivity

        Id = fName;
        Pass = fPassword;
       // textView = (TextView) findViewById(R.id.textView);

        ParseText parseText = new ParseText();
        parseText.execute();


        try {
            String str = parseText.get();
            if (str.equals("")) {
                startActivity(new Intent(CheakLogin.this, MainActivity.class));/// ecli budet ne pravilno 학번 이나 비밀 to on vernet v MainActivity
                Toast.makeText(getApplicationContext(),
                        "UserID or Password is incorrect ", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent_menu = new Intent(CheakLogin.this, Home_Page.class); ///ecli pravilno zapuskaetsa klass Menu
                intent_menu.putExtra("login", fName);
                intent_menu.putExtra("password", fPassword);
                intent_menu.putExtra("name", str);
                Toast.makeText(getApplicationContext(),
                        "Loading...", Toast.LENGTH_SHORT).show();
                startActivity(intent_menu);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(CheakLogin.this, MainActivity.class));
    }


    class ParseText extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String str = ""; ///
            try {
                Connection.Response res2 = Jsoup.connect("https://ecampus.***********") ///  ecampuss link to your uni site login
                        .data("username", Id, "password", Pass) /// zapolnaet s ecampusa danie  iiiii
                        .method(Connection.Method.POST)
                        .execute();
                Document doc = Jsoup.connect("https://ecampus.******").cookies(res2.cookies()).get();//  link go to "doc"   //ecampuss link to your uni user/user_edit.php
                Elements element = doc.select("input[id=id_firstname]");    /// "input[id=id_firstname]" idet v element

                String attr = element.attr("value");
                str = attr.toString(); ///

            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
