package com.example.dell.waterqualitymonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    public HashMap<String,String> user_pw=new HashMap<String,String>();

    public void set(String a,String b)
    {
        String usna=a;
        String pwod=b;

        user_pw.put(usna,pwod);

    }
//    TextView username=(TextView)findViewById(R.id.textView2);
//    TextView password=(TextView)findViewById(R.id.textView3);
//
//    EditText uname=(EditText)findViewById(R.id.editText);
//    EditText pword=(EditText)findViewById(R.id.editText2);
//
//    Button button1=(Button)findViewById(R.id.button);
//public final HashMap<String,String> user_pw=new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView username=(TextView)findViewById(R.id.textView2);
        TextView password=(TextView)findViewById(R.id.textView3);

       final EditText uname=(EditText)findViewById(R.id.editText);
        final EditText pword=(EditText)findViewById(R.id.editText2);

        Intent intent=getIntent();

        String un=intent.getStringExtra("uname");
        String pw=intent.getStringExtra("pword");

       //final HashMap<String,String> user_pw=new HashMap<String,String>();

        user_pw.put("JE","Je123");
        user_pw.put("Admin","Admin123");
        user_pw.put("Worker","Worker123");
        user_pw.put(un,pw);

        final Button button1=(Button)findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Admin hmap=new Admin();
               // HashMap<String,String> user_pw=hmap.user_pw;
//                if(uname.getText().toString().equals("Admin") && pword.getText().toString().equals("Admin123"))
//                {
//                    Intent intent1=new Intent(getApplicationContext(),Authority.class);
//                    startActivity(intent1);
//                }
//
//                else if(uname.getText().toString().equals("Worker") && pword.getText().toString().equals("Worker123"))
//                {
//                    Intent intent2=new Intent(getApplicationContext(),Worker.class);
//                    startActivity(intent2);
//                }

//                if (user_pw.containsKey(uname.getText().toString()) && user_pw.containsValue(pword.getText().toString()))
//                {
//
//                    Intent intent1=new Intent(getApplicationContext(),Authority.class);
//                    startActivity(intent1);
//                }
//
//                else if (user_pw.containsKey(uname.getText().toString().equals("Worker")) && user_pw.containsValue(pword.getText().toString()))
//                {
//                    Intent intent2=new Intent(getApplicationContext(),Worker.class);
//                    startActivity(intent2);
//                }
//
//                else if(user_pw.containsKey(uname.getText().toString().equals("Admin")) && user_pw.containsValue(pword.getText().toString()))
//                {
//                    Intent intent3=new Intent(getApplicationContext(),Admin.class);
//                    startActivity(intent3);
//                }

             //   String usna;
                if(uname.getText().toString().equals("Admin") && pword.getText().toString().equals("Admin123"))
                {
                    Intent intent2 = new Intent(getApplicationContext(), Admin.class);
                    startActivity(intent2);
                }
                if(user_pw.containsKey(uname.getText().toString())){

                    String storedpassword=user_pw.get(uname.getText().toString());
                    if(storedpassword.equals(pword.getText().toString())) {

                            if(uname.getText().toString().contains("JE")) {

                                Intent intent1 = new Intent(getApplicationContext(), Authority.class);
                                startActivity(intent1);
                            }

//                             if (uname.getText().toString().equals("Admin"))
//                            {
//                                Intent intent2 = new Intent(getApplicationContext(), Admin.class);
//                                startActivity(intent2);
//                            }
                             if (uname.getText().toString().contains("Worker"))
                            {
                                Intent intent3 = new Intent(getApplicationContext(), Worker.class);
                                startActivity(intent3);
                            }
                        }
                    }

                else {
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
                }
            }
        });


    }





    }

