package com.ctrip.zhshan.myapplication.newSolution;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ctrip.zhshan.myapplication.R;
import com.ctrip.zhshan.myapplication.headerScrollView.HeaderScrollHelper;
import com.ctrip.zhshan.myapplication.headerScrollView.HeaderScrollView;
import com.ctrip.zhshan.myapplication.oldSolution.CommonUtils;
import com.ctrip.zhshan.myapplication.oldSolution.HoveringAdapter;

/**
 * @author Zhenhua on 2018/3/19.
 * @email zhshan@ctrip.com ^.^
 */

public class SolutionActivity extends AppCompatActivity implements HeaderScrollHelper.ScrollableContainer {
    private RecyclerView recyclerView;
    private HeaderScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solution_activity_layout);
        scrollView = (HeaderScrollView) findViewById(R.id.view_hover);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        scrollView.setCurrentScrollableContainer(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //设置recyclerview的高度为屏幕高度-状态栏高度-header高度
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtils.getScreenHeight(this) - CommonUtils.getBarHeight(this) - CommonUtils.dp2px(this, 55)));
        final String[] data = {"header", "content", "content", "content", "content", "content", "content", "content", "content", "content", "content", "footer",
                "header", "content", "content", "content", "content", "content", "content", "content", "content", "content", "content", "footer",
                "header", "content", "content", "content", "content", "content", "content", "content", "content", "content", "content", "footer"};
        HoveringAdapter adapter = new HoveringAdapter(this, data);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public View getScrollableView() {
        return recyclerView;
    }
}

