package me.xiaocao.demo.base.utils

import android.graphics.Typeface
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import me.xiaocao.demo.R

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/6 0006 10:13
 */
class TabLayoutUtil {
    companion object {
        fun initTabLayout(mTabLayout: TabLayout, mTitles: List<String>, mSelectedColor:Int, mNormalColor:Int, onTabSelectedListener: OnTabSelectedListener?) {
            mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) {

                }

                override fun onTabUnselected(mTab: TabLayout.Tab?) {
                    if (mTab != null && mTab.customView != null) {
                        val title: AppCompatTextView =
                            mTab.customView!!.findViewById(R.id.mTabChild)
                        title.run {
                            textSize = 16F
                            setTextColor(ContextCompat.getColor(mTabLayout.context, mNormalColor))
                            typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
                        }
                    }
                }

                override fun onTabSelected(mTab: TabLayout.Tab?) {
                    if (mTab != null && mTab.customView != null) {
                        val title: AppCompatTextView =
                            mTab.customView!!.findViewById(R.id.mTabChild)
                        title.run {
                            title.run {
                                textSize = 20F
                                setTextColor(
                                    ContextCompat.getColor(
                                        mTabLayout.context,
                                        mSelectedColor
                                    )
                                )
                                typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                            }
                        }
                    }
                    onTabSelectedListener?.onTabSelected(mTab)
                }
            })
            for (i in 0 until mTitles.size) {
                val mTab = mTabLayout.getTabAt(i)
                mTab?.setCustomView(R.layout.layout_tab_child)
                val title: AppCompatTextView = mTab?.customView!!.findViewById(R.id.mTabChild)
                title.text = mTitles[i]
                if (i == 0) {
                    title.run {
                        textSize = 20F
                        setTextColor(
                            ContextCompat.getColor(
                                mTabLayout.context,
                                mSelectedColor
                            )
                        )
                        typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    }
                } else {
                    title.run {
                        textSize = 16F
                        setTextColor(
                            ContextCompat.getColor(
                                mTabLayout.context,
                                mNormalColor
                            )
                        )
                        typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
                    }
                }
            }
        }
    }

    interface OnTabSelectedListener{
        fun onTabSelected(mTab: TabLayout.Tab?)
    }
}