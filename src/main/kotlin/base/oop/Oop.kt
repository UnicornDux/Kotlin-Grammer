package base.oop

fun oop(){
    // 匿名内部类的使用
    val p = P()
    p.call(object : Callback {
        // 调用的时候实现这个类就可以了
        override fun finish() {
            println("finish")
        }
    })
}


class P (){
    fun call(callback: Callback) {
        callback.finish()
    }
}


// 内部类
// 匿名内部类，主要是通过接口来实现，类似于方法的参数
interface Callback {
   fun finish()
}
