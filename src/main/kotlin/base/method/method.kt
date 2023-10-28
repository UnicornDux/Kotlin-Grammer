package base.method

fun method(){
    val j = J()
    j.go()
}


open class K {

    // 方法默认的修饰符为 final, 是不能被继承的或者重写的
    // 当子类需要重写对应的方法的时候，需要使用 open 关键字
    // 这样的方法是可以被重写的
    open fun go(){}

    // 方法的修饰符为有以下集中
    // - private (子类不能访问)
    // - protected (子类可以访问)
    // - public (默认值)
    private fun lang() {}

    protected fun rust(){
       println("rust")
    }
}

class J : K() {

    // 重写类的方法
    override fun go(){
        println("go")
    }

    fun ko(){
        // 子类无法获取到这个方法
        // lang()
        // protected 方法是可以正常访问
        rust()
    }
}