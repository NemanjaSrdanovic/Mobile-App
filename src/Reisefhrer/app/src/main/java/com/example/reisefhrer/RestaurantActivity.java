package com.example.reisefhrer;

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

public class RestaurantActivity extends AppCompatActivity {

    String grad;
    int[] rImg = {R.drawable.nordsee, R.drawable.vapiano
        , R.drawable.brewing_company , R.drawable.figls ,
            R.drawable.regina_margherita
            /* ,R.drawable. , R.drawable. , R.drawable.*/
    } ;

    String[] rName  = {"Nordsee", "Vapiano" , "1516-Brewing Company", "Figls", "Regina Margherita"};

    String[] rStar = {"* * *","* * * *", "* * * * *"};

    String[] r_urls = {"https://www.google.at/maps/search/nordsee/@48.2187918,16.3399599,14z/data=!3m1!4b1","https://www.google.at/maps/search/vapiano/@48.2188463,16.3399599,14z/data=!3m1!4b1","https://www.google.com/maps/place/1516+Brewing+Company/@48.2033843,16.3707866,17z/data=!3m1!4b1!4m5!3m4!1s0x476d079dba3f5d8f:0xc1980cfd30852a9e!8m2!3d48.2033843!4d16.3729753","https://www.google.com/maps/place/Figls/@48.254124,16.3493373,17z/data=!3m1!4b1!4m5!3m4!1s0x476d06202ab1d7fd:0xa179564769875482!8m2!3d48.254124!4d16.351526",
            "https://www.google.com/maps/place/Regina+Margherita/@48.2096632,16.3653122,17z/data=!3m1!4b1!4m5!3m4!1s0x476d079841a8a7ff:0xd55684635f3c691e!8m2!3d48.2096632!4d16.3675009"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        ListView res = (ListView) findViewById(R.id.restoranList);
        CustomeAdapter customeAdapter = new CustomeAdapter();

        res.setAdapter(customeAdapter);


        if(getIntent().hasExtra("key")){
            grad = (String) getIntent().getExtras().get("key");
        }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(RestaurantActivity.this, Grad.class));
        Intent start = new Intent(RestaurantActivity.this, Grad.class);
        start.putExtra("key", grad);
        startActivity(start);
        finish();

    }


    // List adapter
    class CustomeAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return rImg.length;
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
            convertView = getLayoutInflater().inflate(R.layout.restaurant_listview, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView2);
            TextView rIme = (TextView) convertView.findViewById(R.id.textView4);
            TextView rRating = (TextView) convertView.findViewById(R.id.textView5);
            String url = "";

            imageView.setImageResource(rImg[position]);
            rIme.setText(rName[position]);
            url=r_urls[position];
            if (position>=3) rRating.setText(rStar[2]);
            else rRating.setText(rStar[position]);
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
                case R.id.navigation_hotel:
                    Intent start1 = new Intent(RestaurantActivity.this, HotelActivity.class);
                    start1.putExtra("key", grad);
                    startActivity(start1);

                    break;
                case R.id.navigation_attractions:
                    Intent start2 = new Intent(RestaurantActivity.this, AttractionActivity.class);
                    start2.putExtra("key", grad);
                    startActivity(start2);

                    break;
                case R.id.navigation_restaurant:

                    break;
            }
            return false;
        }
    };
}
