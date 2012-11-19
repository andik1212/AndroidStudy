package com.example.andik1212;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ActivityDetail extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            handleIntentExtras(getIntent());
        }
    }

    private void handleIntentExtras(Intent intent) {
        String text = "";
        if (intent.hasExtra(FragmentDetail.EXTRA_TEXT)) {
            text = intent.getStringExtra(FragmentDetail.EXTRA_TEXT);
        }

        FragmentDetail fragmentDetail = FragmentDetail.newInstance(text);
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container, fragmentDetail).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag_container);
//        if (fragment instanceof Fragment2_2) {
//            ((Fragment2_2) fragment).updateDetailsFragment();
//        }

        super.onBackPressed();
    }
}