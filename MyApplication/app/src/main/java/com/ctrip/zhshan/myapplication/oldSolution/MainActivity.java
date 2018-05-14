package com.ctrip.zhshan.myapplication.oldSolution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ctrip.zhshan.myapplication.R;
import com.ctrip.zhshan.myapplication.airStop.AirStopActivity;
import com.ctrip.zhshan.myapplication.newSolution.SolutionActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyScrollView.OnFixHeadListener {
    private MyScrollView view_hover;
    private RecyclerView recyclerView;
    private LinearLayout headBarLayout;
    private LinearLayoutManager manager;
    private boolean fixedFlag = false, resetFlag = false;
    private HoveringAdapter adapter;
    private List<String> headTitles = new ArrayList<>();
    private int selectPos = 0;
    private TextView other,airStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hover);
        view_hover = (MyScrollView) findViewById(R.id.view_hover);
        view_hover.setFixHeadListener(this);
        headBarLayout = (LinearLayout) findViewById(R.id.head_bar_linear);
        findViewById(R.id.click_to).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearlyMove2Position(11);
                view_hover.post(new Runnable() {
                    @Override
                    public void run() {
                        view_hover.scrollTo(0, headBarLayout.getTop());
                    }
                });
            }
        });
        other = (TextView) findViewById(R.id.other);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SolutionActivity.class);
                startActivity(intent);
            }
        });

        airStop = (TextView) findViewById(R.id.air_stop);
        airStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AirStopActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.getLayoutParams().height = CommonUtils.getScreenHeight(this) - CommonUtils.dp2px(this, 50);
        final String[] data = {"header", "content", "content", "content", "content", "content", "content", "content", "content", "content", "content", "footer",
                "header", "content", "content", "content", "content", "content", "content", "content", "content", "content", "content", "footer",
                "header", "content", "content", "content", "content", "content", "content", "content", "content", "content", "content", "footer"};
        adapter = new HoveringAdapter(this, data);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int last = manager.findLastVisibleItemPosition();
                int first = manager.findFirstVisibleItemPosition();
                int count = manager.getChildCount();

                System.out.println("first=" + first + ";last=" + last + ";count=" + count);
            }

        });
    }

    //move Method without animation
    private void clearlyMove2Position(int pos) {
        manager.scrollToPositionWithOffset(pos, 0);
        manager.setStackFromEnd(false);
    }

    @Override
    public void onFix() {
        enableNestedScrolling(recyclerView);
    }

    @Override
    public void onReset() {
        disableNestedScrolling(recyclerView);
    }

    //Enable nested scrolling of recyclerView in ScrollView
    private void enableNestedScrolling(RecyclerView recyclerView) {
        if (recyclerView != null) {
            if (!fixedFlag) {
                setFixedFlag();
                recyclerView.setNestedScrollingEnabled(true);
            }
        }
    }

    //Disable nested scrolling of recyclerView in ScrollView
    private void disableNestedScrolling(RecyclerView recyclerView) {
        if (recyclerView != null) {
            if (!resetFlag) {
                setResetFlag();
                recyclerView.setNestedScrollingEnabled(false);
            }
        }
    }

    private void setFixedFlag() {
        setFlag(false);
    }

    private void setResetFlag() {
        setFlag(true);
    }

    //True:reset;false:fix
    private void setFlag(boolean reset) {
        if (reset) {
            resetFlag = true;
            fixedFlag = false;
        } else {
            fixedFlag = true;
            resetFlag = false;
        }
    }
}
