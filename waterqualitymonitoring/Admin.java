package com.example.dell.waterqualitymonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by DELL on 15-03-2017.
 */

public class Admin extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        final EditText username=(EditText)findViewById(R.id.editText3);
        final EditText password=(EditText)findViewById(R.id.editText4);
        Button add_user=(Button)findViewById(R.id.button5);


        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un=username.getText().toString();
                String pw=password.getText().toString();
               // MainActivity ma=new MainActivity();
               // ma.set(un,pw);


                Intent i1=new Intent(getApplicationContext(),MainActivity.class);

                i1.putExtra("uname",un);
                i1.putExtra("pword",pw);
                startActivity(i1);


                Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_LONG).show();

//                Intent i=new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(i);

            }
        });

    }

}
