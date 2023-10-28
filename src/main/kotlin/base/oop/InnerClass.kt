package base.oop


fun inner () {
}

class Q {
    fun out() {
        println("out")
    }
    // 内部类使用 inner 修饰
    // 这时候的类可以访问外部类的方法，属性
    // 类似于 java 的内部类
    inner class I(){
        fun ini(){
            this@Q.out();
        }
    }

    // 相当于 java 中的静态内部类
    class L(){
        fun lun(){
            // 这时候相当于 静态内部类，无法访问外部类的实例
            // this@P.out()
        }
    }
}
