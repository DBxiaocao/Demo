package me.xiaocao.demo.tabscroll.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import me.xiaocao.demo.R
import me.xiaocao.demo.tabscroll.ItemBean
import me.xiaocao.demo.tagview.FlexBoxTagView
import me.xiaocao.demo.tagview.TagBean

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/24 0024 13:34
 */
class TagViewBinder : ItemViewBinder<ItemBean, TagViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_list_view_type_evalutation_tag, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: ItemBean) {
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
        holder.mTagView.setItems(mItems)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mTagView:FlexBoxTagView=itemView.findViewById(R.id.mTagView)
    }
}