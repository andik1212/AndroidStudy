package com.example.andik1212;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.list_frag, new FragmentList()).commit();
        }
    }
}
