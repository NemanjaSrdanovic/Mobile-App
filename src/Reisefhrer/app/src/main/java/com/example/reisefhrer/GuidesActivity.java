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

public class GuidesActivity extends AppCompatActivity {
    private RecycleAdapter mAdapter;
    final static private ArrayList<GradItem> guideList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);


        if(getIntent().hasExtra("key")){
            String wort = (String) getIntent().getExtras().get("key");
            assert wort != null;
            String word = wort.toLowerCase();
            boolean notInList = true;
            for(int i = 0; i < guideList.size(); i++){
                if (wort.equals(guideList.get(i).getmText1()))
                    notInList = false;
            }
            if (notInList) {
                int resID = getResources().getIdentifier(word, "drawable", getPackageName());
                guideList.add(new GradItem(resID, wort));
            }
        }

        if(guideList.size() >= 2)
        Collections.sort(guideList, new Comparator<GradItem>() {
            @Override
            public int compare(GradItem o1, GradItem o2) {
                return o1.getmText1().compareTo(o2.getmText1());
            }
        });

        setUpRecyclerView();
    }



    private void setUpRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecycleAdapter(guideList);

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

    static int listSize(){
        return guideList.size();
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
