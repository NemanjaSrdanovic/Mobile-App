package com.example.reisefhrer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HotelActivity extends AppCompatActivity {
    private TextView mTextMessage;
    String grad;
    int[] hImg = {R.drawable.hilton, R.drawable.inter
            , R.drawable.theater , R.drawable.belle ,
            R.drawable.grand
            /* ,R.drawable. , R.drawable. , R.drawable.*/
    } ;

    String[] hName  = {"Hilton", "InterContinental Vienna" , "Theaterhotel Wien", "Hotel Bellevue Wien", "Grand Hotel Wien"};

    String[] hStar = {"* * *","* * * *", "* * * * *"};

    String[] h_urls = {"https://www.google.com/maps/search/Hilton+Hotel+Wien/@48.2021438,16.3546234,14z/data=!3m1!4b1",
            "https://www.google.com/maps/place/InterContinental+Vienna/@48.2017561,16.377014,17z/data=!3m1!4b1!4m5!3m4!1s0x476d0776fb915795:0x757f7fbeef5e812f!8m2!3d48.2017525!4d16.3792027",
            "https://www.google.com/maps/place/Theaterhotel+Wien/@48.2095838,16.349244,17z/data=!3m1!4b1!4m5!3m4!1s0x476d0794c77bed65:0x2db3ab8c68a8d1f3!8m2!3d48.2095802!4d16.3514327",
            "https://www.google.com/maps/place/Hotel+Bellevue+Wien/@48.2264585,16.3579671,17z/data=!3m1!4b1!4m5!3m4!1s0x476d07c9e3c2b3fd:0x1f4eab698903262c!8m2!3d48.2264549!4d16.3601558",
            "https://www.google.com/maps/place/Grand+Hotel+Wien/@48.202113,16.3699443,17z/data=!3m1!4b1!4m5!3m4!1s0x476d079d056b9a9d:0x6c3c3c799ea79544!8m2!3d48.202113!4d16.372133"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ListView res = (ListView) findViewById(R.id.hotellist);
        HotelActivity.CustomeAdapter customeAdapter = new HotelActivity.CustomeAdapter();

        res.setAdapter(customeAdapter);


        if(getIntent().hasExtra("key")){
            grad = (String) getIntent().getExtras().get("key");
        }

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    class CustomeAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return hImg.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.hotels_listview, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView3);
            TextView rIme = (TextView) convertView.findViewById(R.id.textView7);
            TextView rRating = (TextView) convertView.findViewById(R.id.textView8);
            String url = "";

            imageView.setImageResource(hImg[position]);
            rIme.setText(hName[position]);
            url=h_urls[position];
            if (position>=3) rRating.setText(hStar[2]);
            else rRating.setText(hStar[position]);
            final String finalUrl = url;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uriurl = Uri.parse(finalUrl);
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriurl);
                    startActivity(launchBrowser);
                }
            });
            return convertView;
        }

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(RestaurantActivity.this, Grad.class));
        Intent start = new Intent(HotelActivity.this, Grad.class);
        start.putExtra("key", grad);
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
                    Intent start = new Intent(HotelActivity.this, Grad.class);
                    start.putExtra("key", grad);
                    startActivity(start);
                    break;*/
                case R.id.navigation_hotel:

                    break;
                case R.id.navigation_attractions:
                    Intent start1 = new Intent(HotelActivity.this, AttractionActivity.class);
                    start1.putExtra("key", grad);
                    startActivity(start1);

                    break;
                case R.id.navigation_restaurant:
                    Intent start2 = new Intent(HotelActivity.this, RestaurantActivity.class);
                    start2.putExtra("key", grad);
                    startActivity(start2);

                    break;
            }
            return false;
        }
    };
}
