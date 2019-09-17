package me.xiaocao.demo.chart

import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import me.xiaocao.demo.R
import java.util.ArrayList

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/17 0017 14:24
 */
class BarChartEntity(chart: BarLineChartBase<*>, entries: List<BarEntry>, valueColor: Int, mTextSize: Float) : BaseChartEntity<BarEntry>(chart, entries, valueColor, mTextSize) {

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
        mChart.xAxis.axisMinimum = 0f
        val week = arrayOf("", "一", "二", "三", "四", "五", "六", "日")
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
        mChart.axisLeft.axisMaximum = 100f
        mChart.axisLeft.axisMinimum = 0f
        mChart.axisLeft.labelCount = 5
        mChart.setPinchZoom(false)
        mChart.setScaleEnabled(false)

    }

    public override fun setChartData() {
        //睡眠质量小于60较差,75－60一般,90－75良好,100－90优秀
        val lineDataSets = ArrayList<IBarDataSet>()
        for (mEntry in mEntries) {
            val entries = ArrayList<BarEntry>()
            entries.add(mEntry)
            val dataSet = BarDataSet(entries, "")
            if (mEntry.y < 60) {
                dataSet.color = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_01)
                dataSet.valueTextColor = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_01)
            } else if (mEntry.y > 60 && mEntry.y < 75) {
                dataSet.color = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_02)
                dataSet.valueTextColor = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_02)
            } else if (mEntry.y > 75 && mEntry.y < 90) {
                dataSet.color = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_03)
                dataSet.valueTextColor = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_03)
            } else if (mEntry.y > 90 && mEntry.y < 100) {
                dataSet.color = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_04)
                dataSet.valueTextColor = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_04)
            } else {
                dataSet.color = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_04)
                dataSet.valueTextColor = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_04)
            }
            lineDataSets.add(dataSet)
        }
        val data = BarData(lineDataSets)
        data.setValueTextSize(mTextSize)
        data.barWidth = 0.5f
        mChart.data = data
    }


    fun setDrawValueAboveBar(aboveBar: Boolean) {
        (mChart as BarChart).setDrawValueAboveBar(aboveBar)
    }

    /**
     *
     * 设置bar宽度
     *
     * @param barWidth float
     */
    fun setBarWidth(barWidth: Float) {
        (mChart as BarChart).data.barWidth = barWidth
    }
}
