package com.example.mangago;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.mozilla.geckoview.GeckoRuntime;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<MangaLinkModel> mangaLinkModels = new ArrayList<>();
    private static GeckoRuntime sRuntime;
    @SuppressLint({"MissingInflatedId", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.mRecyclerview);
        setMangaLinkModels();
        MM_RecyclerViewAdapter adapter = new MM_RecyclerViewAdapter(this,mangaLinkModels);
        recyclerView.setAdapter(adapter);
//        DividerItemDecoration dividerItem = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        dividerItem.setDrawable(ContextCompat.getDrawable(this,R.drawable.divider));
//        recyclerView.addItemDecoration(dividerItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BottomNavigationView bottomNavigationMenuView = (BottomNavigationView) findViewById(R.id.bottomnavigation);
        BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) findViewById(R.id.goBack);
        bottomNavigationItemView.setClickable(false);
        bottomNavigationItemView = (BottomNavigationItemView) findViewById(R.id.goForward);
        bottomNavigationItemView.setClickable(false);
//        bottomNavigationItemView = (BottomNavigationItemView) findViewById(R.id.home);
//        bottomNavigationItemView.setActivated(false);
//        bottomNavigationItemView = (BottomNavigationItemView) findViewById(R.id.exit);
//        bottomNavigationItemView.setActiveIndicatorEnabled(false);
        bottomNavigationMenuView.setOnItemSelectedListener(menuItem -> {
            Log.i("AMIT", "onCreate: "+menuItem.getItemId());
            switch (menuItem.getItemId()) {
                case R.id.goBack:
                    break;
                case R.id.goForward:
                    break;
                case R.id.home:
                    break;
                case R.id.exit:
                    finishAndRemoveTask();
            }
            return true;
        });
    }


    private void setMangaLinkModels() {
        String[] mangaNames = getResources().getStringArray(R.array.MangaNameList);
        String[] mangaLinks = getResources().getStringArray(R.array.MangaWebList);

        for (int i = 0; i < mangaNames.length; i++) {
            mangaLinkModels.add(new MangaLinkModel(mangaNames[i],mangaLinks[i]));
        }
    }
}