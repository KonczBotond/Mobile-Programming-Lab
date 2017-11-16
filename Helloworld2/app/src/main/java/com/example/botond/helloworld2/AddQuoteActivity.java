package com.example.botond.helloworld2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuoteActivity extends AppCompatActivity {

    private static  final String TAG="mymsg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);
    }

    public void sendEmail(View view){
        Log.i("Send email", "");
        String[] TO = {"konczbotondvagyok@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Quote");

        final EditText edit =  (EditText) findViewById(R.id.EditTextQuote);
        String emailtext = edit.getText().toString();
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailtext);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i(TAG, "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AddQuoteActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
