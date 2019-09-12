package me.xiaocao.demo.base.utils

import android.graphics.Bitmap
import android.view.View

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/11 0011 17:15
 */
class MapHelper {
    companion object{
        fun getViewBitmap(addViewContent: View): Bitmap {

            addViewContent.isDrawingCacheEnabled = true
            addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                ), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            addViewContent.layout(0, 0, addViewContent.measuredWidth, addViewContent.measuredHeight)

            addViewContent.buildDrawingCache()
            val cacheBitmap = addViewContent.drawingCache

            return Bitmap.createBitmap(cacheBitmap)
        }
    }
}