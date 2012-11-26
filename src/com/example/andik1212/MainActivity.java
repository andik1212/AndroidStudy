package com.example.andik1212;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    public static String INTERNET_STATUS = "com.example.andik1212.DOWN";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        checkInternet();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.list_frag, new FragmentList()).commit();
        }
    }
    private void checkInternet(){

        Context context = MainActivity.this.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            INTERNET_STATUS = "com.example.andik1212.UP";
        }
        else {
            INTERNET_STATUS = "com.example.andik1212.DOWN";
        }
        sendBroadcast(new Intent(INTERNET_STATUS));

    }
}
