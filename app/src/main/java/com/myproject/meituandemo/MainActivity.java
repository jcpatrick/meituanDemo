package com.myproject.meituandemo;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initPopupWindow();
    }

    TextView tv_product,tv_sort,tv_account,tv_title;
    LinearLayout lay_product,lay_sort,lay_account;

    ListView popListView;


    private void initView() {
        //btn
        tv_product = (TextView)findViewById(R.id.tv_product);
        tv_sort = (TextView)findViewById(R.id.tv_sort);
        tv_account = (TextView)findViewById(R.id.tv_account);
        tv_title = (TextView)findViewById(R.id.tv_title);
        //layout
        lay_product = (LinearLayout)findViewById(R.id.lay_product);
        lay_sort = (LinearLayout)findViewById(R.id.lay_sort);
        lay_account = (LinearLayout)findViewById(R.id.lay_account);
        lay_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_product.setTextColor(Color.parseColor("#39ac69"));
                popListView.setAdapter(productAdapter);
                popupWindow.showAsDropDown(lay_product, 0, 2);
                currentIndex = 0;
            }
        });
        lay_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_sort.setTextColor(Color.parseColor("#39ac69"));
                popListView.setAdapter(sortAdapter);
                popupWindow.showAsDropDown(lay_product, 0, 2);
                currentIndex = 1;
            }
        });
        lay_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_account.setTextColor(Color.parseColor("#39ac69"));
                popListView.setAdapter(accountAdapter);
                popupWindow.showAsDropDown(lay_product, 0, 2);
                currentIndex = 2;
            }
        });
    }


    String[] menuStr1,menuStr2,menuStr3;

    private void initData() {
        menuStr1 = new String[]{"全部","粮油","衣服","图书","电子产品","酒水饮料","水果"};
        menuStr2 = new String[]{"综合排序","配送费最低"};
        menuStr3 = new String[]{"优惠活动","特价活动","免费配送","可在线支付"};
    }


    PopupWindow popupWindow;
    ArrayAdapter productAdapter, sortAdapter,accountAdapter;
    int currentIndex = 0;
    private String currentProduct, currentSort, currentAccount;
    ProgressBar progressBar;
    public void initPopupWindow(){
//        initData();
        View convertView = View.inflate(this,R.layout.pop,null);
        popupWindow = new PopupWindow(convertView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.anim.shape_progress);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tv_product.setTextColor(Color.parseColor("#5a5959"));
                tv_sort.setTextColor(Color.parseColor("#5a5959"));
                tv_account.setTextColor(Color.parseColor("#5a5959"));
            }
        });
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getY() < popListView.getY() || event.getY() > popListView.getY() + popListView.getMeasuredHeight()) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        return true;
                    }
                }
                return false;
            }
        });
        popListView = (ListView) convertView.findViewById(R.id.list_view);
         productAdapter = new ArrayAdapter(this,R.layout.pop_item,R.id.tv_pop, menuStr1);
        sortAdapter = new ArrayAdapter(this,R.layout.pop_item,R.id.tv_pop, menuStr2);
        accountAdapter = new ArrayAdapter(this,R.layout.pop_item,R.id.tv_pop, menuStr3);
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                if (currentIndex == 0) {
                    currentProduct = menuStr1[position];
                    tv_title.setText(currentProduct);
                    tv_product.setText(currentProduct);
                    Toast.makeText(MainActivity.this, currentProduct, Toast.LENGTH_SHORT).show();
                } else if (currentIndex == 1) {
                    currentSort = menuStr2[position];
                    tv_title.setText(currentSort);
                    tv_sort.setText(currentSort);
                    Toast.makeText(MainActivity.this, currentSort, Toast.LENGTH_SHORT).show();
                } else if (currentIndex == 2) {
                    currentAccount = menuStr3[position];
                    tv_title.setText(currentAccount);
                    tv_account.setText(currentAccount);
                    Toast.makeText(MainActivity.this, currentAccount, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
