package com.example.reisefhrer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Grad extends AppCompatActivity implements View.OnClickListener {
    String user_name;
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad);

        if(getIntent().hasExtra("key")){
            user_name = (String) getIntent().getExtras().get("key");
        }

        String sar = "Sarajevo is a city in which even strangers can feel at home. Neither geographically expansive nor characterised by large buildings, the city retains a particular, arresting charm with its abundance of busy café's and abiding tradition of hospitality."
                +"The city's breathtaking backdrop of seemingly endless hills and towering mountains have in a sense always isolated the city, creating a timeless world, which despite its seclusion has always kept its doors open to the rest of the world. Although Sarajevo is a capital city typified by the hustle and bustle of everyday life, it also possesses a unique ambience that seeps into the soul." +
                "The city's breathtaking backdrop of seemingly endless hills and towering mountains have in a sense always isolated the city, creating a timeless world, which despite its seclusion has always kept its doors open to the rest of the world. Although Sarajevo is a capital city typified by the hustle and bustle of everyday life, it also possesses a unique ambience that seeps into the soul." ;


        String vie = "Vienna  is the federal capital, largest city and one of nine states of Austria. Vienna is Austria's primate city, with a population of about 1.9 million, and its cultural, economic, and political centre. In 2001, the city centre was designated a UNESCO World Heritage Site."
                +"In July 2017 it was moved to the list of World Heritage in Danger. Vienna is known for its high quality of life. In a 2005 study of 127 world cities, the Economist Intelligence Unit ranked the city first (in a tie with Vancouver and San Francisco) for the world's most liveable cities."
                +"Between 2011 and 2015, Vienna was ranked second, behind Melbourne.In 2018, it replaced Melbourne as the number one spot. For ten consecutive years (2009–2019), the human-resource-consulting firm Mercer ranked Vienna first in its annual \"Quality of Living\" survey of hundreds of cities around the world."
                +"Monocle's 2015 \"Quality of Life Survey\" ranked Vienna second on a list of the top 25 cities in the world \"to make a base within.";

        String grz ="Graz is the capital of Styria and the second-largest city in Austria after Vienna. On 1 January 2019, it had a population of 328,276. In 2015, the population of the Graz larger urban zone who had principal residence status stood at 633,168.\n" +
                "Graz has a long tradition as seat of universities: its six universities have almost 60,000 students. Its historic centre is one of the best-preserved city centres in Central Europe.\n" +
                "For centuries, Graz (Slovene: Gradec) was more important to Slovenes, both politically and culturally, than the capital of Slovenia, Ljubljana, and it remains influential to this day.\n" +
                "In 1999, Graz was added to the UNESCO list of World Heritage Sites, and the site was extended in 2010 with Eggenberg Palace (German: Schloss Eggenberg). Graz was the sole Cultural Capital of Europe of 2003 and became a City of Culinary Delights in 2008.";

        String mstr="Mostar is a city and the administrative center of Herzegovina-Neretva Canton of the Federation of Bosnia and Herzegovina, an entity of Bosnia and Herzegovina. Inhabited by 105,797 people, it is the most important city in the Herzegovina region, serving as its cultural and economic capital.\n" +
                "Mostar is situated on the Neretva River and is the fifth-largest city in the country. Mostar was named after the bridge keepers (mostari) who in the medieval times guarded the Stari Most (Old Bridge) over the Neretva. The Old Bridge, built by the Ottomans in the 16th century, is one of Bosnia and Herzegovina's most visited landmarks, and is considered an exemplary piece of Islamic architecture in the Balkans.\n" +
                "Human settlements on the river Neretva, between the Hum Hill and the Velež Mountain, have existed since prehistory, as witnessed by discoveries of fortified enceintes and cemeteries. Evidence of Roman occupation was discovered beneath the present town.";

        if(user_name.equals("Sarajevo")) {
            TextView text = findViewById(R.id.textView);
            text.setText(user_name);

            TextView text2 = findViewById(R.id.textView3);
            text2.setText(sar);

            ImageView grad = findViewById(R.id.gradimg);
            grad.setImageResource(R.drawable.sarajevo);
        }

        if(user_name.equals("Vienna")) {
            TextView text = findViewById(R.id.textView);
            text.setText(user_name);


            TextView text2 = findViewById(R.id.textView3);
            text2.setText(vie);

            ImageView grad = findViewById(R.id.gradimg);
            grad.setImageResource(R.drawable.vienna);
        }

        if(user_name.equals("Graz")) {
            TextView text = findViewById(R.id.textView);
            text.setText(user_name);

            TextView text2 = findViewById(R.id.textView3);
            text2.setText(grz);

            ImageView grad = findViewById(R.id.gradimg);
            grad.setImageResource(R.drawable.graz);
        }


        if(user_name.equals("Mostar")) {
            TextView text = findViewById(R.id.textView);
            text.setText(user_name);

            TextView text2 = findViewById(R.id.textView3);
            text2.setText(mstr);

            ImageView grad = findViewById(R.id.gradimg);
            grad.setImageResource(R.drawable.mostar);
        }

        Button grad = findViewById(R.id.button2);
        if ((Boolean) MainActivity.get_offline())
            grad.setVisibility(View.INVISIBLE);
        else grad.setVisibility(View.VISIBLE);
        grad.setOnClickListener(this);



        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(RestaurantActivity.this, Grad.class));
        Intent start = new Intent(Grad.this, CityList.class);
        startActivity(start);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.acc_menu, menu);

        return true;
    }

    /*BUTTON ACCOUNT/USER*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account:
                Intent intent = new Intent(this, activity_user.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                /*case R.id.navigation_stadt:

                    break;*/
                case R.id.navigation_hotel:
                    Intent start = new Intent(Grad.this, HotelActivity.class);
                    start.putExtra("key", user_name);
                    startActivity(start);
                    break;
                case R.id.navigation_attractions:
                    Intent start1 = new Intent(Grad.this, AttractionActivity.class);
                    start1.putExtra("key", user_name);
                    startActivity(start1);
                    break;
                case R.id.navigation_restaurant:
                    Intent start2 = new Intent(Grad.this, RestaurantActivity.class);
                    start2.putExtra("key", user_name);
                    startActivity(start2);

                    break;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        Toast.makeText(Grad.this, "guide Added", Toast.LENGTH_LONG).show();
        Intent start = new Intent(this, GuidesActivity.class);
        start.putExtra("key", user_name);
        startActivity(start);
    }
}
