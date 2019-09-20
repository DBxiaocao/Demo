package me.xiaocao.demo.design

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tab.*
import me.xiaocao.demo.R
import me.xiaocao.demo.base.adapter.BaseFragmentPagerAdapter
import me.xiaocao.demo.base.utils.TabLayoutUtil
import java.util.ArrayList

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/20 0020 10:27
 */
class TabFragment :Fragment(){

    companion object{
        fun newInstance(): TabFragment {
            val fragment = TabFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragments = ArrayList<Fragment>()
        val titles = ArrayList<String>()
        for (i in 1..10){
            titles.add("TAB$i")
            fragments.add(PageFragment.newInstance())
        }
        mViewPager.adapter = BaseFragmentPagerAdapter(childFragmentManager, titles, fragments)
        mTabLayout.setupWithViewPager(mViewPager)
        mTabLayoutIndicator.setupWithViewPager(mViewPager)
        TabLayoutUtil.initTabLayout(mTabLayout, titles,R.color.colorAccent,R.color.default_text_color,null)
        TabLayoutUtil.initTabLayout(mTabLayoutIndicator, titles,R.color.colorAccent,R.color.default_text_color,null)
    }
}