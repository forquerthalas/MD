package com.lost.administrator.md.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lost.administrator.md.R;

public class EnterBrandActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_brand);
        iv_back= (ImageView) findViewById(R.id.iv_back_enter);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_enter:
                finish();
                break;
        }
    }
}
