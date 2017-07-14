package com.lost.administrator.md.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lost.administrator.md.R;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_deal;
    private ImageView img_back;
    private EditText et_num;
    private Button btn_login;
    private CheckBox cb_deal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_deal= (TextView) findViewById(R.id.tv_deal_register);
        img_back= (ImageView) findViewById(R.id.iv_back_register);
        img_back.setOnClickListener(this);
        btn_login= (Button) findViewById(R.id.btn_getCode_register);
        tv_deal.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        cb_deal= (CheckBox) findViewById(R.id.cb_deal_register);
        et_num= (EditText) findViewById(R.id.et_phone_register);
        et_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = et_num.getText().toString();
                if (s!=null){
                    if (s.length()==11){
                        btn_login.setEnabled(true);
                    }else{
                        btn_login.setEnabled(false);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back_register:
                finish();
                break;
            case R.id.btn_getCode_register:
                if (cb_deal.isChecked()){

                }else{
                    Toast.makeText(this, "您还没有同意美团用户协议", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
