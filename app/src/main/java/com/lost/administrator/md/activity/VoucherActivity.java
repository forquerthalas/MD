package com.lost.administrator.md.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lost.administrator.md.R;

public class VoucherActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private LinearLayout ll_rule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        initView();
        initEvent();
    }

    private void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_back_voucher);
        ll_rule= (LinearLayout) findViewById(R.id.ll_rule_voucher);
    }


    private void initEvent() {
        iv_back.setOnClickListener(this);
        ll_rule.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_voucher:
                finish();
                break;
            case R.id.ll_rule_voucher:
                startActivity(new Intent(this,RuleActivity.class));
                break;
        }
    }

}
