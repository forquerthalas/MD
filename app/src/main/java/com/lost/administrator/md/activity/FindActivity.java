package com.lost.administrator.md.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lost.administrator.md.R;


public class FindActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back;
    //下一步 换一张按钮
    private Button btn_next,btn_change;
    //用户输入框  验证码输入框
    private EditText et_name,et_check;
    //错误信息提示TextView  无法接受短息TextView 及所在父布局
    private TextView tv_errorInfo,tv_falidMsg;
    private LinearLayout ll_error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        initView();
        initEvent();

    }

    private void initEvent() {
        iv_back.setOnClickListener(this);

    }

    private void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_back_find);
        btn_next= (Button) findViewById(R.id.btn_next_find);
        btn_change= (Button) findViewById(R.id.btn_change_find);
        et_name= (EditText) findViewById(R.id.et_name_find);
        et_check= (EditText) findViewById(R.id.et_check_find);
        tv_errorInfo= (TextView) findViewById(R.id.tv_errorInfo_find);
        tv_falidMsg= (TextView) findViewById(R.id.tv_failed_find);
        ll_error= (LinearLayout) findViewById(R.id.ll_error_find);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_find:
                finish();
                break;
        }
    }
}
