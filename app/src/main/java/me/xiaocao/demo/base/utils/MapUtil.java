package me.xiaocao.demo.base.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.baidu.mapapi.map.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import me.xiaocao.demo.MyApp;

/**
 * @author 　xiaocao
 * Description
 * Date:2019/6/14 0014 17:05
 */
public class MapUtil {
    public final static String BAIDU_PKG = "com.baidu.BaiduMap"; //百度地图的包名

    public final static String GAODE_PKG = "com.autonavi.minimap";//高德地图的包名

    public final static String TENCENT_PKG = "com.tencent.map";//腾讯地图的包名

    public static final String GCJO2_LNG = "gd_lng";
    public static final String GCJO2_LAT = "gd_lat";
    public static final String DESTINATION = "destination";

    public static void openGaoDe(Context context, double latitude, double longitude) {
        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("androidamap://route?sourceApplication=" + MyApp.getInstance().getPackageName() + "&lat=" + latitude + "&lon=" + longitude + "&dev=0"));
        intent.setPackage("com.autonavi.minimap");
        context.startActivity(intent);
    }

    public static void openBaidu(Context context, MarkerOptions markerOption) {
        Intent i1 = new Intent();
        double[] position = GPSUtil.gcj02_To_Bd09(markerOption.getPosition().latitude, markerOption.getPosition().longitude);
        i1.setData(Uri.parse("baidumap://map/direction?location=" + position[0] + "," + position[1]));
        context.startActivity(i1);
    }

    /**
     * 检测地图应用是否安装
     *
     * @param context
     * @param packagename
     * @return
     */
    public static boolean checkMapAppsIsExist(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    public static final String[] MAP_PACKAGES = {BAIDU_PKG, GAODE_PKG, TENCENT_PKG};
    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param packageNames 可变参数 String[]
     * @return 目标软件中已安装的列表
     */
    public static List<String> checkInstalledPackage(Context sContext, String... packageNames) {

        //获取packageManager
        final PackageManager packageManager = sContext.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储
        List<String> newPackageNames = new ArrayList<>();
        int count = packageNames.length;

        if (packageInfos != null && packageInfos.size() > 0) {

            outermost:for (String packageName : packageNames) {
                for (int i = 0; i < packageInfos.size(); i++) {
                    String packageInfo = packageInfos.get(i).packageName;
                    if (packageInfo.contains(packageName)) {
                        newPackageNames.add(packageName);
                        if (newPackageNames.size() == count) {
                            break outermost;//这里使用了循环标记，跳出外层循环
                        }
                    }
                }
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return newPackageNames;
    }
}
