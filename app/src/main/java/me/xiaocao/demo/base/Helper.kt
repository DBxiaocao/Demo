package me.xiaocao.demo.base

/**
 * @author 　xiaocao
 * Description　　
 * Date:2019/9/20 0020 10:44
 */
class Helper {
    companion object{
        fun getTest(max:Int):ArrayList<String>{
            val mItems=ArrayList<String>()
            for (i in 0..max){
                mItems.add(i.toString())
            }
            return mItems
        }
    }
}