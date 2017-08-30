package com.example.dell.waterqualitymonitoring;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DELL on 02-03-2017.
 */

public class Authority extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private static String url = "https://api.thingspeak.com/channels/215222/feeds.json";

    ArrayList<HashMap<String, String>> readingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authority);

        readingList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list_authority);

        Button mail_authority = (Button) findViewById(R.id.button_mail_authority);
        //sendsms=(Button)findViewById(R.id.button_mail_authority);

        mail_authority.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("SendMailActivity", "Send Button Clicked.");
                String fromEmail = "siddhitheonly1@gmail.com";
                String fromPassword = "ganapatibappa";
                String toEmails = "siddhitheonly1@gmail.com";
                List toEmailList = Arrays.asList(toEmails.split("\\s*,\\s*"));
                Log.i("SendMailActivity", "To List: " + toEmails);
                String emailSubject = "Work to do";
                String emailBody = "Readings are out of bounds.Stop distribution of water. Restore the normal levels.Revert back ASAP.";
                new SendMailTask(Authority.this).execute(fromEmail,fromPassword, toEmails, emailSubject, emailBody);
            }


            });








        new GetReadings().execute();
    }


    private class GetReadings extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Authority.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Void doInBackground(Void... arg0)
        {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if(jsonStr!=null)
            {
                try
                {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray readings = jsonObj.getJSONArray("feeds");

                    for(int i=0;i<readings.length();i++)
                    {
                        JSONObject r=readings.getJSONObject(i);
                        String created=r.getString("created_at");
                        String temp=r.getString("field1");
                        String ph=r.getString("field2");
                        String turbidity=r.getString("field3");

                        HashMap<String,String> reading=new HashMap<>();

                        reading.put("Created",created);
                        reading.put("temp",temp);
                        reading.put("ph",ph);
                        reading.put("turbidity",turbidity);

                        readingList.add(reading);
                    }

                }
                catch (final JSONException e)
                {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

            }
            else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                   @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Authority.this, readingList,
                    R.layout.list_item, new String[]{"Created", "temp","ph","turbidity"}, new int[]{R.id.created,
                    R.id.temp,R.id.ph,R.id.turbidity});

            lv.setAdapter(adapter);
        }


    }

}
