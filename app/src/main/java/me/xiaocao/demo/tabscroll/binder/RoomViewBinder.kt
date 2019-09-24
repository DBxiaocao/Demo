package me.xiaocao.demo.tabscroll.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import me.xiaocao.demo.R
import me.xiaocao.demo.tabscroll.ItemBean

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/24 0024 13:34
 */
class RoomViewBinder : ItemViewBinder<ItemBean, RoomViewBinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val root = inflater.inflate(R.layout.item_list_view_type_room, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: ItemBean) {
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }
}