package com.lost.administrator.md.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.SearchHistory;
import com.lost.administrator.md.utils.CommonAdapter;
import com.lost.administrator.md.utils.DensityUtils;
import com.lost.administrator.md.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView gridView;
    private List<String> list;
    private CommonAdapter<String> adapter;
    private ListView listView;
    private List<SearchHistory> list_history=new ArrayList<SearchHistory>();
    private CommonAdapter<SearchHistory> adapter_history;
    private LinearLayout ll_del;
    private SharedPreferences spf;
    private SharedPreferences.Editor editor;
    private static final String HISTORY="search_history";
    private Button btn_search;
    private EditText et_search;
    private ImageView iv_del;
    private ImageView iv_back;
    private LinearLayout ll_search_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(  R.layout.activity_search);
        spf=getSharedPreferences(HISTORY,MODE_PRIVATE);
        String history = spf.getString("history","");


        initView();
        if (history.equals("")){
            ll_search_search.setVisibility(View.GONE);
        }else{
            List<SearchHistory> strings = JSON.parseArray(history, SearchHistory.class);
            if (strings!=null&&strings.size()>0){
                list_history.addAll(strings);
            }
        }
        initData();
        initEvent();
    }

    private void initView() {
        gridView= (GridView) findViewById(R.id.gv_hot_search);
        listView= (ListView) findViewById(R.id.lv_history_search);
        ll_del= (LinearLayout) findViewById(R.id.ll_del_search);
        btn_search= (Button) findViewById(R.id.btn_search_search);
        et_search= (EditText) findViewById(R.id.et_search);
        iv_del= (ImageView) findViewById(R.id.iv_del_search);
        iv_back= (ImageView) findViewById(R.id.iv_back_search);
        ll_search_search= (LinearLayout) findViewById(R.id.ll_history_search);

    }

    private void initData() {
        list=new ArrayList<String>();
        for (int i=1;i<10;i++){
            list.add("热搜"+i);
        }

        adapter=new CommonAdapter<String>(this,list,R.layout.item_hot_search_layout) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.tv_hot_search,s);
            }
        };
        gridView.setAdapter(adapter);

//        for (int i=0;i<10;i++){
//            list_history.add(new SearchHistory("","记录"+i));
//        }
        adapter_history=new CommonAdapter<SearchHistory>(this,list_history,R.layout.item_history_layout) {
            @Override
            public void convert(ViewHolder holder, SearchHistory s) {
                holder.setText(R.id.tv_title_history,s.getTitle());
            }
        };

        listView.setAdapter(adapter_history);
        DensityUtils.setListViewHeightBasedOnChildren(listView);
        adapter_history.notifyDataSetChanged();
    }

    private void initEvent() {
        ll_del.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        iv_del.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String s = et_search.getText().toString();
                if (s!=null&&!s.equals("")){
                    iv_del.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_del_search:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setNegativeButton("取消",null)
                        .setMessage("是否删除历史记录")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                list_history.clear();
                                editor=spf.edit();
                                editor.clear();
                                editor.commit();
                                adapter_history.notifyData(list_history);
                                DensityUtils.setListViewHeightBasedOnChildren(listView);
                                ll_search_search.setVisibility(View.GONE);
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
                break;
            case R.id.btn_search_search:
                String s = et_search.getText().toString();
                if (!s.equals("")&&s!=null){
                    list_history.add(new SearchHistory(1,s));
                    adapter_history.notifyData(list_history);
                    String s1 = JSON
                            .toJSONString(list_history);
                    editor=spf.edit();
                    editor.putString("history",s1);
                    editor.commit();
                }
                Toast.makeText(this, "搜索完成", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_del_search:
                et_search.setText("");
                iv_del.setVisibility(View.GONE);
                break;
            case R.id.iv_back_search:
                finish();
                break;



        }
    }
}
