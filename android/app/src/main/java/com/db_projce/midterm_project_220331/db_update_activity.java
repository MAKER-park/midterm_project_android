package com.db_projce.midterm_project_220331;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class db_update_activity extends AppCompatActivity {

    Spinner sp_id1 = null;
    EditText ed_id4 = null;
    Button bt_id2 = null;
    TextView text_id4 =null;

    static String time = null;
    static String temp = null;
    static String page;


    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.db_updater);

        sp_id1 = findViewById(R.id.spin_id);
        ed_id4 = findViewById(R.id.edit_id2);
        bt_id2 = findViewById(R.id.button_id4);
        text_id4 = findViewById(R.id.text_id3);

        sp_id1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> time_set, View view, int postion, long id) {
                time = time_set.getItemAtPosition(postion).toString();
                text_id4.setText(time_set.getItemAtPosition(postion).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bt_id2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = ed_id4.getText().toString();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ///android/updatetemp/00:00:00&40
                        page = "http://10.10.141.76:8080/android/updatetemp/"+ time +"&"+ temp;

                        try {
                            URL url = new URL(page);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            StringBuilder stringBuilder = new StringBuilder();

                            if(conn != null){
                                conn.setConnectTimeout(10000);
                                conn.setRequestMethod("GET");
                                conn.setUseCaches(false);
                                new InputStreamReader(conn.getInputStream(), "UTF-8");

                                conn.disconnect();

                            }

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } //End of run()
                }); //End of Thread
                thread.start();
                System.out.println("time_set : " + time);
                Intent intent = new Intent(db_update_activity.this, MainActivity.class);
                startActivity(intent); // 뷰 열기
            }
        });
    }
}
