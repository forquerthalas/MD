package com.lost.administrator.md.activity;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wx.ovalimageview.RoundImageView;
import com.lost.administrator.md.R;


public class LoginActivity extends AppCompatActivity implements  RadioGroup.OnCheckedChangeListener, View.OnClickListener {


    private RadioGroup radioGroup;
    //手机号登陆布局  账号密码登陆布局 第三方陆点击响应布局 第三方登陆总布局
    private LinearLayout ll_login1,ll_login2,ll_diSanFang,ll_bottom;
    private ImageView iv_back;
    //判断第三方布局是显示全部还是只显示头部变量
    private boolean ifShow=true;
    //立即注册 TextView  找回密码TextView
    private TextView tv_register,tv_find_psw;
    //手机号输入框 验证码输入框 密码，账号输入框
    private EditText et_phone,et_checkCode,et_name,et_psw;
    //删除的ImageView 共4个
    private RoundImageView iv_del1,iv_del2,iv_del3,iv_del4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }
    private void initEvent() {
        iv_back.setOnClickListener(this);
        tv_find_psw.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        ll_diSanFang.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        iv_del1.setOnClickListener(this);
        iv_del2.setOnClickListener(this);
        iv_del3.setOnClickListener(this);
        iv_del4.setOnClickListener(this);
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = et_phone.getText().toString();
                if (s!=null&&!s.equals("")){
                    iv_del1.setVisibility(View.VISIBLE);
                }else{
                    iv_del1.setVisibility(View.GONE);
                }
            }
        });
        et_checkCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = et_checkCode.getText().toString();
                if (s!=null&&!s.equals("")){
                    iv_del2.setVisibility(View.VISIBLE);
                }else{
                    iv_del2.setVisibility(View.GONE);
                }
            }
        });
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = et_name.getText().toString();
                if (s!=null&&!s.equals("")){
                    iv_del3.setVisibility(View.VISIBLE);
                }else{
                    iv_del3.setVisibility(View.GONE);
                }
            }
        });
        et_psw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = et_psw.getText().toString();
                if (s!=null&&!s.equals("")){
                    iv_del4.setVisibility(View.VISIBLE);
                }else{
                    iv_del4.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initView() {
        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.check(R.id.rb_login1);
        ll_login1= (LinearLayout) findViewById(R.id.login_phone);
        ll_login2= (LinearLayout) findViewById(R.id.login_psw);
        ll_diSanFang= (LinearLayout) findViewById(R.id.ll_diSanFang);
        ll_bottom= (LinearLayout) findViewById(R.id.ll_bottom);
        iv_back= (ImageView) findViewById(R.id.iv_back_login);
        tv_register= (TextView) findViewById(R.id.tv_register);
        tv_find_psw= (TextView) findViewById(R.id.tv_find_psw);
        et_phone= (EditText) findViewById(R.id.et_phone_register);
        et_checkCode= (EditText) findViewById(R.id.et_checkCode_register);
        et_name= (EditText) findViewById(R.id.et_name_register);
        et_psw= (EditText) findViewById(R.id.et_psw_register);

        iv_del1= (RoundImageView) findViewById(R.id.iv_del1_register);
        iv_del2= (RoundImageView) findViewById(R.id.iv_del2_register);
        iv_del3= (RoundImageView) findViewById(R.id.iv_del3_register);
        iv_del4= (RoundImageView) findViewById(R.id.iv_del4_register);

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb_login1:
                ll_login2.setVisibility(View.GONE);
                ll_login1.setVisibility(View.VISIBLE);
                tv_find_psw.setVisibility(View.GONE);
                break;
            case R.id.rb_login2:
                ll_login1.setVisibility(View.GONE);
                ll_login2.setVisibility(View.VISIBLE);
                tv_find_psw.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_login:
                finish();
                break;
            case R.id.ll_diSanFang:
                ifShow=!ifShow;
                if (!ifShow) {

                    ObjectAnimator animator= ObjectAnimator.ofFloat(ll_bottom,"translationY",0,-200);
                    animator.setDuration(500);
                    animator.start();
                }
                else {
                    ObjectAnimator animator= ObjectAnimator.ofFloat(ll_bottom,"translationY",-200,0);
                    animator.setDuration(500);
                    animator.start();
                }

                break;
            case R.id.tv_register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_find_psw:
                Intent intent1 = new Intent(this, FindActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_del1_register:
                et_phone.setText("");
                break;
            case R.id.iv_del2_register:
                et_checkCode.setText("");
                break;
            case R.id.iv_del3_register:
                et_name.setText("");
                break;
            case R.id.iv_del4_register:
                et_psw.setText("");
                break;
        }
    }

}
