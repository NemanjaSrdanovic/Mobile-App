package com.example.reisefhrer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_signup extends AppCompatActivity {

    EditText fname;
    EditText lname;
    EditText email;
    EditText password;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname= (EditText) findViewById(R.id.editTextfname);
        lname= (EditText) findViewById(R.id.editTextlname);
        email= (EditText) findViewById(R.id.editTextemail);
        password=(EditText) findViewById(R.id.editTextpassword);
        reg= (Button) findViewById(R.id.buttonRegister);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = fname.getText().toString();
                String text2 = lname.getText().toString();
                String text3 = email.getText().toString();
                String text4 = password.getText().toString();
                Intent startIntent=new Intent(activity_signup.this, activity_login.class);
                if(text1.length() == 0 || text2.length() == 0 || text3.length() == 0 || text4.length() == 0){
                    Toast.makeText(activity_signup.this,"Please complete all required fields\n\t\t\t\t\t\t\t\t\t\t\tTry again",Toast.LENGTH_SHORT).show();
                }else
                {startActivity(startIntent);}
            }
        });

    }
}
