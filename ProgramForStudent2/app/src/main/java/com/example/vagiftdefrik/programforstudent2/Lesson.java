package com.example.vagiftdefrik.programforstudent2;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Lesson extends AppCompatActivity {

    private ListView listView;
    private Intent myWebLink;
    String userID;
    String userpassword;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    //backKeyPressedTime은 Back Button이 눌린 마지막 시간을 기록한다.



    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson);

//
        userID = getIntent().getStringExtra("login");
        userpassword = getIntent().getStringExtra("password");


        //startActivity(new Intent(this, SplashActivity.class));

        initialize();

        back= (Button) findViewById(R.id.backtotable);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // startActivity(new Intent(Lesson.this, Timetable.class));
            }
        });


        listView = (ListView) findViewById(R.id.lecturelist);
        // textView = (TextView) findViewById(R.id.textView);

        ParseTitle parseTitle = new ParseTitle();
        parseTitle.execute();

        try {
            final HashMap<String, String> hashMap = parseTitle.get();
            final ArrayList<String> arrayList = new ArrayList<>();
            for (Map.Entry entry : hashMap.entrySet()) {
                arrayList.add(entry.getKey().toString());
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Lesson.this, android.R.layout.simple_list_item_1, arrayList);

            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ParseText parseText = new ParseText();
                    parseText.execute(hashMap.get(arrayList.get(position)));

                    try {
                       // listView.setVisibility(View.GONE);
                        //textView.setText(Html.fromHtml(parseText.get())); // linkni chqarib bervotti ekranga

                        myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                        myWebLink.setData(Uri.parse(parseText.get()));
                        startActivity(myWebLink);

                        //  myWebView.loadUrl(parseText.get());


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onBackPressed() {
       // listView.setVisibility(View.VISIBLE);
        finish();
        //textView.setVisibility(View.GONE);
        //myWebView.setVisibility(View.GONE);
        //if(myWebLink)
    }


    class ParseText extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String str = " ";
            try {
                Connection.Response res2 = Jsoup.connect("https://ecampus.smu.ac.kr/login/index.php")
                        .data("username", userID, "password", userpassword)
                        .method(Connection.Method.POST)
                        .timeout(1000)
                        .execute();
                // Map<String, String> loginCookies = res2.cookies();

                Document document = Jsoup.connect(params[0]).cookies(res2.cookies()).get();
                //Elements element =  document.select("a[class=arrow show-participants ] target[_blank]");
                Elements element =  document.select("a[target=_blank]");


                str = element.attr("href");

                //str = element.attr("id");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }


    class ParseTitle extends AsyncTask<Void, Void, HashMap<String, String>> {

        @Override
        protected HashMap<String, String> doInBackground(Void... params) {
            HashMap<String, String> hashMap = new HashMap<>();

            try {
                Connection.Response res = Jsoup.connect("https://ecampus.smu.ac.kr/login/index.php")
                        .data("username", userID, "password", userpassword)
                        .method(Connection.Method.POST)
                        .timeout(1000)
                        .execute();
                //Map<String, String> loginCookies = res.cookies();
                Document doc = Jsoup.connect("http://ecampus.smu.ac.kr/").cookies(res.cookies()).get();


                Elements elements = doc.select(".course_subject");
                Elements elements1 = doc.select(".course_body");



                int i,t;
                Element temp, temp2, temp3, temp4;


                for(i=0; i<elements.size(); i++){
                    temp = elements.get(i);
                    temp2 = temp.select("a[href]").first();
                    hashMap.put(temp.text(), temp2.attr("abs:href"));
                }

                for (t=0; t<elements1.size(); t++){
                    temp3 = elements1.get(t);
                    temp4 = temp3.select("a[href]").first();
                    hashMap.put(temp3.text(), temp4.attr("abs:href"));
                }
/*
                for (Element element : elements) {
                    Element element1 = element.select("a[href]").first();
                    hashMap.put(element.text(), element1.attr("abs:href"));
                }
  */
            } catch (IOException e) {
                e.printStackTrace();
            }

            return hashMap;
        }
    }

    private void initialize()
    {
        InitializationRunnable init = new InitializationRunnable();
        new Thread(init).start();
    }



    class InitializationRunnable implements Runnable
    {
        public void run()
        {
            // 여기서부터 초기화 작업 처리
            // do_something
        }
    }


}
