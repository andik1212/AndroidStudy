package com.example.andik1212;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.andik1212.helper.Article;
import com.example.andik1212.helper.ArticleCollection;
import com.example.andik1212.helper.CustomArrayAdapter;
import com.example.andik1212.helper.GetNews;

public class FragmentList extends Fragment {
    private static FragmentList Instance;

    static String[] values; //{ "new1", "new2", "new3", "new4", "new5", "new6"  };
    static String[] date;
    static String[] content;
    GetNews loader;
    //временно
//        String[] content = new String[0];


    private static class Self {
        public View view;
        public boolean restore;
        public FragmentActivity activity;
        public ArticleCollection articles;
    }

    static Self _self = new Self();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _self.activity = getActivity();
        Instance = this;
    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (_self.restore && _self.articles.size() > 0){
            updateUi();
            _self.restore = false;
        } else{
            if (MainActivity.INTERNET_STATUS == "UP"){
                loadData();
            }
            else{
                showLoadingError();
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();

        _self.restore = true;
    }

    private void loadData() {
        showLoadingIndicator();
        loader = new GetNews();
        loader.run();
        while(!GetNews.finished){}
        _self.articles=loader.returner();
        Article art;
        values = new String[_self.articles.size()];
        date = new String[_self.articles.size()];
        content = new String[_self.articles.size()];
        for (int i = 0; i < _self.articles.size(); i++){
            art = (Article)_self.articles.elementAt(i);
            values[i]= art.getTitle();
            date[i]= art.getDate();
            date[i]=date[i].substring(0, 10);
            content[i]= art.getContent();

//            Toast.makeText(_self.activity, art.getTitle(), Toast.LENGTH_SHORT).show();
        }
        hideLoadingIndicator();
        updateUi();
    }


    private void showLoadingError() {
        _self.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                Toast.makeText(_self.activity, "We loose an internet connection ;(", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateUi() {
        _self.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                if (_self.articles.size() > 0){
                    ListView list = (ListView) view.findViewById(R.id.list);

//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2,
//                        android.R.id.text1, values);
                    CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(), values, date);

                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String text = (String) adapterView.getItemAtPosition(position);
                            String[] extra = new String[2];
                            extra[0] = values[position];
                            extra[1] = content[position];
                            Intent intent = new Intent(getActivity(), ActivityDetail.class);
                            intent.putExtra(FragmentDetail.EXTRA_TEXT, extra);
                            startActivity(intent);
                        }
                    });
                }

            }
        });
    }

    private void hideLoadingIndicator() {
        _self.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                Toast.makeText(_self.activity, "Finished ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoadingIndicator() {
        _self.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                Toast.makeText(_self.activity, "Loading . . . ", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public static class BroadcastListener extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent){
            if (Instance == null || intent == null){
                return;
            }
            String action = intent.getAction();
            if (action.equals(MainActivity.INTERNET_STATUS)){
                Toast.makeText(_self.activity, "NO connection to internet!", Toast.LENGTH_LONG).show();
            }

        }
    }

}
