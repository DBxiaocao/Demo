package me.xiaocao.demo.chart

import android.graphics.Color
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.activity_mpchart.*
import me.xiaocao.demo.R
import me.xiaocao.demo.base.BaseActivity

class MPChartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mpchart)
        val mHistogramItems = ArrayList<BarEntry>()
        for (i in 1 .. 7) {
            mHistogramItems.add(BarEntry(i.toFloat(),(0..100).random().toFloat()))
        }
        initBarChart(mHistogramItems)

        val mPolyLineItems=ArrayList<Entry>()
        for (i in 1..31){
            mPolyLineItems.add(BarEntry(i.toFloat(),(0..100).random().toFloat()))
        }
        initPolyLineChart(mPolyLineItems)
        val mPointLineItems=ArrayList<Entry>()
        for (i in 1..7){
            mPointLineItems.add(BarEntry(i.toFloat(),(1..2).random().toFloat()))
        }
        initPointLineChart(mPointLineItems)
    }

    private var mHistogramEntity: BarChartEntity? = null
    /**
     * 柱状图
     */
    private fun initBarChart(mHistogramItems: ArrayList<BarEntry>) {
        if (mHistogramBarChart.data != null)
            mHistogramBarChart.data.clearValues()
        if (mHistogramEntity == null) {
            mHistogramEntity = BarChartEntity(
                    mHistogramBarChart,
                    mHistogramItems,
                    Color.parseColor("#FF76ECCB"),
                    11f
            )
            mHistogramEntity?.setBarWidth(0.5f)
            mHistogramEntity?.setDrawValueAboveBar(true)
        } else {
            mHistogramEntity?.setEntries(mHistogramItems)
            mHistogramEntity?.setChartData()
        }
        mHistogramBarChart.invalidate()
    }

    private var mPolyLineEntity: PolyLineChartEntity? = null
    /**
     * 折线图
     */
    private fun initPolyLineChart( mPolyLineItems: ArrayList<Entry>) {
        if (mPolyLineEntity == null) {
            mPolyLineEntity = PolyLineChartEntity(
                    mPolyLineChart,
                    mPolyLineItems,
                    Color.parseColor("#FF76ECCB"),
                    11f
            )
        } else {
            mPolyLineChart.data.clearValues()
            mPolyLineEntity?.setEntries(mPolyLineItems)
            mPolyLineEntity?.setChartData()
        }
        setChartCount(mPolyLineItems,mPolyLineChart)
        mPolyLineChart.invalidate()
    }

    private fun setChartCount(mChartItems:ArrayList<Entry>, lineChart: LineChart){
        val mItems = ArrayList<String>()
        for (entry in mChartItems) {
            if (entry.x.toInt() % 2 == 1) {
                mItems.add(entry.x.toInt().toString())
            } else {
                mItems.add("")
            }
        }
        lineChart.xAxis.valueFormatter = object : IndexAxisValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {

                val index = value.toInt()
                return if (index < 0 || index >= mItems.size) {
                    ""
                } else mItems[index]
            }
        }
        lineChart.xAxis.labelCount = mChartItems.size+1
    }

    private var mPointLineEntity: PointLineChartEntity? = null
    /**
     * 点图
     */
    private fun initPointLineChart(mPointItems: ArrayList<Entry>) {
        if (mPointLineEntity == null) {
            mPointLineEntity = PointLineChartEntity(
                    mPointLineChart,
                    mPointItems,
                    Color.parseColor("#FF76ECCB"),
                    11f)
        } else {
            mPointLineChart.data.clearValues()
            mPointLineEntity?.setEntries(mPointItems)
            mPointLineEntity?.setChartData()
        }
        mPointLineChart.invalidate()
    }
}
