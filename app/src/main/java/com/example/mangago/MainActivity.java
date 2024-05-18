package com.example.mangago;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<MangaLinkModel> mangaLinkModels = new ArrayList<>();
    private static GeckoRuntime sRuntime;
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
    }

    private void setMangaLinkModels() {
        String[] mangaNames = getResources().getStringArray(R.array.MangaNameList);
        String[] mangaLinks = getResources().getStringArray(R.array.MangaWebList);

        for (int i = 0; i < mangaNames.length; i++) {
            mangaLinkModels.add(new MangaLinkModel(mangaNames[i],mangaLinks[i]));
        }
    }
}