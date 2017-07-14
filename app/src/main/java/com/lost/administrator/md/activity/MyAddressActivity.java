package com.lost.administrator.md.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.Address;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private List<Address> list;
    private MyAdapter adapter;
    private TextView tv_manage;
    private boolean isDelMode=false;
    private LinearLayout ll_add;
    private static final int EDIT=0x99;
    private static final int ADD=0x95;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        tv_manage.setOnClickListener(this);
        ll_add.setOnClickListener(this);
        iv_back.setOnClickListener(this);

    }

    private void initData() {
        list=new ArrayList<Address>();
        for (int i=1;i<10;i++){
            list.add(new Address("火星"+i+"号","207室","坏脾气","小姐","15092696151"));
        }
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
    }

    private void initView() {
        listView= (ListView) findViewById(R.id.list_address);
        tv_manage= (TextView) findViewById(R.id.tv_manage_address);
        ll_add= (LinearLayout) findViewById(R.id.ll_add_address);
        iv_back= (ImageView) findViewById(R.id.iv_back_address);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_manage_address:
                isDelMode=!isDelMode;
                if (isDelMode){
                    ll_add.setVisibility(View.GONE);
                    for (Address a:list){
                        a.setDelMode(true);
                    }
                    tv_manage.setText("完成");
                    adapter.notifyDataSetChanged();
                }else{
                    ll_add.setVisibility(View.VISIBLE);
                    for (Address a:list){
                        a.setDelMode(false);
                    }
                    tv_manage.setText("管理");
                    ll_add.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.ll_add_address:
                Intent intent = new Intent(this, AddAddressActivity.class);
                intent.putExtra("add",true);
                startActivityForResult(intent,ADD);
                break;
            case R.id.iv_back_address:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case EDIT:
                if (resultCode==RESULT_OK){
                    String street = data.getStringExtra("street");
                    String door = data.getStringExtra("door");
                    String name = data.getStringExtra("name");
                    String sex = data.getStringExtra("sex");
                    String tel = data.getStringExtra("tel");
                    int position = data.getIntExtra("position", -1);
                    if (position>=0){
                        list.set(position,new Address(street,door,name,sex,tel));
                        for (Address a:list){
                            a.setDelMode(false);
                        }
                        isDelMode=false;
                        tv_manage.setText("管理");
                        adapter.notifyDataSetChanged();
                    }

                }
                break;
            case ADD:
                if (resultCode==RESULT_OK) {
                    String street = data.getStringExtra("street");
                    String door = data.getStringExtra("door");
                    String name = data.getStringExtra("name");
                    String sex = data.getStringExtra("sex");
                    String tel = data.getStringExtra("tel");
                    list.add(new Address(street,door,name,sex,tel));
                    adapter.notifyDataSetChanged();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            final Address a = list.get(position);
            MyHolder holder;
            if (view==null){
                holder=new MyHolder();
                view=getLayoutInflater().inflate(R.layout.item_address_layout,null);
                holder.tv_street= (TextView) view.findViewById(R.id.tv_street_address);
                holder.tv_door= (TextView) view.findViewById(R.id.tv_door_address);
                holder.tv_sex= (TextView) view.findViewById(R.id.tv_sex_address);
                holder.tv_name= (TextView) view.findViewById(R.id.tv_name_address);
                holder.tv_phone= (TextView) view.findViewById(R.id.tv_tel_address);
                holder.iv_edit= (ImageView) view.findViewById(R.id.iv_edit_address);
                holder.iv_del= (ImageView) view.findViewById(R.id.iv_del_address);
                view.setTag(holder);
            }else{
                holder=(MyHolder)view.getTag();
            }
            holder.tv_street.setText(a.getStreet());
            holder.tv_door.setText(a.getDoor());
            holder.tv_name.setText(a.getName());
            holder.tv_sex.setText(a.getSex());
            holder.tv_phone.setText(a.getTel());
            holder.iv_edit.setVisibility(View.GONE);
            holder.iv_del.setVisibility(View.GONE);
            if (a.isDelMode()){
                holder.iv_edit.setVisibility(View.VISIBLE);
                holder.iv_del.setVisibility(View.VISIBLE);
                holder.iv_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyAddressActivity.this);
                        builder.setMessage("确认删除此送货地址?")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        boolean remove = list.remove(a);
                                        if (remove){
                                            Toast.makeText(MyAddressActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(MyAddressActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                                        }
                                        MyAdapter.this.notifyDataSetChanged();
                                    }
                                }).setNegativeButton("取消",null).show();
                    }
                });

                holder.iv_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MyAddressActivity.this,AddAddressActivity.class);
                        intent.putExtra("position",position);
                        intent.putExtra("name",a.getName());
                        intent.putExtra("street",a.getStreet());
                        intent.putExtra("door",a.getDoor());
                        intent.putExtra("sex",a.getSex());
                        intent.putExtra("tel",a.getTel());
                        startActivityForResult(intent,EDIT);
                    }
                });
            }

            return view;
        }
    }
    class MyHolder{
        public TextView tv_street,tv_door,tv_sex,tv_name,tv_phone;
        public ImageView iv_edit,iv_del;
    }
}

