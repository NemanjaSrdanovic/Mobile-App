package com.example.reisefhrer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    Button signup;
    static String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.btn_login);
        signup = (Button) findViewById(R.id.btn_signup);
        username = (EditText) findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = username.getText().toString();
                String text2 = password.getText().toString();
                user=username.getText().toString();
                Intent intent = new Intent(activity_login.this, CityList.class);
                if(text1.length() == 0 || text2.length() == 0){
                    Toast.makeText(activity_login.this,"Username or Password not correct\n\t\t\t\t\t\t\t\t\t\t\tTry again",Toast.LENGTH_SHORT).show();
                }
                else{ startActivity(intent); }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_login.this, activity_signup.class);
                startActivity(intent);

            }
        });
    }

    public String getUser(){return user;}
}
