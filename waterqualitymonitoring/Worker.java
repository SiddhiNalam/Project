package com.example.dell.waterqualitymonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by DELL on 12-03-2017.
 */

public class Worker extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker);

        Button restore_temp=(Button)findViewById(R.id.button2);
        Button restore_ph=(Button)findViewById(R.id.button3);
        Button restore_turbidity=(Button)findViewById(R.id.button4);

        Button expected_readings=(Button)findViewById(R.id.button6);
        Button mail_worker=(Button)findViewById(R.id.button7);

        restore_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(getApplicationContext(),restore_temp.class);
                startActivity(intent3);

            }
        });

        restore_ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(getApplicationContext(),restore_ph.class);
                startActivity(intent4);

            }
        });

        restore_turbidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(getApplicationContext(),restore_turbidity.class);
                startActivity(intent5);

            }
        });

        expected_readings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6=new Intent(getApplicationContext(),expected_readings.class);
                startActivity(intent6);

            }
        });


        mail_worker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("SendMailActivity", "Send Button Clicked.");
                String fromEmail = "siddhitheonly1@gmail.com";
                String fromPassword = "ganapatibappa";
                String toEmails = "siddhitheonly1@gmail.com";
//                List toEmailList = Arrays.asList(toEmails
//                        .split("\\s*,\\s*"));
                Log.i("SendMailActivity", "To List: " + toEmails);
                String emailSubject = "Work Done";
                String emailBody = "Readings are in normal range.Starting water distribution.";
                new SendMailTask(Worker.this).execute(fromEmail,
                        fromPassword, toEmails, emailSubject, emailBody);
            }
        });

    }
}
