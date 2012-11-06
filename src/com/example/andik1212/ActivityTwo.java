package com.example.andik1212;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ActivityTwo extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        if (savedInstanceState == null) {
            handleIntentExtras(getIntent());
        }
    }

    private void handleIntentExtras(Intent intent) {
        String text = "";
        if (intent.hasExtra(Fragment2_1.EXTRA_TEXT)) {
            text = intent.getStringExtra(Fragment2_1.EXTRA_TEXT);
        }

        Fragment2_1 fragment_2_1 = Fragment2_1.newInstance(text);
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container, fragment_2_1).commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag_container);
        if (fragment instanceof Fragment2_2) {
            ((Fragment2_2) fragment).updateDetailsFragment();
        }

        super.onBackPressed();
    }
}