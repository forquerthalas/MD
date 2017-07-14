package com.lost.administrator.md.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lost.administrator.md.R;

public class RedBagActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_toRule;
    private ImageView iv_back;
    private TextView tv_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_bag);
        initView();
        initEvent();
    }

    private void initView() {
        ll_toRule= (LinearLayout) findViewById(R.id.ll_toRule);
        iv_back= (ImageView) findViewById(R.id.iv_back_red);
        tv_add= (TextView) findViewById(R.id.tv_add_red);
    }


    private void initEvent() {
        ll_toRule.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_toRule:
                startActivity(new Intent(this,RuleActivity.class));
                break;
            case R.id.iv_back_red:
                finish();
                break;
            case R.id.tv_add_red:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("添加优惠券")
                        .setView(new EditText(this))
                        .setPositiveButton("确定",null)
                        .setNegativeButton("取消",null)
                        .setMessage("请输入优惠券密码")
                        .show();
                break;
        }
    }
}
