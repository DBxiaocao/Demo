package me.xiaocao.demo.tabscroll

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/24 0024 13:28
 */
data class ItemBean(val title: String, val viewType: Int) {
    companion object {
        const val TYPE_TITLE = 0 //文字title
        const val TYPE_RECOMMEND = 1 //推荐套餐
        const val TYPE_FOOD = 2 //美食
        const val TYPE_VIEW = 3 // 景色
        const val TYPE_ROOM = 4// 住宿
        const val TYPE_EVALUATION=5 //评价
    }
}