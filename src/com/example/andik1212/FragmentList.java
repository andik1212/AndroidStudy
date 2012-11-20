package com.example.andik1212;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.andik1212.helper.Article;
import com.example.andik1212.helper.ArticleCollection;
import com.example.andik1212.helper.GetNews;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FragmentList extends Fragment {

    String[] values = new String[0]; //{ "new1", "new2", "new3", "new4", "new5", "new6"  };
    GetNews loader;
    //временно
//        String[] content = new String[0];


    private static class Self {
        public View view;
        public boolean restore;
        public FragmentActivity activity;
        public ArticleCollection articles;
    }

    Self _self = new Self();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _self.activity = getActivity();
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
        showLoadingIndicator();
        loader = new GetNews();
        loader.run(_self.articles);
        while(!GetNews.finished){}
//        for (int i = 0; i <= _self.articles.size(); i++){
//            Article art = (Article) _self.articles.get(i);
//            values[i]= art.getTitle();
//        }
        hideLoadingIndicator();



//        Toast.makeText(_self.activity, "val "+values.length, Toast.LENGTH_LONG).show();




                ListView list = (ListView) view.findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                android.R.id.text1, values);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String text = (String) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), ActivityDetail.class);
                intent.putExtra(FragmentDetail.EXTRA_TEXT, text);
                startActivity(intent);
            }
        });
    }




    private void showLoadingError() {
        _self.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                Toast.makeText(_self.activity, "Something goes wrong during parsing ;(", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateUi() {
        _self.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    private void hideLoadingIndicator() {
        _self.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                Toast.makeText(_self.activity, "Finished ", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showLoadingIndicator() {
        _self.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
                Toast.makeText(_self.activity, "Loading . . . ", Toast.LENGTH_LONG).show();
            }
        });

    }

}
