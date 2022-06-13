package com.puco.iptv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class WlcmActivity extends Activity {

    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlcm);
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