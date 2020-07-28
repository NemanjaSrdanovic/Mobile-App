package com.example.reisefhrer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class activity_user extends AppCompatActivity {

    TextView username;
    Button buttonDow;
    Button buttonLog;
    String textinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        username = (TextView) findViewById(R.id.username_info);
        buttonDow = (Button) findViewById(R.id.btn_download);
        buttonLog = (Button) findViewById(R.id.btn_logout);

        activity_login src = new activity_login();
        textinfo =  src.getUser();
        username.setText(textinfo);


        /*BUTTON DOWNLOAD*/
        buttonDow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_user.this, GuidesActivity.class);
                startActivity(intent);
            }
        });

        /*BUTTON LOGOUT*/
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_user.this, MainActivity.class);
                /*ToDO Nemanja LOGIN PAGE/FirstPage Intent intent = new Intent(activity_user.this, LoginActivity.class);*/
                startActivity(intent);
            }
        });

    }
}
