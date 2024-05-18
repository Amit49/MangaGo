package com.example.mangago;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

public class GeckoviewAactivity extends AppCompatActivity {
    private static GeckoRuntime sRuntime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.geckview);

        String linkToLoad = getIntent().getStringExtra("key");
        GeckoView view = findViewById(R.id.geckoview);
        GeckoSession session = new GeckoSession();

        // Workaround for Bug 1758212
        session.setContentDelegate(new GeckoSession.ContentDelegate() {});
//
        if (sRuntime == null) {
            Log.i("AMIT", "onCreate: null");
            // GeckoRuntime can only be initialized once per process
            sRuntime = GeckoRuntime.create(this);
        }
//
        session.open(sRuntime);
        view.setSession(session);
        assert linkToLoad != null;
        session.loadUri(linkToLoad);
        Log.i("AMIT", "loadUri: called");
    }

}
