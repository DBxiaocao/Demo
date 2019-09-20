package me.xiaocao.demo.design

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.include_recyclerview.*
import me.xiaocao.demo.R
import me.xiaocao.demo.base.Helper
import me.xiaocao.demo.base.adapter.SimpleRecyclerAdapter

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/20 0020 10:36
 */
class PageFragment : Fragment() {

    companion object {
        fun newInstance(): PageFragment {
            val fragment = PageFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.include_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.run {
            adapter = SimpleRecyclerAdapter<String>()
                    .setDataList(Helper.getTest(50))
                    .setLayoutResId(R.layout.item_list_test)
                    .setOnBindViewListener { _, t, view ->
                        view.findViewById<AppCompatTextView>(R.id.text).text = t
                    }
        }
    }
}