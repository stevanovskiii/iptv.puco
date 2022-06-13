package com.puco.iptv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;


public class MainActivity
    extends Activity
{
    @Override
    protected void onStart()
    {
        super.onStart();
        
        try {
            Intent intent = new Intent();
            intent.setClassName(_IPTV_CORE_PACKAGE_NAME, _IPTV_CORE_CLASS_NAME);

            // Playlista
             String playlistUrl = "https://github.com/stevanovskiii/iptv.puco/blob/master/app/src/main/res/drawable-mdpi/kanali.xspf";
             intent.setData(Uri.parse(playlistUrl));
            
            intent.putExtra("package", getPackageName());


            startActivity(intent);
            finish();
        } catch (ActivityNotFoundException e) {
            showIptvCoreNotFoundDialog();
        }
    }
    
    public void showIptvCoreNotFoundDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_core_not_installed_title);
        builder.setMessage(R.string.dialog_core_not_installed_message);
        builder.setPositiveButton(R.string.dialog_button_install,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int id)
                {
                    try {
                        // try to open Google Play app first 
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + _IPTV_CORE_PACKAGE_NAME)));
                    } catch (ActivityNotFoundException e) {
                        // if Google Play is not found for some reason, let's open browser
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + _IPTV_CORE_PACKAGE_NAME)));
                    }
                }
            });
        builder.setNegativeButton(R.string.dialog_button_cancel,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int id)
                {
                    // if cancelled - just close the app
                    finish();
                }
            });
        builder.setCancelable(false);
        builder.create().show();
    }

    
    private static final String _IPTV_CORE_PACKAGE_NAME = "ru.iptvremote.android.iptv.core";
    private static final String _IPTV_CORE_CLASS_NAME = _IPTV_CORE_PACKAGE_NAME + ".ChannelsActivity";
}
