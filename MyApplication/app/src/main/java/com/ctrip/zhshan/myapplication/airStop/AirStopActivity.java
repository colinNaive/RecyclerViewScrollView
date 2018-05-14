package com.ctrip.zhshan.myapplication.airStop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ctrip.zhshan.myapplication.R;

/**
 * @author Zhenhua on 2018/3/20.
 * @email zhshan@ctrip.com ^.^
 */

public class AirStopActivity extends AppCompatActivity {
    private NestedScrollView mCustomScrollView;
    private LinearLayout mTargetContentContainer;
    private LinearLayout mOldContentContainer;
    private LinearLayout mNewContentContainer;
    private HorizontalScrollView mTargetHorizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.air_stop_fragment_layout);
        init();
    }

    private void init() {
        mCustomScrollView = (NestedScrollView) findViewById(R.id.scrollview);
        mOldContentContainer = (LinearLayout) findViewById(R.id.horizontal_content);
        mTargetContentContainer = (LinearLayout) findViewById(R.id.target_content);
        mTargetHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.target_horizontal_scrollview);
        initHorizontalScrollView(mTargetContentContainer);
        mNewContentContainer = (LinearLayout) findViewById(R.id.horizontal_content_new);
        mCustomScrollView.setFixListener(new NestedScrollView.OnFixListener() {
            @Override
            public void onFix() {
                mOldContentContainer.removeView(mTargetHorizontalScrollView);
                mNewContentContainer.addView(mTargetHorizontalScrollView);
            }

            @Override
            public void onDismiss() {
                mNewContentContainer.removeView(mTargetHorizontalScrollView);
                mOldContentContainer.addView(mTargetHorizontalScrollView);
            }
        });
    }

    private void initHorizontalScrollView(LinearLayout schedules) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(CommonUtil.dp2px(this, 65), CommonUtil.dp2px(this, 40));
        lp.rightMargin = CommonUtil.dp2px(this, 5f);
        lp.leftMargin = CommonUtil.dp2px(this, 5f);

        //情况ScrollView数据
        schedules.removeAllViews();

        //填充ScrollView数据
        for (int i = 0; i < 10; i++) {
            View item = LayoutInflater.from(this).inflate(R.layout.cttour_vacation_detail_schedule_item, null);
            TextView date = (TextView) item.findViewById(R.id.date);
            TextView price = (TextView) item.findViewById(R.id.price);

            date.setText("劳动节");
            price.setText("5月1号");

            schedules.addView(item, lp);
        }
    }
}

