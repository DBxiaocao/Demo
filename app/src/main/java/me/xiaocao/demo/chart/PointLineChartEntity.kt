package me.xiaocao.demo.chart

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import me.xiaocao.demo.R
import java.util.ArrayList

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/17 0017 14:50
 */
class PointLineChartEntity(chart: BarLineChartBase<*>, entries: List<Entry>, valueColor: Int, mTextSize: Float) : BaseChartEntity<Entry>(chart, entries, valueColor, mTextSize) {

    override val isDefaultY: Boolean
        get() = false

    override fun initChart() {
        super.initChart()
        mChart.axisLeft.setDrawGridLines(true)
        mChart.axisLeft.enableGridDashedLine(10f, 15f, 0f)
        mChart.axisLeft.gridLineWidth = 0.5f
        mChart.axisLeft.gridColor = ContextCompat.getColor(mChart.context, R.color.default_hint_color)
        mChart.axisLeft.setDrawZeroLine(false)
        mChart.axisRight.setDrawZeroLine(false)
        mChart.axisRight.zeroLineWidth = 0f
        mChart.axisLeft.zeroLineWidth = 0f
        mChart.axisLeft.setDrawAxisLine(false)
        mChart.xAxis.setDrawAxisLine(false)
        val left = arrayOf("", "异常", "正常")
        val week = arrayOf("", "一", "二", "三", "四", "五", "六", "日", "")
        mChart.xAxis.valueFormatter = object : IndexAxisValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {

                val index = value.toInt()
                return if (index < 0 || index >= week.size) {
                    ""
                } else week[index]
            }
        }
        mChart.isHighlightPerTapEnabled = false
        mChart.isHighlightPerDragEnabled = false
        mChart.legend.isEnabled = false
        mChart.setPinchZoom(false)
        mChart.setScaleEnabled(false)

        mChart.axisLeft.valueFormatter = object : IndexAxisValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                val index = value.toInt()
                return if (index < 0 || index >= left.size) {
                    ""
                } else left[index]
            }
        }
        mChart.axisLeft.axisMaximum = 2f
        mChart.axisLeft.axisMinimum = 0f
        val leftAxis = mChart.axisLeft

        leftAxis.textColor = mValueColor
        leftAxis.textSize = mTextSize
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = false
        leftAxis.setDrawZeroLine(false)
        leftAxis.labelCount = 2
        leftAxis.axisLineWidth = 1f
        leftAxis.axisLineColor = Color.BLACK

        mChart.axisRight.isEnabled = false

        mChart.setDrawBorders(false)
    }

    public override fun setChartData() {
        val lineDataSets = ArrayList<ILineDataSet>()
        for (mEntry in mEntries) {
            val entries = ArrayList<Entry>()
            entries.add(mEntry)
            val dataSet = LineDataSet(entries, "")
            dataSet.setDrawCircles(true)
            dataSet.setDrawCircleHole(false)
            dataSet.color = ContextCompat.getColor(mChart.context, android.R.color.transparent)
            if (mEntry.y == 2f) {//正常
                dataSet.setCircleColor(ContextCompat.getColor(mChart.context, R.color.mattress_sleep_00))
            } else {//异常
                dataSet.setCircleColor(ContextCompat.getColor(mChart.context, R.color.mattress_sleep_01))
            }
            dataSet.lineWidth = 1f
            dataSet.setDrawValues(false)
            lineDataSets.add(dataSet)
        }
        val data = LineData(lineDataSets)
        data.setValueTextSize(mTextSize)
        data.setValueTextColor(mValueColor)
        // set data
        mChart.data=data
    }


    /**
     * 不显示无数据的提示
     */
    fun setNotShowNoDataText() {
        mChart.clear()
        mChart.notifyDataSetChanged()
        mChart.setNoDataText("你还没有记录数据")
        mChart.setNoDataTextColor(Color.WHITE)
        mChart.invalidate()
    }
}