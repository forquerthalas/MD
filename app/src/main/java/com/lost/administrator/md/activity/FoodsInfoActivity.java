package com.lost.administrator.md.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lost.administrator.md.R;
import com.lost.administrator.md.entity.FoodsInfo;
import com.lost.administrator.md.entity.Goods;
import com.lost.administrator.md.entity.GoodsInfo;
import com.lost.administrator.md.utils.ImagUtil;
import com.lost.administrator.md.widget.RecyclerGallery;

import java.util.ArrayList;
import java.util.List;


public class FoodsInfoActivity extends AppCompatActivity {
    private static final String TAG ="FoodsInfoActivity" ;
    private ViewGroup viewGroup;// 动画层
    private TextView tv_toPay,tv_cartNum;
    private RecyclerGallery recyclerView;
    private RAdapter adapter;
    private List<FoodsInfo> list_foods=new ArrayList<FoodsInfo>();
    private int totalCount=0;
    private float totalPricce=0;
    private TextView tv_totalprice;
    private ListView lv_cart_pop;
    private List<FoodsInfo> list_cart=new ArrayList<FoodsInfo>();
    private CartAdaper cartAdapter;
    private Boolean v=false;
    private PopupWindow mPopuwindow;
    //测试url图片
    private String url="https://ps.ssl.qhimg.com/sdmt/137_135_100/t011fd64776e672353d.jpg";
    private ImageView iv_cart;
    private TextView tv_price_pop;
    private TextView tv_back_pop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_info);
        Intent intent = getIntent();
        if (intent!=null){
            String goods = intent.getStringExtra("goods");
            Log.d(TAG, "onCreate: "+goods);
            List<GoodsInfo> infos = JSONArray.parseArray(goods, GoodsInfo.class);
            Log.d(TAG, "onCreate: "+infos.size());

        }

        initData();
        initView();
        if (totalCount==0){
            tv_cartNum.setVisibility(View.GONE);
        }else {
            tv_cartNum.setVisibility(View.VISIBLE);
            tv_cartNum.setText(totalCount+"");
        }
        tv_totalprice.setText("￥"+totalPricce);
    }
    private void initData() {
        for (int i=1;i<10;i++){
            list_foods.add(new FoodsInfo(url,"秀色可餐"+i,99,1,99,1,99f));
        }
        for (FoodsInfo f:list_foods){

            if (f.getNum()>0){
                totalCount+=f.getNum();
                totalPricce+=f.getPrice()*f.getNum();
                list_cart.add(f);
            }
        }

    }
    private void  showPopuWindow(){
        View contentView = getLayoutInflater().inflate(R.layout.pop_layout, null);
        mPopuwindow=new PopupWindow(contentView);
        mPopuwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopuwindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        lv_cart_pop= (ListView) contentView.findViewById(R.id.lv_cart);
        tv_price_pop= (TextView)contentView.findViewById(R.id.tv_price_pop);
        tv_back_pop= (TextView) contentView.findViewById(R.id.tv_back_pop);

        tv_price_pop.setText("￥"+totalPricce);
        cartAdapter=new CartAdaper();
        lv_cart_pop.setAdapter(cartAdapter);
        View rootView = getLayoutInflater().inflate(R.layout.activity_foods_info, null);
        mPopuwindow.showAtLocation(rootView,0,0,1110);
        mPopuwindow.setOutsideTouchable(true);
        tv_back_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disMiss();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mPopuwindow!=null&&mPopuwindow.isShowing()){
            disMiss();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void initView() {

        recyclerView= (RecyclerGallery) findViewById(R.id.recyclerView);
        adapter=new RAdapter(this,list_foods);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        tv_toPay= (TextView) findViewById(R.id.tv_goToPay);
        tv_cartNum= (TextView) findViewById(R.id.tv_cartNum);
        tv_totalprice= (TextView) findViewById(R.id.shoppingPrice);
        iv_cart= (ImageView) findViewById(R.id.shopping_cart);
        iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!v){
                    if (list_cart.size()>0){
                        show();
                    }else{
                        return;
                    }
                }else{
                   disMiss();
                }
            }
        });
    }
    //显示popWindow;
    private void show(){
        list_cart.clear();
        for (FoodsInfo f:list_foods){
            if (f.getNum()>0){
                list_cart.add(f);
            }
        }
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        showPopuWindow();
        v=!v;

    }

    //隐藏popWindow
    private void disMiss(){

        if (list_cart.size()==0){
            for (FoodsInfo f:list_foods){
                f.setNum(0);
            }
        }else{
            for (FoodsInfo fc: list_cart){
                for (FoodsInfo ff:list_foods){
                    if (fc.getName().equals(ff.getName())){
                        ff.setNum(fc.getNum());
                    }
                }
            }
        }
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1f;
        getWindow().setAttributes(lp);
        mPopuwindow.dismiss();
        adapter.notifyDataSetChanged();
        tv_totalprice.setText("￥"+totalPricce);
        tv_cartNum.setText(totalCount+"");
        if (totalCount==0){
            tv_cartNum.setVisibility(View.GONE);
        }
        v=!v;
        mPopuwindow=null;
    }
    //适配器
    class RAdapter extends RecyclerView.Adapter<MyHolder> {

        private LayoutInflater inflater;
        public List<FoodsInfo> list_foods;

        public RAdapter(Context context,List<FoodsInfo> list_foods){
                inflater=LayoutInflater.from(context);
                this.list_foods=list_foods;

        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            return new MyHolder(inflater.inflate(R.layout.item_recycle_layout,null));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.bindHolder(list_foods.get(position));
        }

        @Override
        public int getItemCount() {
            return list_foods.size();
        }


    }
    //Holder类
    class MyHolder extends  RecyclerView.ViewHolder {
        private ImageView iv_back, iv_share, iv_icon, iv_reduce, iv_increase, iv_addToCart;
        private TextView tv_name,tv_monthCount,tv_price,tv_haoPing,tv_chaping,
                tv_pingJia,tv_noPingJia,tv_shoppingNum;
        private ProgressBar progressBar;
        private LinearLayout ll_buy, ll_pingJia, ll_chaPing, ll_haoPing;

        public MyHolder(View itemView) {
            super(itemView);
            iv_back = (ImageView) itemView.findViewById(R.id.iv_back_goodsInfo);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon_goodsInfo);
            iv_share = (ImageView) itemView.findViewById(R.id.iv_share_goodsInfo);
            iv_reduce = (ImageView) itemView.findViewById(R.id.reduce);
            iv_increase = (ImageView) itemView.findViewById(R.id.increase);
            ll_buy = (LinearLayout) itemView.findViewById(R.id.ll_buy);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name_goodsInfo);
            tv_monthCount = (TextView) itemView.findViewById(R.id.tv_monthCount_goodsInfo);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price_goodsInfo);
            tv_chaping = (TextView) itemView.findViewById(R.id.tv_chaPing_goodsInfo);
            tv_haoPing = (TextView) itemView.findViewById(R.id.tv_haoPing_goodsInfo);
            tv_pingJia = (TextView) itemView.findViewById(R.id.tv_pingJia_goodsInfo);
            progressBar = (ProgressBar) itemView.findViewById(R.id.contentLoadingProgressBar);
            iv_addToCart = (ImageView) itemView.findViewById(R.id.imageView_addcart);
            ll_pingJia = (LinearLayout) itemView.findViewById(R.id.ll_3);
            tv_noPingJia = (TextView) itemView.findViewById(R.id.tv_noPingJia);
            ll_chaPing = (LinearLayout) itemView.findViewById(R.id.ll_chaPing);
            ll_haoPing = (LinearLayout) itemView.findViewById(R.id.ll_haoPing);
            tv_shoppingNum= (TextView) itemView.findViewById(R.id.shoppingNum);
        }

        public void bindHolder(final FoodsInfo foodsInfo) {
            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            iv_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    foodsInfo.setNum(foodsInfo.getNum()-1);
                    totalCount-=1;
                    totalPricce-=foodsInfo.getPrice();
                    tv_totalprice.setText("￥"+totalPricce);
                    tv_cartNum.setText(totalCount+"");
                    if (totalCount==0){
                        tv_cartNum.setVisibility(View.GONE);
                    }
                    for (FoodsInfo f:list_cart){
                        if (f.getName().equals(foodsInfo.getName())){
                            f.setNum(foodsInfo.getNum());
                            if (foodsInfo.getNum()==0){
                                list_cart.remove(f);
                                break;
                            }
                            break;
                        }

                    }
                    if (foodsInfo.getNum()==0){
                        iv_addToCart.setVisibility(View.VISIBLE);
                        ll_buy.setVisibility(View.GONE);
                    }

                }
            });
            iv_increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    foodsInfo.setNum(foodsInfo.getNum()+1);
                    tv_shoppingNum.setText(foodsInfo.getNum()+"");
                    totalCount+=1;
                    totalPricce+=foodsInfo.getPrice();
                    tv_totalprice.setText("￥"+totalPricce);
                    tv_cartNum.setText(totalCount+"");
                    int [] startLocation=new int[2];
                    iv_increase.getLocationInWindow(startLocation);
                    //复制一个新的商品图标

                    ImageView iv=new ImageView(FoodsInfoActivity.this);
                    iv.setImageResource(R.drawable.s9);
                    move(iv,startLocation);
                }
            });
            iv_addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iv_addToCart.setVisibility(View.GONE);
                    tv_cartNum.setVisibility(View.VISIBLE);
                    list_cart.add(foodsInfo);
                    ll_buy.setVisibility(View.VISIBLE);
                    totalCount+=1;
                    totalPricce+=foodsInfo.getPrice();
                    tv_totalprice.setText("￥"+totalPricce);
                    foodsInfo.setNum(foodsInfo.getNum()+1);
                    tv_cartNum.setText(totalCount+"");
                    tv_shoppingNum.setText("1");
                }
            });

            ImagUtil.setUrlImage(iv_icon, foodsInfo.getUrl());
            if (foodsInfo.getNum() == 0) {
                iv_addToCart.setVisibility(View.VISIBLE);
                ll_buy.setVisibility(View.GONE);
            } else {
                iv_addToCart.setVisibility(View.GONE);
                ll_buy.setVisibility(View.VISIBLE);
                tv_shoppingNum.setText(foodsInfo.getNum()+"");
            }
            tv_name.setText(foodsInfo.getName());
            tv_monthCount.setText("月售" + foodsInfo.getMonthCount());
            tv_price.setText("￥"+foodsInfo.getPrice());
            if (foodsInfo.getChaPing() == 0 && foodsInfo.getHaoPing() == 0) {
                ll_pingJia.setVisibility(View.GONE);
                tv_noPingJia.setText("暂无评价");
                tv_pingJia.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            } else if (foodsInfo.getChaPing() == 0 && foodsInfo.getHaoPing() > 0) {
                ll_pingJia.setVisibility(View.VISIBLE);
                tv_noPingJia.setText("好评率");
                tv_pingJia.setText("100%");
                tv_haoPing.setText(foodsInfo.getHaoPing()+"");
                progressBar.setProgress(100);

                progressBar.setVisibility(View.VISIBLE);
                ll_chaPing.setVisibility(View.GONE);
                ll_haoPing.setVisibility(View.VISIBLE);
            } else if (foodsInfo.getChaPing() > 0 && foodsInfo.getHaoPing() == 0) {
                ll_pingJia.setVisibility(View.VISIBLE);
                tv_noPingJia.setText("好评率");
                progressBar.setProgress(0);
                tv_pingJia.setText("0");
                tv_chaping.setText(""+foodsInfo.getChaPing());
                ll_chaPing.setVisibility(View.VISIBLE);
                ll_haoPing.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            } else if (foodsInfo.getChaPing() > 0 && foodsInfo.getHaoPing() > 0) {
                ll_pingJia.setVisibility(View.VISIBLE);
                tv_noPingJia.setText("好评率");
                int chaPing = foodsInfo.getChaPing();
                int haoPing = foodsInfo.getHaoPing();
                int progress = haoPing * 100 / (haoPing + chaPing);
                progressBar.setVisibility(View.VISIBLE);
                tv_pingJia.setText(progress+"%");

                progressBar.setProgress(progress);
                Log.d(TAG, "bindHolder: "+progressBar.getProgress());
                ll_chaPing.setVisibility(View.VISIBLE);
                tv_chaping.setText(foodsInfo.getChaPing()+"");
                tv_haoPing.setText(foodsInfo.getHaoPing()+"");
                ll_haoPing.setVisibility(View.VISIBLE);
            }
        }
    }
    /**
     * 创建动画层
     *
     * @return
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup vg, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }
    private void move(final View v, int[] start_location) {
        viewGroup = null;
        viewGroup = createAnimLayout();
        viewGroup.addView(v);// 把要移动的控件添加到动画层
        final View view = addViewToAnimLayout(viewGroup, v, start_location);
        int[] end_location = new int[2];// 这是用来存储动画结束位置的X、Y坐标
        tv_cartNum.getLocationInWindow(end_location);


            // 计算位移
            int endX = 0 - start_location[0]
                    + tv_cartNum.getLeft();// 动画位移的X坐标
            int endY = end_location[1] - start_location[1];// 动画位移的y坐标
            TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                    endX, 0, 0);
            translateAnimationX.setInterpolator(new LinearInterpolator());
            translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
            translateAnimationX.setFillAfter(true);

            TranslateAnimation translateAnimationY = new TranslateAnimation(0,
                    0, 0, endY);
            translateAnimationY.setInterpolator(new AccelerateInterpolator());
            translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
            translateAnimationX.setFillAfter(true);

            AnimationSet set = new AnimationSet(false);
            set.setFillAfter(false);
            set.addAnimation(translateAnimationY);
            set.addAnimation(translateAnimationX);
            set.setDuration(400);// 动画的执行时间
            view.startAnimation(set);
            // 动画监听事件
            set.setAnimationListener(new Animation.AnimationListener() {
                // 动画的开始
                @Override
                public void onAnimationStart(Animation animation) {
                    v.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // TODO Auto-generated method stub
                }

                // 动画的结束
                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setVisibility(View.GONE);
                }
            });
    }
    //购物车适配器
    class CartAdaper extends BaseAdapter{

        @Override
        public int getCount() {
            return list_cart.size();
        }

        @Override
        public Object getItem(int i) {
            return list_cart.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final CartHolder cartHolder;
            if (view==null){
                cartHolder=new CartHolder();
                view=getLayoutInflater().inflate(R.layout.item_lv_cart_layout,viewGroup,false);
                cartHolder.tv_name= (TextView) view.findViewById(R.id.tv_name_itemCart);
                cartHolder.tv_num= (TextView) view.findViewById(R.id.tv_num_itemCart);
                cartHolder.tv_price= (TextView) view.findViewById(R.id.tv_price_itemCart);
                cartHolder.iv_incease= (ImageView) view.findViewById(R.id.increase_cart);
                cartHolder.iv_reduce= (ImageView) view.findViewById(R.id.reduce_cart);
                view.setTag(cartHolder);
            }else{
                cartHolder= (CartHolder) view.getTag();
            }
            final FoodsInfo f = list_cart.get(i);
            cartHolder.tv_name.setText(f.getName());
            cartHolder.tv_price.setText("￥"+f.getNum()*f.getPrice());
            cartHolder.tv_num.setText(f.getNum()+"");
            cartHolder.iv_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (f.getNum()>1){
                        f.setNum(f.getNum()-1);
                        totalPricce-=f.getPrice();
                        totalCount-=1;

                    }else if (f.getNum()==1){
                        totalPricce-=f.getPrice();
                        tv_price_pop.setText("￥"+totalPricce);
                        totalCount-=1;
                        list_cart.remove(f);
                        if (list_cart.size()==0){
                            disMiss();
                            return;
                        }

                    }
                    notifyDataSetChanged();
                }
            });
            cartHolder.iv_incease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   f.setNum(f.getNum()+1);
                    totalCount+=1;
                    totalPricce+=f.getPrice();
                   notifyDataSetChanged();

                }
            });

            return view;
        }

    }

    static class CartHolder{
        TextView tv_name,tv_price ,tv_num;
        ImageView iv_incease,iv_reduce;
    }

}
