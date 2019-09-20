package me.xiaocao.demo.tagview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tag_view.*
import me.xiaocao.demo.R
import me.xiaocao.demo.base.BaseActivity

class TagViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_view)

        val mItems=ArrayList<TagBean>()
        mItems.add(TagBean("Android"))
        mItems.add(TagBean("IOS"))
        mItems.add(TagBean("JAVA"))
        mItems.add(TagBean("Kotlin"))
        mItems.add(TagBean("PHP"))
        mItems.add(TagBean("Swift"))
        mItems.add(TagBean("Rx系列"))
        mItems.add(TagBean("计算机信息"))
        mItems.add(TagBean("软件工程"))
        mItems.add(TagBean("DB"))
        mTagView.setItems(mItems)
    }
}
