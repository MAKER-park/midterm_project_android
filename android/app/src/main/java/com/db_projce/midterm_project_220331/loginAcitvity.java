package com.db_projce.midterm_project_220331;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginAcitvity extends AppCompatActivity{

    Button bt_id1 = null;
    EditText ed_id1 = null;

    static String PW = "1234";
    String test = " ";

    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

        bt_id1= findViewById(R.id.button_id3);
        ed_id1 = findViewById(R.id.edit_id1);


        bt_id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test=ed_id1.getText().toString();
                if(test.equals(PW)) {
                    System.out.println("로그인 성공");
                    Toast.makeText(loginAcitvity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(loginAcitvity.this,db_update_activity.class);
                    startActivity(intent1);

                }else{
                    System.out.println("로그인 실패");
                    AlertDialog.Builder builder = new AlertDialog.Builder(loginAcitvity.this);
                    builder.setTitle("로그인 에러").setMessage("비밀번호가 일치 하지 않습니다.\n 다시 확인 해주세요.");

                    AlertDialog alertDialog = builder.create();

                    alertDialog.show();

                }
            }
        });



    }
}