package com.db_projce.midterm_project_220331;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;

public class contorller extends AppCompatActivity {
    //https://androidrepo.com/repo/Angads25-android-toggle-android-android-ui-library
    LabeledSwitch label_id1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contorller);

        label_id1 = findViewById(R.id.switch_id1);
        label_id1.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                //버튼을 눌렀을때

            }
        });
    }

}
