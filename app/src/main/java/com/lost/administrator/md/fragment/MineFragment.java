package com.lost.administrator.md.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lost.administrator.md.R;
import com.lost.administrator.md.activity.EnterBrandActivity;
import com.lost.administrator.md.activity.HelpActivity;
import com.lost.administrator.md.activity.LoginActivity;
import com.lost.administrator.md.activity.MoreActivity;
import com.lost.administrator.md.activity.MyAccountActivity;
import com.lost.administrator.md.activity.MyAddressActivity;
import com.lost.administrator.md.activity.MyCollectActivity;
import com.lost.administrator.md.activity.MyFriendActivity;
import com.lost.administrator.md.activity.MyPingJiaActivity;
import com.lost.administrator.md.activity.RedBagActivity;
import com.lost.administrator.md.activity.ShareActivity;
import com.lost.administrator.md.activity.VoucherActivity;
import com.lost.administrator.md.activity.YuErActivity;
import com.lost.administrator.md.entity.User;

public class MineFragment extends Fragment implements View.OnClickListener {

    private LinearLayout ll_content;
    private LinearLayout ll_mine1;
    private LinearLayout ll_mine2;
    private LinearLayout ll_mine3;
    private LinearLayout ll_pingJia;
    private LinearLayout ll_coll;
    private LinearLayout ll_address;
    private LinearLayout ll_share;
    private LinearLayout ll_enter;
    private LinearLayout ll_help;
    private LinearLayout ll_more;
    private LinearLayout ll_friend;
    private TextView tv_server;
    private RelativeLayout rl_set;
    private User user=new User();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.minefragment_layout, container,false);
        initView(view);
        initEvent();
        return view;
    }

    private void initEvent() {
        ll_content.setOnClickListener(this);
        ll_mine1.setOnClickListener(this);
        ll_mine2.setOnClickListener(this);
        ll_mine3.setOnClickListener(this);
        ll_pingJia.setOnClickListener(this);
        ll_coll.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        ll_share.setOnClickListener(this);
        ll_enter.setOnClickListener(this);
        ll_help.setOnClickListener(this);
        ll_more.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        tv_server.setOnClickListener(this);
        rl_set.setOnClickListener(this);

    }


    private void initView(View view) {
        ll_content= (LinearLayout) view.findViewById(R.id.ll_content);
        ll_mine1= (LinearLayout) view.findViewById(R.id.ll_mime1);
        ll_mine2= (LinearLayout) view.findViewById(R.id.ll_mine2);
        ll_mine3= (LinearLayout) view.findViewById(R.id.ll_mine_3);
        ll_pingJia= (LinearLayout) view.findViewById(R.id.ll_pingJia_mine);
        ll_coll= (LinearLayout) view.findViewById(R.id.ll_collect_mine);
        ll_address= (LinearLayout) view.findViewById(R.id.ll_address_mine);
        ll_share= (LinearLayout) view.findViewById(R.id.ll_share_mine);
        ll_enter= (LinearLayout) view.findViewById(R.id.ll_enter_mine);
        ll_help= (LinearLayout) view.findViewById(R.id.ll_help_mine);
        ll_more= (LinearLayout) view.findViewById(R.id.ll_more_mine);
        ll_friend= (LinearLayout) view.findViewById(R.id.ll_friend_mine);
        tv_server= (TextView) view.findViewById(R.id.tv_service_tel);
        rl_set= (RelativeLayout) view.findViewById(R.id.rl_set_mine);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_content:
                if (user==null){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.ll_mime1:
                if (user!=null){
                    startActivity(new Intent(getActivity(), YuErActivity.class));
                }
                break;
            case R.id.ll_mine2:
                if (user!=null){
                    startActivity(new Intent(getActivity(), RedBagActivity.class));
                }
                break;
            case R.id.ll_mine_3:
                if (user!=null){
                    startActivity(new Intent(getActivity(), VoucherActivity.class));
                }
                break;
            case R.id.ll_pingJia_mine:
                startActivity(new Intent(getActivity(), MyPingJiaActivity.class));
                break;
            case R.id.ll_collect_mine:
                startActivity(new Intent(getActivity(), MyCollectActivity.class));
                break;
            case R.id.ll_address_mine:
                startActivity(new Intent(getActivity(), MyAddressActivity.class));
                break;
            case R.id.ll_share_mine:
                startActivity(new Intent(getActivity(), ShareActivity.class));
                break;
            case R.id.ll_enter_mine:
                startActivity(new Intent(getActivity(), EnterBrandActivity.class));
                break;
            case R.id.ll_help_mine:
                startActivity(new Intent(getActivity(), HelpActivity.class));
                break;
            case R.id.ll_more_mine:
                startActivity(new Intent(getActivity(), MoreActivity.class));
                break;
            case R.id.ll_friend_mine:
                startActivity(new Intent(getActivity(), MyFriendActivity.class));
                break;
            case R.id.tv_service_tel:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:10109777"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.rl_set_mine:
                startActivity(new Intent(getActivity(), MyAccountActivity.class))                            ;
                break;
        }
    }
}
