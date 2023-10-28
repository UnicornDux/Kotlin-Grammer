package base.oop

fun data() {
    val info = Info("alex", 19)
    val infe = Info("alex", 29)
    println(info.toString())
    println(info.hashCode())
    println(info.equals(infe))
    println(info.copy("maria", 10).toString())
}

// 数据类
data class Info(private var name: String, var age: Int = 10) {
    // data class 内部生成的方法, 可以直接使用
    // copy()
    // toString()
    // hashCode()
    // equals()
}