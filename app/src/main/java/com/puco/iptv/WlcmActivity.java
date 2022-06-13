package com.puco.iptv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.analytics.FirebaseAnalytics;


public class WlcmActivity extends Activity {

    int a;
    private Button logout;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlcm);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WlcmActivity.this,SigninActivity.class));
            }
        });
    }


    public void IPTV(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void Kontakt(View view) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.infotextlayout);
        ll.setVisibility(View.INVISIBLE);
        LinearLayout ll1 = (LinearLayout) findViewById(R.id.kontakttextlayout);
        ll1.setVisibility(View.VISIBLE);
    }

    public void INFOKLIK(View view) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.infotextlayout);
        ll.setVisibility(View.VISIBLE);
        LinearLayout ll1 = (LinearLayout) findViewById(R.id.kontakttextlayout);
        ll1.setVisibility(View.GONE);
    }
}