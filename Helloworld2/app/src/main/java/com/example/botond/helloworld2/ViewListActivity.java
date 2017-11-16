package com.example.botond.helloworld2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ViewListActivity extends Activity {
    // Array of strings...
    String[] mobileArray = {"Don’t regret the past, just learn from it.",
                            "Life is trying things to see if they work.",
                            "It does not matter how slowly you go as long as you do not stop.",
                            "Wherever you go, go with all your heart.",
                            "Everything you can imagine is real.",
                            "Don’t wait. The time will never be just right.",
                            "Men are born to succeed, not fail.",
                            "Whatever you are, be a good one."};
    List<String> quoteList= Arrays.asList(mobileArray);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        editList();
        //quoteList.set(2,"mu");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, quoteList);

        final ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView. setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ViewListActivity.this, EditQuoteActivity.class);
                //String quotetext = listView.getSelectedItem().toString();
                String selectedFromList =(String) (listView.getItemAtPosition(position));;

                intent.putExtra("quote", selectedFromList);
                intent.putExtra("listNr", position);
                startActivity(intent);
            }
        });

    }

    private void editList(){

        Intent intent = getIntent();
        String quote = intent.getStringExtra("quote");
        int listNr = intent.getIntExtra("listNr",0);

        if(quote!=null && listNr>=0&&listNr<quoteList.size())
            quoteList.set(listNr,quote);

    }
}