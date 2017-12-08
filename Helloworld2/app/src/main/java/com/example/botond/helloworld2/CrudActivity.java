package com.example.botond.helloworld2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.botond.helloworld2.db.Quote;
import com.example.botond.helloworld2.db.QuoteDao;
import com.example.botond.helloworld2.db.AppDatabase;

import java.util.List;

public class CrudActivity extends AppCompatActivity {

    final Context context=this;

    EditText quoteEditText, authorEditText;
    QuoteDao quoteDao;
    Quote selectedQuote;
    Button deleteButton,updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        Intent intent = getIntent();
        createQuote(intent);

        quoteEditText=(EditText)findViewById(R.id.EditTextQuote);
        authorEditText=(EditText)findViewById(R.id.EditTextAuthor);

        quoteEditText.setText(selectedQuote.getQuote());
        authorEditText.setText(selectedQuote.getAuthor());

        quoteDao= AppDatabase.getAppDatabase(this).quoteDao();
        //quoteDao.deleteAll();

        deleteButton = (Button) findViewById(R.id.buttonDeleteQuote);
        updateButton = (Button) findViewById(R.id.buttonUpdateQuote);

        // add button listener
        setButtonListeners();
    }

    private void selectQuote(Quote quote){
        selectedQuote=quote;
        quoteEditText.setText(quote.getQuote());
        authorEditText.setText(quote.getAuthor());
    }

    public void listQuotes(View view) {
        Intent intent = new Intent(this, ViewListActivity.class);

        startActivity(intent);
    }

    public void deleteQuote() {
        quoteDao.deleteQuotes(selectedQuote);
        onBackPressed();
    }
    public void updateQuote() {
        String quote=quoteEditText.getText().toString();
        String author=authorEditText.getText().toString();
        selectedQuote.setQuote(quote);
        selectedQuote.setAuthor(author);

        quoteDao.updateQuotes(selectedQuote);

        onBackPressed();
    }

    private void createQuote(Intent intent){
        String quote = intent.getStringExtra("quote");
        String author = intent.getStringExtra("author");
        int id = intent.getIntExtra("id",0);
        selectedQuote=new Quote();
        selectedQuote.setQuote(quote);
        selectedQuote.setAuthor(author);
        selectedQuote.setId(id);
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, ViewListActivity.class);
        startActivity(setIntent);
        finish();
    }

    private void setButtonListeners(){
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Delete");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to update?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                deleteQuote();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Delete");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to delete?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                updateQuote();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

}
