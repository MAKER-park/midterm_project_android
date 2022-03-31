package com.db_projce.midterm_project_220331;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//https://angrytools.com/android/button/ 버튼 스타일 바꾸고 싶으면 여기 가셔서 설정 하고 스크립트 복사하기 복사 위치
public class MainActivity extends AppCompatActivity {

    Button button_id1 = null; //button의 ID를 받아올 변수 선언
    TextView text_id1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_id1 = findViewById(R.id.button);//버튼ID 전달
        text_id1 = findViewById(R.id.textView1);

        button_id1.setOnClickListener(new View.OnClickListener() { //Button이 눌릴떄 할거
            @Override
            public void onClick(View view) {
                System.out.println("testst");
                Intent intent = new Intent(MainActivity.this, contorller.class);
                startActivity(intent); // 뷰 열기
                //누르면 contorller_pannel로 이동
            }
        });
    }

}