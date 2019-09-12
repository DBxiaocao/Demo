package me.xiaocao.demo.base.utils;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * @author xiaocao
 * Description 获取屏幕信息
 * date:2019/4/23 14:41
 */
public class DisplayUtil {

	public float density;

	public DisplayUtil() {
		density = Resources.getSystem().getDisplayMetrics().density;
	}

	/**
	 * 获取屏幕宽度
	 * @return
	 */
	public static int getScreenWidth() {
		return Resources.getSystem().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * @return
	 */
	public static int getScreenHeight() {
		return Resources.getSystem().getDisplayMetrics().heightPixels;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * @param dpValue 虚拟像素
	 * @return 像素
	 */
	public static int dp2px(float dpValue) {
		return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * @param pxValue 像素
	 * @return 虚拟像素
	 */
	public static float px2dp(int pxValue) {
		return (pxValue / Resources.getSystem().getDisplayMetrics().density);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * @param dpValue 虚拟像素
	 * @return 像素
	 */
	public int dip2px(float dpValue) {
		return (int) (0.5f + dpValue * density);
	}

	/**
	 * px转sp
	 * @param px
	 * @return
	 */
	public static int pxToSp(int px) {
		return Math.round(px / Resources.getSystem().getDisplayMetrics().scaledDensity + 0.5f);
	}
	/**
	 * sp转px
	 * @param sp
	 * @return
	 */
	public static int spToPx(int sp) {
		return Math.round(Resources.getSystem().getDisplayMetrics().scaledDensity * sp + 0.5f);
	}


	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * @param pxValue 像素
	 * @return 虚拟像素
	 */
	public float px2dip(int pxValue) {
		return (pxValue / density);
	}
	/**
	 * 获取字体高度
	 */
	public static float getTextHeight(Paint p) {
		Paint.FontMetrics fm = p.getFontMetrics();
		return (float) ((Math.ceil(fm.descent - fm.top) + 2) / 2);
	}
	public static int getTextWidth(String str, Paint paint) {
		Rect bounds = new Rect();
		paint.getTextBounds(str, 0, str.length(), bounds);
		return bounds.width();
	}
}
