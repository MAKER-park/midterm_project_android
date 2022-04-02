package com.db_projce.midterm_project_220331;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class contorller extends AppCompatActivity {
    ImageButton switch_id = null;
    TextView text_id = null;
    static int nChecked = 0;
    Timer timer = new Timer();

    //참고 주소형 http://10.10.141.76:8080/android/updateStatus/ON&OFF 이렇게 on , off 가능 전체 데이터 다 바꿀수 있게 잡음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contorller);

        Intent intent = getIntent();
        String status = intent.getStringExtra("status");

        switch_id = findViewById(R.id.switch_id1);
        text_id  = findViewById(R.id.current_state_id);

        if(status.equals("ON")){
            switch_id.setImageResource(R.drawable.on);
            text_id.setText("ON");
        }
        else if(status.equals("OFF")){
            switch_id.setImageResource(R.drawable.off);
            text_id.setText("OFF");
        }

        switch_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println("change!!!");
                switch_id.setImageResource(R.drawable.off);
                System.out.println("change!!! current : " + status);
                //이미지 데이터랑 매칭 시켜야 한다.
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String page;
                        if(status.equals("OFF")) {
                            page = "http://10.10.141.76:8080/android/updateStatus/OFF&ON";
                        }
                        else{
                            page = "http://10.10.141.76:8080/android/updateStatus/ON&OFF";
                        }

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

                Intent intent = new Intent(contorller.this, MainActivity.class);
                //timer.cancel();//타이머 종료
                startActivity(intent); // 뷰 열기
            }
        });
    }



}
