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

import com.example.botond.helloworld2.db.AppDatabase;
import com.example.botond.helloworld2.db.Quote;
import com.example.botond.helloworld2.db.QuoteDao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ViewListActivity extends Activity {
    // Array of strings...
    /*String[] mobileArray = {"Don’t regret the past, just learn from it.",
                            "Life is trying things to see if they work.",
                            "It does not matter how slowly you go as long as you do not stop.",
                            "Wherever you go, go with all your heart.",
                            "Everything you can imagine is real.",
                            "Don’t wait. The time will never be just right.",
                            "Men are born to succeed, not fail.",
                            "Whatever you are, be a good one."};*/
    List<Quote> quoteList;
    QuoteDao quoteDao;
    ListView listView;

    Quote selectedQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        QuoteDao quoteDao= AppDatabase.getAppDatabase(this).quoteDao();

        //DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));
        quoteList=quoteDao.getAll();

        ArrayAdapter adapter = new ArrayAdapter<Quote>(this,
                R.layout.activity_listview, quoteList);

        listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView. setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                selectedQuote=quoteList.get(position);

                Intent intent = new Intent(ViewListActivity.this, CrudActivity.class);

                intent.putExtra("quote", selectedQuote.getQuote());
                intent.putExtra("author", selectedQuote.getAuthor());
                intent.putExtra("id", selectedQuote.getId());
                startActivity(intent);
                finish();
            }
        });

    }

    private void editList(){
        Intent intent = getIntent();
        String quote = intent.getStringExtra("quote");
        int listNr = intent.getIntExtra("id",0);

        //if(quote!=null && listNr>=0&&listNr<quoteList.size())
            //quoteList.set(listNr,quote);

    }
}