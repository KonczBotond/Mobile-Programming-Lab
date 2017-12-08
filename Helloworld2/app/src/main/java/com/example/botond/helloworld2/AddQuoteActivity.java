package com.example.botond.helloworld2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.botond.helloworld2.db.AppDatabase;
import com.example.botond.helloworld2.db.Quote;
import com.example.botond.helloworld2.db.QuoteDao;

import java.util.Calendar;

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

        setUpPicker();
    }

    public void setUpPicker() {
        final Calendar myCalendar = Calendar.getInstance();

        EditText edittext = (EditText) findViewById(R.id.EditTextBirthday);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //updateLabel();
            }
        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddQuoteActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
