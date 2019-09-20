package me.xiaocao.demo.tagview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import me.xiaocao.demo.R
import me.xiaocao.demo.base.adapter.SimpleRecyclerAdapter
import me.xiaocao.demo.base.view.CommonShapeButton
import java.util.ArrayList

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/20 0020 14:59
 */
class FlexBoxTagView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private val mTagItems = ArrayList<ITagBean>()
    private val mAdapter = SimpleRecyclerAdapter<ITagBean>()

    init {
        initView(context)
    }

    private fun initView(mContext: Context) {
        LayoutInflater.from(mContext).inflate(R.layout.view_tag_layout, this)
        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        val manager = FlexboxLayoutManager(mContext)
        manager.flexWrap = FlexWrap.WRAP
        mAdapter.setDataList(mTagItems)
                .setLayoutResId(R.layout.item_flex_box_tag)
                .setOnBindViewListener { _, t, view ->
                    view.findViewById<CommonShapeButton>(R.id.mFlexTagButton).text = t.tag
                }
        mRecyclerView.run {
            layoutManager = manager
            adapter = mAdapter
        }
    }

    fun setItems(items: List<ITagBean>) {
        this.mTagItems.addAll(items)
        mAdapter.notifyDataSetChanged()
    }
}