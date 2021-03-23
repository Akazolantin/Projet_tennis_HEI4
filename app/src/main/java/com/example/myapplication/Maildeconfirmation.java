package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Maildeconfirmation extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maildeconfirmation);
        textView = findViewById(R.id.text_view);
        long duration = TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration, 1000) {

            @Override
            public void onTick(long l) {
                String sduration=String.format(Locale.FRANCE,"%02d:%02d",TimeUnit.MILLISECONDS.toMinutes(l),TimeUnit.MILLISECONDS.toSeconds(l)-
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                Toast.makeText(getApplicationContext(),"Message en cours d'envoie",Toast.LENGTH_LONG).show();
                textView.setText(sduration);
            }

            @Override
            public void onFinish() {
                textView.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Message envoyé",Toast.LENGTH_LONG).show();

            }
        }.start();
    }
}
