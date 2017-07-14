package com.lost.administrator.md.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.User;

public class YuErActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    private TextView tv_balanceInfo;
    private TextView tv_balance;
    private Button btn_giveBack;
    //用户对象
    private User user=new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_er);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_back_balance);
        tv_balanceInfo= (TextView) findViewById(R.id.tv_balanceInfo);
        tv_balance= (TextView) findViewById(R.id.tv_balance);
        btn_giveBack= (Button) findViewById(R.id.btn_giveBack);
    }

    private void initEvent() {
        iv_back.setOnClickListener(this);
        tv_balanceInfo.setOnClickListener(this);
        btn_giveBack.setOnClickListener(this);
    }

    private void initData() {
        tv_balance.setText("￥"+user.getBanlance());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_balance:
                finish();
                break;
            //余额明细
            case R.id.tv_balanceInfo:

                break;
            //原路退回
            case R.id.btn_giveBack:

                break;
        }
    }
}
