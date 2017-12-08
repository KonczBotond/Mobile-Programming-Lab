package com.example.botond.helloworld2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.botond.helloworld2.db.AppDatabase;
import com.example.botond.helloworld2.db.Quote;
import com.example.botond.helloworld2.db.QuoteDao;

public class AddQuoteActivity extends AppCompatActivity {

    final Context context=this;

    EditText quoteEditText, authorEditText;
    QuoteDao quoteDao;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        quoteEditText=(EditText)findViewById(R.id.EditTextQuote);
        authorEditText=(EditText)findViewById(R.id.EditTextAuthor);
        addButton = (Button) findViewById(R.id.buttonAddQuote);

        quoteDao= AppDatabase.getAppDatabase(this).quoteDao();

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                addQuote();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Delete");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Quote added!")
                        .setCancelable(false)
                        ;

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

    public void addQuote() {
        Quote quote=new Quote();
        quote.setQuote(quoteEditText.getText().toString());
        quote.setAuthor(authorEditText.getText().toString());
        quoteDao.insertQuotes(quote);
        onBackPressed();
    }
}
