package com.lost.administrator.md.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lost.administrator.md.R;
import com.lost.administrator.md.widget.MyRadioButton;


public class AddAddressActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, View.OnFocusChangeListener {

    private RadioGroup radioGroup;
    private ImageView iv_del1,iv_del2,iv_del3,iv_sure,iv_back;
    private EditText et_name,et_phone,et_address;
    private MyRadioButton rb1,rb2;
    private LinearLayout ll_address;
    private static final int REQUESTCODE=0x0011;
    private TextView tv_address;
    private String name,sex,phone,street,door;
    private int position=-1;
    private Boolean ok=false;
    private boolean editMode=false;
    private boolean addMode=false;
    private boolean commMode=false;
    private String TAG="AddAddressActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        Intent intent = getIntent();
        initView();
        initEvent();
        if (intent!=null){
            if (intent.getBooleanExtra("add",false)){
                addMode=true;
            }else if(intent.getBooleanExtra("comm",false)) {
                commMode=true;
            }else{
                //编辑模式
                editMode=true;
                name=intent.getStringExtra("name");
                street=intent.getStringExtra("street");
                door=intent.getStringExtra("door");
                sex=intent.getStringExtra("sex");
                phone=intent.getStringExtra("tel");
                position=intent.getIntExtra("position",-1);
                et_name.setText(name);
                et_phone.setText(phone);
                et_address.setText(door);
                tv_address.setText(street);
                if (sex.equals("先生")){
                    radioGroup.check(R.id.rb1);
                }else{
                    radioGroup.check(R.id.rb2);
                }
            }
        }
    }

    private void initView() {
        radioGroup= (RadioGroup) findViewById(R.id.rg_address);
        iv_del1= (ImageView) findViewById(R.id.iv_del1);
        iv_del2= (ImageView) findViewById(R.id.iv_del2);
        iv_del3= (ImageView) findViewById(R.id.iv_del3);
        et_name= (EditText) findViewById(R.id.et_name);
        et_phone= (EditText) findViewById(R.id.et_phone);
        et_address= (EditText) findViewById(R.id.et_address);
        rb1= (MyRadioButton) findViewById(R.id.rb1);
        rb2= (MyRadioButton) findViewById(R.id.rb2);
        ll_address= (LinearLayout) findViewById(R.id.ll_address);
        tv_address= (TextView) findViewById(R.id.tv_address);
        iv_sure= (ImageView) findViewById(R.id.iv_sure);
        iv_back= (ImageView) findViewById(R.id.iv_back_add);
    }

    private void initEvent() {
        radioGroup.setOnCheckedChangeListener(this);
        ll_address.setOnClickListener(this);
        iv_sure.setOnClickListener(this);
        et_name.setOnFocusChangeListener(this);
        et_address.setOnFocusChangeListener(this);
        et_phone.setOnFocusChangeListener(this);
        iv_del1.setOnClickListener(this);
        iv_del2.setOnClickListener(this);
        iv_del3.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_name.getText()!=null&&!et_name.getText().toString().equals("")){
                    iv_del1.setVisibility(View.VISIBLE);
                }else{
                    iv_del1.setVisibility(View.GONE);
                }
            }
        });
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_phone.getText()!=null&&!et_phone.getText().toString().equals("")){
                    iv_del2.setVisibility(View.VISIBLE);
                }else{
                    iv_del2.setVisibility(View.GONE);
                }
            }
        });
        et_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_address.getText()!=null&&!et_address.getText().toString().equals("")){
                    iv_del3.setVisibility(View.VISIBLE);
                }else{
                    iv_del3.setVisibility(View.GONE);
                }
            }
        });

    }
    //检查手机号是否合法
    public static boolean isMobileNO(String mobiles) {

        String telRegex = "[1][35874]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)){
            return false;
        }
        else return mobiles.matches(telRegex);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb1:
                radioGroup.check(R.id.rb1);
                break;
            case R.id.rb2:
                radioGroup.check(R.id.rb2);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_address:
                Intent intent = new Intent(this, MapActivity.class);
                startActivityForResult(intent,REQUESTCODE);
                break;
            case R.id.iv_sure:
                name=et_name.getText().toString();
                phone=et_phone.getText().toString();
                door=et_address.getText().toString();
                street=tv_address.getText().toString();
                sex=rb1.isChecked()?"先生":"小姐";
                doCheck();
                if (ok){
                    Toast.makeText(this, "合格", Toast.LENGTH_SHORT).show();
                    if (editMode||addMode){
                        Intent intent1 = new Intent();
                        intent1.putExtra("street",street);
                        intent1.putExtra("door",door);
                        intent1.putExtra("name",name);
                        intent1.putExtra("sex",sex);
                        intent1.putExtra("tel",phone);
                        intent1.putExtra("position",position);
                        setResult(RESULT_OK,intent1);
                        editMode=false;
                        addMode=false;
                        finish();
                    }else if (commMode){
                        Toast.makeText(this, "成功啦", Toast.LENGTH_SHORT).show();
                        commMode=false;
                        finish();
                    }
                }
                break;
            case R.id.iv_del1:
                et_name.setText("");
                break;
            case R.id.iv_del2:
                et_phone.setText("");
                break;
            case R.id.iv_del3:
                et_address.setText("");
                break;
            case R.id.iv_back_add:
                finish();
                break;
        }
    }

    private void doCheck() {
        if (name.equals("")){
            ok=false;
            Toast.makeText(this, "名字不能为空", Toast.LENGTH_SHORT).show();
        }else{
            if (!isMobileNO(phone)){
                ok=false;
                Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
            }else{
                if (street.equals("")){
                    ok=false;
                    Toast.makeText(this, "请选择地址", Toast.LENGTH_SHORT).show();
                }else{
                    if (door.equals("")){
                        ok=false;
                        Toast.makeText(this, "请填写门牌号", Toast.LENGTH_SHORT).show();
                    }else{
                        ok=true;
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUESTCODE:
                if (resultCode==RESULT_OK){
                    String address = data.getStringExtra("address");
                    tv_address.setText(address);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.et_name:
                if (b){
                    if (!et_name.getText().toString().equals("")){
                        iv_del1.setVisibility(View.VISIBLE);
                    }else{
                        iv_del1.setVisibility(View.GONE);
                    }
                }else{
                    iv_del1.setVisibility(View.GONE);
                }
                break;
            case R.id.et_phone:
                if (b){
                    if (!et_phone.getText().toString().equals("")){
                        iv_del2.setVisibility(View.VISIBLE);
                    }else{
                        iv_del2.setVisibility(View.GONE);
                    }
                }else{
                    iv_del2.setVisibility(View.GONE);
                }
                break;
            case R.id.et_address:
                if (b){
                    if (!et_address.getText().toString().equals("")){
                        iv_del3.setVisibility(View.VISIBLE);
                    }else{
                        iv_del3.setVisibility(View.GONE);
                    }
                }else{
                    iv_del3.setVisibility(View.GONE);
                }
                break;
        }
    }
}
