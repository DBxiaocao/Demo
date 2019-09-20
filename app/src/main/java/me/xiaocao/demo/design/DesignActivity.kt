package me.xiaocao.demo.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.xiaocao.demo.R
import me.xiaocao.demo.base.BaseActivity

class DesignActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_design)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, TabFragment.newInstance())
                .commit()
    }
}
