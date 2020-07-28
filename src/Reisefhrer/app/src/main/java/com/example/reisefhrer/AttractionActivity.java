package com.example.reisefhrer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AttractionActivity extends AppCompatActivity {

    private TextView mTextMessage;
    String grad;
    int[] aImg = {R.drawable.foltermuseum, R.drawable.inter
            , R.drawable.hofburg , R.drawable.haus_des_meeres ,
            R.drawable.prater
    };

    String[] aName  = {"Foltermuseum", "Albertina" , "Hofburg", "Haus des Meeres", "Wiener Prater"};

    String[] aStar = {"* * *","* * * *", "* * * * *"};

    String[] a_urls = {"https://www.google.com/maps/place/Foltermuseum+Wien/@48.1977593,16.3353773,14z/data=!4m8!1m2!2m1!1sattraktionen!3m4!1s0x476d0789333d669f:0x1e97b45c46b3ff09!8m2!3d48.197958!4d16.352526",
            "https://www.google.com/maps/place/Albertina/@48.1978139,16.3353773,14z/data=!3m1!5s0x476d079beeccb3a7:0x6e3ce34be1d4e11c!4m8!1m2!2m1!1sattraktionen!3m4!1s0x476d079bf27e5ac1:0x66ef8a989f37deb8!8m2!3d48.2046992!4d16.3681824",
            "https://www.google.com/maps/place/Hofburg+Wien/@48.1977048,16.3353773,14z/data=!4m8!1m2!2m1!1sattraktionen!3m4!1s0x476d0799bf2006df:0x8685168014c4425f!8m2!3d48.2076579!4d16.3660538",
            "https://www.google.com/maps/place/Haus+des+Meeres+-+Aqua+Terra+Zoo/@48.1976502,16.3353773,14z/data=!4m8!1m2!2m1!1sattraktionen!3m4!1s0x476d07899552a1ef:0x191e35f845e9a632!8m2!3d48.1976502!4d16.3528868",
            "https://www.google.com/maps/place/Prater/@48.2264893,16.3426462,14z/data=!4m8!1m2!2m1!1sattraktionen!3m4!1s0x476d073f4daba163:0xfce7699ce9a8b1f4!8m2!3d48.2167269!4d16.3980286"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);
        ListView res = (ListView) findViewById(R.id.attractionlist);
        AttractionActivity.CustomeAdapter customeAdapter = new AttractionActivity.CustomeAdapter();

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
            return aImg.length;
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

            imageView.setImageResource(aImg[position]);
            rIme.setText(aName[position]);
            url=a_urls[position];
            if (position>=3) rRating.setText(aStar[2]);
            else rRating.setText(aStar[position]);
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
        Intent start = new Intent(AttractionActivity.this, Grad.class);
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
                    Intent start = new Intent(AttractionActivity.this, Grad.class);
                    start.putExtra("key", grad);
                    startActivity(start);
                    break;*/
                case R.id.navigation_hotel:
                    Intent start1 = new Intent(AttractionActivity.this, HotelActivity.class);
                    start1.putExtra("key", grad);
                    startActivity(start1);
                    break;
                case R.id.navigation_attractions:

                    break;
                case R.id.navigation_restaurant:
                    Intent start2 = new Intent(AttractionActivity.this, RestaurantActivity.class);
                    start2.putExtra("key", grad);
                    startActivity(start2);

                    break;
            }
            return false;
        }
    };
}
