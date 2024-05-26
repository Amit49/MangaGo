package com.example.mangago;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.mozilla.geckoview.GeckoRuntime;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<MangaLinkModel> mangaLinkModels = new ArrayList<>();
    private static GeckoRuntime sRuntime;
    private InterstitialAd mInterstitialAd;
    private AdView adView;
    AdRequest adRequest;
    private String TAG = "MangaGo";

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

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView = findViewById(R.id.ad_view);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        InterstitialAd.load(this,getString(R.string.opener_ad_unit_id), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.show(MainActivity.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
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