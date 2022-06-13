package com.puco.iptv;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class Language {

    private Context context;
    public Language(Context ctx){
        context = ctx;
    }
    public void updateResource(String code){
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

}