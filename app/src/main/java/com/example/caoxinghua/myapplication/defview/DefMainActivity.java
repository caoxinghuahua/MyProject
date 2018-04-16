package com.example.caoxinghua.myapplication.defview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caoxinghua.myapplication.R;

public class DefMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_def_main);
        MyView view= (MyView) findViewById(R.id.myView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DefMainActivity.this,"click",Toast.LENGTH_SHORT).show();
            }
        });
        FlowLayout flowLayout=(FlowLayout) findViewById(R.id.flowLayout);
        for (int i = 'A'; i < 'Z'; i++) {
            Button btn = new Button(this);
            btn.setHeight(dp2px(32));
            btn.setTextSize(16);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i - 'A' + 4; j++) {
                sb.append((char) i);
            }
            btn.setText(sb.toString());
            flowLayout.addView(btn);
        }
    }
    private int dp2px(float dp){
        return (int) (dp*getResources().getDisplayMetrics().density);

    }
}
