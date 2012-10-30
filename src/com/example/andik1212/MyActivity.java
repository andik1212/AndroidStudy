package com.example.andik1212;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    Fragment1 frag1;
    android.app.FragmentTransaction fTrans;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // находим список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);

//        fTrans = getFragmentManager().beginTransaction();
//        frag1 = new Fragment1();
//        fTrans.add(R.id.frgmCont, frag1);
//        fTrans.addToBackStack(null);
//        fTrans.commit();


    }
}
