package com.example.vagiftdefrik.programforstudent2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button button;
    EditText username, password;
    String sUser, sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Spinner spinner = findViewById(R.id.majorSpinner);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,R.array.major, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);





        button = (Button) findViewById(R.id.loginButton);
        username = (EditText) findViewById(R.id.idText);
        password = (EditText) findViewById(R.id.passwordText);






        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sUser = username.getText().toString();  /// zapolnenie pri registratii
                sPassword = password.getText().toString();/// toje samoe

                if (sUser.equals("") && sPassword.equals(""))   /// proverka  napisano li sh to to
                {
                 // startActivity(new Intent(MainActivity.this, Home_Page.class));   // ecli ne nujna registratiya
                    Toast.makeText(getApplicationContext(),
                            "Please enter your UserID or Password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, CheakLogin.class);   /// zdes idet ot[ravka na CheakLogin
                    intent.putExtra("login", sUser);// otpravka
                    intent.putExtra("password", password.getText().toString()); // otpravaka

                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
