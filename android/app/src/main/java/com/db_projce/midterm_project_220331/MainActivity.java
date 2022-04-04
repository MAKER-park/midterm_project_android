package com.db_projce.midterm_project_220331;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

//https://angrytools.com/android/button/ 버튼 스타일 바꾸고 싶으면 여기 가셔서 설정 하고 스크립트 복사하기 복사 위치
public class MainActivity extends AppCompatActivity {

    Button button_id1 = null; //button의 ID를 받아올 변수 선언
    ImageView image_id1 = null;
    TextView text_id1 = null;
    TextView text_id2 = null;
    ImageButton image_id2 = null;

    static  int temp = 0, index = 0; //db 온도값 , index 선택인자
    static String status = null;
    //타이머 자식 쓰레드 생성 -- db 구문 업데이트 기능 쓰레드
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_id1 = findViewById(R.id.button);//버튼ID 전달
        text_id1 = findViewById(R.id.textView1);
        text_id2 = findViewById(R.id.textView2);
        image_id1 = findViewById(R.id.imageView);
        image_id2 = findViewById(R.id.image_Button);


        Handler text_changer = new Handler() {
            public void handleMessage(Message msg) {

                if (status.equals("ON")) {
                    image_id1.setImageResource(R.drawable.on);
                    text_id2.setText("ON");
                    text_id1.setText(String.valueOf(temp) + " ℃"); //this is the textview

                }else if(status.equals("OFF")){
                    image_id1.setImageResource(R.drawable.off);
                    text_id2.setText("OFF");
                    text_id1.setText(" "); //this is the textview

                }
            }
        };

        button_id1.setOnClickListener(new View.OnClickListener() { //Button이 눌릴떄 할거
            @Override
            public void onClick(View view) {
                //System.out.println("testst");
                Intent intent = new Intent(MainActivity.this, contorller.class);
                intent.putExtra("status",status);
                //timer.cancel();//타이머 종료
                startActivity(intent); // 뷰 열기
                //누르면 contorller_pannel로 이동
            }
        });

        image_id2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click!!");
                Intent intent2 = new Intent(MainActivity.this,loginAcitvity.class);
                startActivity(intent2);
            }
        });


        //타이머 자식 쓰레드 생성 -- db 구문 업데이트 기능 쓰레드
        Timer timer = new Timer();
        timer = new Timer();

        TimerTask TT = new TimerTask() {
            @Override
            public void run() {
                // 반복실행할 구문

                //시간데이터를 안드로이드 기기로 부터 받아옵니다.
                Date currentTime = Calendar.getInstance().getTime();
                //시연용으로 10초를 기준을 잡고 DB 시간으로 잡고 돌립니다. -> 시간 변화에 따른 온도 값 출력을 위한 설계 입니다.
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:MM:SS"); //05:04:04 시분초로 출력 되며 이를 각각 따로 나눠 받을 예정! 이거 yyyyMMdd 이런식으로 하면 년월일 단위도 됩니당
                String date = dateFormat.format(currentTime.getTime());//우선 문자열 값으로 데이터를 받아 옵니다.
                //System.out.println("time!!!!!!!!!!!!!!!!!!! : " +  date);//임시 확인

                //---- web requst line-----------//
                List<Request_db> data;

                String page = "http://10.10.141.76:8080/android/boilerList";

                try {
                    URL url = new URL(page);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    StringBuilder stringBuilder = new StringBuilder();

                    if(conn != null){

                        conn.setConnectTimeout(10000);
                        conn.setRequestMethod("GET");
                        conn.setUseCaches(false);
                        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){

                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                            while(true){
                                String line = bufferedReader.readLine();
                                if(line == null) break;
                                stringBuilder.append(line + "\n");
                            }
                            bufferedReader.close();
                        }
                        conn.disconnect();
                    }

                    System.out.println("서버에서 받아온 JSON 타입의 String : " + "\n" + String.valueOf(stringBuilder));

                    Gson gson = new Gson();

                    Type type = new TypeToken<List<Request_db>>() {}.getType();
                    data = gson.fromJson(String.valueOf(stringBuilder),type);


                    //원하는 행의 데이터를 받아오는 곳 시간과 합쳐서 사용합니다.
                    String time = data.get(index).getUserid();
                    status = data.get(index).getAge();
                    temp = data.get(index).getUsername();
                    //System.out.println("temp! : " + String.valueOf(temp));
                    text_changer.obtainMessage(1).sendToTarget(); //ui 변경 핸들러 호출
                    index++;
                    if(index == 23){
                        index = 0;
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        };//DB 업데이트 타이머 구문의 끝
        timer.schedule(TT, 0, 1000); //Timer 실행
        //timer.cancel();//타이머 종료
    }
}