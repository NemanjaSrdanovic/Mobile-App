package com.example.reisefhrer;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button signup;
    Switch swit;
    private long backpressedtime;
    static boolean offline = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login_path);
        signup = (Button) findViewById(R.id.signup_path);
        swit = (Switch) findViewById(R.id.switch2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_login.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_signup.class);
                startActivity(intent);
            }
        });

        swit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (GuidesActivity.listSize() == 0)
                    Toast.makeText(MainActivity.this, "You have no Guides!", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(MainActivity.this, GuidesActivity.class);
                    if (isChecked) {
                        offline = true;
                        Toast.makeText(MainActivity.this, "You're OFFLINE", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } else {
                        offline = false;
                        Toast.makeText(MainActivity.this, "You're ONLINE", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    static boolean get_offline(){
        return offline;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finishAffinity(); }

}

