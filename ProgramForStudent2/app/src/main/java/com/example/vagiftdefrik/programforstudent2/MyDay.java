package com.example.vagiftdefrik.programforstudent2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MyDay extends AppCompatActivity implements OnClickListener {

  ImageButton mHome, mMenu;

  private final String tag = "timetable.class";
  private String db_name = "timetable.db";

  private Timetable_Helper helper;
  SQLiteDatabase db;

  Cursor cur;
  LinearLayout[] lay = new LinearLayout[10];
  LinearLayout lay_time;

  String[] time_line = new String[]{"1교시\n09:00", "2교시\n10:00", "3교시\n11:00", "4교시\n12:00",
      "5교시\n13:00", "6교시\n14:00", "7교시\n15:00", "8교시\n16:00", "9교시\n17:00", "10교시\n18:00"};
  String[] day_line = new String[]{"시간", "월", "화", "수", "목", "금"};

  TextView[] time;
  TextView[] day;
  TextView[] data;
  TextView[] db_data;

  EditText put_subject;
  EditText put_classroom;
  int db_id;
  String db_classroom;
  String db_subject;

  String userID;
  String userpassword;


  public MyDay() {
    this.time = new TextView[this.time_line.length];
    this.day = new TextView[this.day_line.length];
    this.data = new TextView[this.time_line.length * this.day_line.length];
    this.db_data = new TextView[this.time_line.length * this.day_line.length];
  }

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.fragment_my_day);


    userID = getIntent().getStringExtra("login");
    userpassword = getIntent().getStringExtra("password");

    mHome = (ImageButton) findViewById(R.id.home);
    mMenu = (ImageButton) findViewById(R.id.menu);

    mHome.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
        //startActivity(new Intent(MyDay.this, Home_Page.class));

      }
    });

    mMenu.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

        //startActivity(new Intent(Timetable.this, Lesson.class));

        Intent intent = new Intent(MyDay.this, Lesson.class);
        intent.putExtra("login", userID);
        intent.putExtra("password", userpassword);
        startActivity(intent);
      }
    });

//        mMenu.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(onNewIntent(MyDay););
//            }
//        });

    String dbPath = this.getApplicationContext().getDatabasePath(this.db_name).getPath();
    Log.i("my db path=", dbPath);

    this.helper = new Timetable_Helper(this);
    int counter = this.helper.getCounter();
    Log.i("timetable.class", "counter = " + counter);

    MyDay.this.helper.add(1, "id", "dars");

    this.helper.search_data();

    LinearLayout.LayoutParams params_1 = new LinearLayout.LayoutParams(-1, -1);
    params_1.weight = 1.0F;
    params_1.width = this.getLcdSizeWidth() / 6;
    params_1.height = this.getLcdSizeHeight() / 14;
    params_1.setMargins(1, 1, 1, 1);
    params_1.gravity = 1;
    LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams(-1, -1);
    params_2.weight = 1.0F;
    params_2.width = this.getLcdSizeWidth() / 6;
    params_2.height = this.getLcdSizeHeight() / 20;
    params_2.setMargins(1, 1, 1, 1);

    this.lay_time = (LinearLayout) this.findViewById(R.id.lay_time);
    this.lay[0] = (LinearLayout) this.findViewById(R.id.lay_0);
    this.lay[1] = (LinearLayout) this.findViewById(R.id.lay_1);
    this.lay[2] = (LinearLayout) this.findViewById(R.id.lay_2);
    this.lay[3] = (LinearLayout) this.findViewById(R.id.lay_3);
    this.lay[4] = (LinearLayout) this.findViewById(R.id.lay_4);
    this.lay[5] = (LinearLayout) this.findViewById(R.id.lay_5);
    this.lay[6] = (LinearLayout) this.findViewById(R.id.lay_6);
    this.lay[7] = (LinearLayout) this.findViewById(R.id.lay_7);
    this.lay[8] = (LinearLayout) this.findViewById(R.id.lay_8);
    this.lay[9] = (LinearLayout) this.findViewById(R.id.lay_9);

    int i;
    for (i = 0; i < this.day.length; ++i) {
      this.day[i] = new TextView(this);
      this.day[i].setText(this.day_line[i]);
      this.day[i].setGravity(17);
      this.day[i].setBackgroundColor(Color.parseColor("#FAF4C0"));
      this.day[i].setTextSize(10.0F);
      this.lay_time.addView(this.day[i], params_2);
    }

    for (i = 0; i < this.time.length; ++i) {
      this.time[i] = new TextView(this);
      this.time[i].setText(this.time_line[i]);
      this.time[i].setGravity(17);
      this.time[i].setBackgroundColor(Color.parseColor("#EAEAEA"));
      this.time[i].setTextSize(10.0F);
      this.lay[i].addView(this.time[i], params_1);
    }

    this.cur = this.helper.getAll();
    this.cur.moveToFirst();
    i = 0;

    for (int id = 0; i < this.lay.length; ++i) {
      for (int j = 1; j < this.day_line.length; ++j) {
        this.data[id] = new TextView(this);
        this.data[id].setId(id);
        this.data[id].setTextSize(10.0F);
        this.data[id].setOnClickListener(this);
        this.data[id].setGravity(17);
        this.data[id].setBackgroundColor(Color.parseColor("#EAEAEA"));
        if (this.cur != null && !this.cur.isAfterLast()) {
          this.db_id = this.cur.getInt(0);
          this.db_subject = this.cur.getString(1);
          this.db_classroom = this.cur.getString(2);
          if (this.data[id].getId() == this.db_id) {
            this.data[id].setText(this.db_subject + "\n" + this.db_classroom);
            this.cur.moveToNext();
          }
        } else if (this.cur.isAfterLast()) {
          this.cur.close();
        }

        this.lay[i].addView(this.data[id], params_1);
        ++id;
      }
    }

  }

  public void onDestroy() {
    super.onDestroy();
    this.helper.close();
  }

  public void onClick(View view) {
    Cursor cursor = null;
    cursor = this.helper.getAll();
    int[] get = new int[50];

    if (cursor != null) {
      Log.i("timetable.class", "cursor is not null");
      cursor.moveToFirst();

      int i;
      for (i = 0; i < 50; ++i) {
        get[i] = 0;
      }

      while (!cursor.isAfterLast()) {
        get[cursor.getInt(0)] = cursor.getInt(0);
        Log.i("timetable.class", "get " + get[cursor.getInt(0)]);
        cursor.moveToNext();
      }

      for (i = 0; i < 50; ++i) {
        Log.i("timetable.class",
            "get[i] =" + get[i] + "   view.getid =" + view.getId() + "   data[i].getId() ="
                + this.data[i].getId());
        if (get[i] != 0 && get[i] == view.getId()) {
          this.update_timetable_dig(view.getId());
          break;
        }

        if (get[i] == 0 && view.getId() == this.data[i].getId()) {
          this.add_timetable_dig(view.getId());
          break;
        }
      }
    }
  }

  public void update_timetable_dig(final int id) {
    LinearLayout lay = (LinearLayout) View
        .inflate(this, R.layout.timetable_input_dig, (ViewGroup) null);
    AlertDialog.Builder ad = new AlertDialog.Builder(this);
    ad.setTitle("TimeTable"); //////////////////////??????????????/////////////////
    ad.setView(lay);
    this.put_subject = (EditText) lay.findViewById(R.id.input_subject);
    this.put_classroom = (EditText) lay.findViewById(R.id.input_classroom);
    Cursor c = this.helper.getAll();
    if (c != null) {
      c.moveToFirst();

      while (!c.isAfterLast()) {
        if (c.getInt(0) == id) {
          this.put_subject.setText(c.getString(1));
          this.put_classroom.setText(c.getString(2));
          break;
        }

        c.moveToNext();
      }
    }

    this.put_subject.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        MyDay.this.put_subject.setText((CharSequence) null);
      }
    });
    this.put_classroom.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        MyDay.this.put_classroom.setText((CharSequence) null);
      }
    });
    ad.setPositiveButton("수정", new android.content.DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
        int get_id = MyDay.this.data[id].getId();
        MyDay.this.helper.update((long) get_id, MyDay.this.put_subject.getText().toString(),
            MyDay.this.put_classroom.getText().toString());
        MyDay.this.data[id]
            .setText(MyDay.this.put_subject.getText() + "\n" + MyDay.this.put_classroom.getText());
      }
    });
    ad.setNegativeButton("삭제", new android.content.DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
        MyDay.this.helper.delete((long) id);
        MyDay.this.data[id].setText((CharSequence) null);
      }
    });
    ad.show();
  }

  public void add_timetable_dig(final int id) {
    final LinearLayout lay = (LinearLayout) View
        .inflate(this, R.layout.timetable_input_dig, (ViewGroup) null);
    AlertDialog.Builder ad = new AlertDialog.Builder(this);
    ad.setTitle("TimeTable");
    ad.setView(lay);
    ad.setPositiveButton("저장", new android.content.DialogInterface.OnClickListener() {
      EditText put_subject = (EditText) lay.findViewById(R.id.input_subject);
      EditText put_classroom = (EditText) lay.findViewById(R.id.input_classroom);

      public void onClick(DialogInterface dialog, int which) {
        int get_id = MyDay.this.data[id].getId();
        String strI = "" + get_id;
        MyDay.this.helper.add(get_id, this.put_subject.getText().toString(),
            this.put_classroom.getText().toString());
        MyDay.this.data[id]
            .setText(this.put_subject.getText() + "\n" + this.put_classroom.getText());
        MyDay.this.helper.add(1, "id", Integer.toString(get_id));
        MyDay.this.data[1].setText("id" + "\n" + Integer.toString(get_id));

      }
    });
    ad.setNegativeButton("취소", new android.content.DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    ad.show();
  }

  public int getLcdSizeWidth() {
    return ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
        .getWidth();
  }


  public int getLcdSizeHeight() {
    return ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
        .getHeight();
  }

  public void add_timetable_dig2() {
  }

//  public void startLesson(View view) {
//
//    Intent start_myday = new Intent(MyDay.this, Lesson.class);
//    start_myday.putExtra("login", userID);
//    start_myday.putExtra("password", userpassword);
//    startActivity(start_myday);
//
//  }

//  public void startThisActivity() {
//    Intent start_myday = new Intent(this, MyDay.class);
//    startActivity(start_myday);
//  }



}
