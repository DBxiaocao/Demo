package me.xiaocao.demo.map

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.baidu.mapapi.map.BitmapDescriptorFactory
import com.baidu.mapapi.map.MapStatus
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MarkerOptions
import com.baidu.mapapi.model.LatLng
import kotlinx.android.synthetic.main.activity_baidu_map.*
import me.xiaocao.demo.R
import me.xiaocao.demo.base.BaseActivity
import me.xiaocao.demo.base.dialog.BottomListDialog
import me.xiaocao.demo.base.utils.GPSUtil
import me.xiaocao.demo.base.utils.MapHelper
import me.xiaocao.demo.base.utils.MapUtil
import java.util.ArrayList
/**
 * @author 　xiaocao
 * Description　　地图 导航 浮标
 * Date:　2019/9/12 001210:53　
 */
class BaiduMapActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baidu_map)
        btnNavigation.setOnClickListener {
            if (gpsItems.size == 0) {
                showMessage("暂未找到地图插件")
            } else {
                showMapsDialog()
            }
        }
        initBaiduMap()
        initMaps()
    }

    private fun initBaiduMap() {
        val location=LatLng(lat,lon)
        val markerView = LayoutInflater.from(mActivity).inflate(R.layout.view_marker_layout, null)
        val option1 = MarkerOptions()
                .position(location)
                .icon(BitmapDescriptorFactory.fromBitmap(MapHelper.getViewBitmap(markerView)))
        mMapView.map.addOverlay(option1)
        val builder = MapStatus.Builder().zoom(17f).target(location)
        mMapView.map.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))
    }

    private fun initMaps() {
        projectItems = MapUtil.checkInstalledPackage(mActivity, *MapUtil.MAP_PACKAGES)
        if (projectItems.isNotEmpty()) {
            for (item in projectItems) {
                when (item) {
                    MapUtil.BAIDU_PKG -> gpsItems.add("百度地图")
                    MapUtil.GAODE_PKG -> gpsItems.add("高德地图")
                    MapUtil.TENCENT_PKG -> gpsItems.add("腾讯地图")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }

    private val gpsItems = ArrayList<String>()
    private var projectItems: List<String> = ArrayList()
    //    31.2604562191,121.4681558075
    private var lat = 31.2604562191
    private var lon = 121.4681558075
    private var address="上海市上海市静安区中华新路619号"
    private fun showMapsDialog() {
        BottomListDialog()
                .setItems(gpsItems)
                .setOnItemClickListener { _, position ->
                    val intent: Intent
                    when (projectItems[position]) {
                        MapUtil.BAIDU_PKG -> {
                            intent = Intent()
                            intent.data = Uri.parse("baidumap://map/direction?destination=latlng:"
                                    + lat + ","
                                    + lon + "|name:" + address + // 终点
                                    "&mode=driving" + // 导航路线方式
                                    "&src=" + packageName)
                            startActivity(intent)
                        }
                        MapUtil.GAODE_PKG -> {
                            val stringBuffer = StringBuffer("androidamap://route?sourceApplication=").append("amap")
                            val latLng = GPSUtil.BD2GCJ(LatLng(lat, lon))
                            stringBuffer.append("&dlat=").append(latLng.latitude)
                                    .append("&dlon=").append(latLng.longitude)
                                    .append("&dname=").append(address)
                                    .append("&dev=").append(0)
                                    .append("&t=").append(0)
                            intent = Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()))
                            intent.setPackage("com.autonavi.minimap")
                            startActivity(intent)
                        }
                        MapUtil.TENCENT_PKG -> {
                            val endPoint = GPSUtil.BD2GCJ(LatLng(lat, lon))//坐标转换
                            val sb = StringBuffer("qqmap://map/routeplan?type=drive")
                                    .append("&tocoord=").append(endPoint.latitude).append(",").append(endPoint.longitude).append("&to=$address")
                            intent = Intent("android.intent.action.VIEW", Uri.parse(sb.toString()))
                            startActivity(intent)
                        }
                    }
                }
                .show(supportFragmentManager)
    }
}
