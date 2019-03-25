package com.ybxcc.testinysx.ybxdemoset.smart_refresh_layout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ybxcc.testinysx.ybxdemoset.R;

public class SmartRefreshLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_refresh_layout);


        final SmartRefreshLayout smart_refresh = findViewById(R.id.smart_refresh);

        smart_refresh.setEnableRefresh(true);
        smart_refresh.setEnableLoadMore(true);

        smart_refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                smart_refresh.finishLoadMore(5000);
//                refreshLayout.setNoMoreData(true);
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });


        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                smart_refresh.finishRefresh(6000);


            }
        });


    }
}
