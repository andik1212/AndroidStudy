package com.example.andik1212;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class FragmentDetail extends Fragment {

    public static final String EXTRA_TEXT = "extra_text";

    private static final int REQUEST_NUMBER = 1;

    private View view;
    private String[] text;

    public static FragmentDetail newInstance(String[] text) {
        FragmentDetail f = new FragmentDetail();
        Bundle args = new Bundle();
        args.putStringArray(EXTRA_TEXT, text);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            text = args.getStringArray(EXTRA_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            text = savedInstanceState.getStringArray("text");
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) view.findViewById(R.id.text);
                tv.setText(text[0]);
                WebView wv = (WebView) view.findViewById(R.id.web);
                wv.loadData(text[1], "text/html", null);

            }
        });

//        Button replace = (Button) view.findViewById(R.id.details_btn);
//        replace.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment2_2 numberFragment = Fragment2_2.newInstance(text);
//                numberFragment.setTargetFragment(Fragment2_1.this, REQUEST_NUMBER);
//
//                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.frag_container, numberFragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("text", text);
    }

//    public void setText(final String number, final int requestCode) {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                switch (requestCode) {
//                    case 1:
//                        text = text + " " + number;
//                        break;
//                }
//            }
//        });
//    }
}
