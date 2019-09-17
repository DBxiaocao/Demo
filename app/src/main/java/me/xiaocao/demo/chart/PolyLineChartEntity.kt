package me.xiaocao.demo.chart

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import me.xiaocao.demo.R
import java.util.ArrayList

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/17 0017 14:41
 */
class PolyLineChartEntity(chart: BarLineChartBase<*>, entries: List<Entry>, valueColor: Int, mTextSize: Float) : BaseChartEntity<Entry>(chart, entries, valueColor, mTextSize) {

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

        mChart.isHighlightPerTapEnabled = false
        mChart.isHighlightPerDragEnabled = false
        mChart.legend.isEnabled = false
        mChart.setPinchZoom(false)
        mChart.setScaleEnabled(false)
        mChart.axisLeft.axisMaximum = 100f
        mChart.axisLeft.axisMinimum = 0f
        val leftAxis = mChart.axisLeft

        leftAxis.textColor = mValueColor
        leftAxis.textSize = mTextSize
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = false
        leftAxis.setDrawZeroLine(false)
        leftAxis.labelCount = 5
        leftAxis.axisLineWidth = 1f
        leftAxis.axisLineColor = Color.BLACK

        mChart.axisRight.isEnabled = false

        mChart.setDrawBorders(false)

    }

   public override fun setChartData() {

        val lineDataSets = ArrayList<ILineDataSet>()
        val dataSet = LineDataSet(mEntries, "")
        dataSet.setDrawCircles(false)
        dataSet.setDrawCircleHole(false)
        dataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        dataSet.setDrawFilled(false)
        dataSet.color = ContextCompat.getColor(mChart.context, R.color.mattress_sleep_00)
        dataSet.lineWidth = 1f
        dataSet.setDrawValues(false)
        dataSet.isVisible = true
        lineDataSets.add(dataSet)
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