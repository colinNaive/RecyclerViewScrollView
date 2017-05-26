package com.ctrip.zhshan.myapplication;

import android.content.Context;

/**
 * @author Zhenhua on 2017/5/25 10:30.
 * @email zhshan@ctrip.com
 */

public class CommonUtils {
    public static int getScreenHeight(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        if (null == context) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
