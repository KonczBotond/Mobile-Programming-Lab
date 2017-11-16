package com.example.botond.helloworld2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditQuoteActivity extends AppCompatActivity {

    private int listNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quote);

        Intent intent = getIntent();
        String quote = intent.getStringExtra("quote");
        listNr = intent.getIntExtra("listNr",0);

        final EditText edit =  (EditText) findViewById(R.id.EditTextQuote);
        edit.setText(quote);
    }

    public void editQuote(View view){
        Intent intent = new Intent(EditQuoteActivity.this, ViewListActivity.class);

        final EditText edit =  (EditText) findViewById(R.id.EditTextQuote);
        String edittext = edit.getText().toString();

        intent.putExtra("quote", edittext);
        intent.putExtra("listNr", listNr);
        startActivity(intent);
    }
}
