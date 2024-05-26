package com.example.mangago;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

public class GeckoviewAactivity extends AppCompatActivity {
    private static GeckoRuntime sRuntime;
    private GeckoSession session = new GeckoSession();
    private AdView adView;
    AdRequest adRequest;

    private class MyNavigationDelegate implements GeckoSession.NavigationDelegate {
        public boolean canGoBack = false;
        public boolean canGoForward = false;

        @Override
        public void onCanGoBack(GeckoSession session, boolean canGoBack) {
            this.canGoBack = canGoBack;
        }

        @Override
        public void onCanGoForward(GeckoSession session, boolean canGoForward) {
            this.canGoForward = canGoForward;
        }
    }

    @Override
    public void onBackPressed() {
        if (navigationDelegate.canGoBack) {
            session.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private MyNavigationDelegate navigationDelegate = new MyNavigationDelegate();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.geckview);
        String linkToLoad = getIntent().getStringExtra("key");
        GeckoView view = findViewById(R.id.geckoview);

        session.setContentDelegate(new GeckoSession.ContentDelegate() {});
        session.setNavigationDelegate(navigationDelegate);
        if (sRuntime == null) {
            Log.i("AMIT", "onCreate: null");
            // GeckoRuntime can only be initialized once per process
            sRuntime = GeckoRuntime.create(this);
        }
        session.open(sRuntime);
        view.setSession(session);
        assert linkToLoad != null;
        Toast toast = Toast.makeText(getApplicationContext(), "Going to a third party website", Toast.LENGTH_SHORT);

        // Show the toast
        toast.show();
        session.loadUri(linkToLoad);

        BottomNavigationView bottomNavigationMenuView = (BottomNavigationView) findViewById(R.id.bottomnavigationWeb);
        bottomNavigationMenuView.setOnItemSelectedListener(menuItem -> {
            Log.i("AMIT", "onCreate: "+menuItem.getItemId());
            switch (menuItem.getItemId()) {
                case R.id.goBack:
                    onBackPressed();
                    break;
                case R.id.goForward:
                    if (navigationDelegate.canGoForward) {
                        session.goForward();
                    }
                    break;
                case R.id.home:
                    super.onBackPressed();
                    break;
                case R.id.exit:
                    finishAffinity();
                    System.exit(0);
            }
            return true;
        });

        adView = (AdView) findViewById(R.id.ad_view_web);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

}
