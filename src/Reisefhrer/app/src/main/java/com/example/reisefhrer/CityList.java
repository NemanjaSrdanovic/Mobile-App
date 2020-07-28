package com.example.reisefhrer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CityList extends AppCompatActivity {
    private RecycleAdapter mAdapter;
    private ArrayList<GradItem> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        fillCity();

        Collections.sort(cityList, new Comparator<GradItem>() {                     // Sortierung von Liste
            @Override
            public int compare(GradItem o1, GradItem o2) {
                return o1.getmText1().compareTo(o2.getmText1());
            }
        });

        setUpRecyclerView();

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        //startActivity(new Intent(RestaurantActivity.this, Grad.class));
        Intent start = new Intent(CityList.this, activity_user.class);
        startActivity(start);
        finish();

    }

        private void fillCity(){
            cityList = new ArrayList<>();
            cityList.add(new GradItem(R.drawable.vienna, "Vienna"));
            cityList.add(new GradItem(R.drawable.sarajevo, "Sarajevo"));
            cityList.add(new GradItem(R.drawable.graz, "Graz"));
            cityList.add(new GradItem(R.drawable.mostar, "Mostar"));
           // cityList.add(new GradItem(R.drawable.beograd, "Belgrade"));

        }

        private void setUpRecyclerView() {
            RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new RecycleAdapter(cityList);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.srch_menu, menu);
            inflater.inflate(R.menu.acc_menu, menu);

            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();

            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    mAdapter.getFilter().filter(newText);
                    return false;
                }
            });
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


}
