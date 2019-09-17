package me.xiaocao.demo.chart

import android.graphics.Color
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/17 0017 14:18
 */

abstract class BaseChartEntity<T : Entry> {

    protected var mChart: BarLineChartBase<*>

    protected var mEntries: List<T>
    protected var mTextSize: Float = 0.toFloat()
    protected var mValueColor: Int = 0

    protected open val isDefaultY: Boolean
        get() = true

    protected open val isDefaultX: Boolean
        get() = true

    fun setEntries(mEntries: List<T>) {
        this.mEntries = mEntries
    }


    protected constructor(chart: BarLineChartBase<*>, entries: List<T>, valueColor: Int, mTextSize: Float) {
        this.mChart = chart
        this.mEntries = entries
        this.mValueColor = valueColor
        this.mTextSize = mTextSize
        this.initChart()
    }

    protected open fun initChart() {
        initProperties()
        setChartData()
        initLegend(Legend.LegendForm.LINE, mTextSize, mValueColor)
        if (isDefaultX) {
            initXAxis()
        }

        if (isDefaultY) {
            initLeftAxis()
        }

    }


    private fun initLeftAxis() {
        val leftAxis = mChart.axisLeft

        leftAxis.textColor = mValueColor
        leftAxis.textSize = mTextSize
        leftAxis.setDrawGridLines(false)
        leftAxis.isGranularityEnabled = false
        leftAxis.setDrawZeroLine(false)
        leftAxis.labelCount = 2
        leftAxis.axisLineWidth = 1f
        leftAxis.axisLineColor = Color.BLACK

        mChart.axisRight.isEnabled = false

    }

    private fun initXAxis() {
        val xAxis = mChart.xAxis

        xAxis.textSize = mTextSize
        xAxis.axisMinimum = 0f
        xAxis.textColor = mValueColor
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.setDrawLabels(true)
        xAxis.axisLineWidth = 1f
        xAxis.labelCount = 6
        xAxis.setDrawLimitLinesBehindData(true)
        xAxis.axisLineColor = Color.BLACK
        xAxis.setCenterAxisLabels(false)
        xAxis.axisMinimum = mChart.data.xMin
        xAxis.position = XAxis.XAxisPosition.BOTTOM

    }

    /**
     * 初始化属性信息
     */
    private fun initProperties() {
        mChart.setNoDataText("")
        mChart.description.isEnabled = false

        mChart.setTouchEnabled(true)
        mChart.dragDecelerationFrictionCoef = 0.9f
        mChart.isDragEnabled = true
        mChart.isScaleXEnabled = true
        mChart.setPinchZoom(false)
        mChart.setVisibleXRangeMaximum(6f)
        mChart.isScaleYEnabled = false
        mChart.setDrawGridBackground(false)
        mChart.isHighlightPerDragEnabled = false

        mChart.setPinchZoom(false)
    }

    /**
     *
     * 初始化Legend展示信息
     * @param form 样式
     * @param legendTextSize 文字大小
     * @param legendColor 颜色值
     */
    private fun initLegend(form: Legend.LegendForm, legendTextSize: Float, legendColor: Int) {
        val l = mChart.legend
        l.form = form
        l.textSize = legendTextSize
        l.textColor = legendColor
        updateLegendOrientation(Legend.LegendVerticalAlignment.BOTTOM, Legend.LegendHorizontalAlignment.RIGHT, Legend.LegendOrientation.HORIZONTAL)
    }

    /**
     *
     * 图例说明
     * @param vertical 垂直方向位置 默认底部
     * @param horizontal 水平方向位置 默认右边
     * @param orientation 显示方向 默认水平展示
     */

    private fun updateLegendOrientation(vertical: Legend.LegendVerticalAlignment, horizontal: Legend.LegendHorizontalAlignment, orientation: Legend.LegendOrientation) {
        val l = mChart.legend
        l.verticalAlignment = vertical
        l.horizontalAlignment = horizontal
        l.orientation = orientation
        l.setDrawInside(false)

    }

    /**
     * 图表value显示开关
     */
    fun toggleChartValue() {
        val sets = mChart.data.dataSets
        for (iSet in sets) {
            iSet.setDrawValues(!iSet.isDrawValuesEnabled)
        }
        mChart.invalidate()
    }

    fun setMarkView(markView: MarkerView) {
        markView.chartView = mChart
        mChart.marker = markView
        mChart.invalidate()
    }

    /**
     * x/ylabel显示样式
     * @param xvalueFromatter x
     * @param leftValueFromatter y
     */
    fun setAxisFormatter(xvalueFromatter: ValueFormatter, leftValueFromatter: ValueFormatter) {
        mChart.xAxis.valueFormatter = xvalueFromatter
        mChart.axisLeft.valueFormatter = leftValueFromatter
        mChart.invalidate()

    }

    protected abstract fun setChartData()

    /**
     * value显示格式设置
     * @param valueFormatter IValueFormatter
     */
    fun setDataValueFormatter(valueFormatter: ValueFormatter) {
        mChart.data.setValueFormatter(valueFormatter)
    }
}