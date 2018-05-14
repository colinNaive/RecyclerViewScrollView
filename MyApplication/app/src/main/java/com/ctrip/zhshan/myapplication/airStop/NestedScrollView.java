package com.ctrip.zhshan.myapplication.airStop;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;


/**
 * @author Zhenhua on 2017/6/2 09:46.
 * @email zhshan@ctrip.com
 */

public class NestedScrollView extends ScrollView {
    private View fixView;
    private OnFixListener listener;
    private boolean fixed;


    public NestedScrollView(Context context) {
        super(context);
    }

    public NestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (getScrollY() >= fixView.getTop()) {
            fix();
        } else {
            dismiss();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            LinearLayout linearLayout = (LinearLayout) getChildAt(0);
            if (linearLayout != null) {
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    if (linearLayout.getChildAt(i).getTag() != null && linearLayout.getChildAt(i).getTag().equals("fix")) {
                        fixView = linearLayout.getChildAt(i);
                    }
                }
            }
        }
    }

    public void setFixListener(OnFixListener listener) {
        this.listener = listener;
    }

    private void fix() {
        if (listener != null && !fixed) {
            listener.onFix();
            fixed = true;
        }
    }

    private void dismiss() {
        if (listener != null && fixed) {
            listener.onDismiss();
            fixed = false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    public interface OnFixListener {
        void onFix();

        void onDismiss();
    }

}
