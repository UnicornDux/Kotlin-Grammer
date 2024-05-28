// Kotlin 内置的类
//
//
// Any 类型是一切类的父类，相当于 java 中的 Object
//   > 内部为所有的类提供 hash, equals, toString 方法
//
// Nothing
//
//
// null

fun any() {
  A().sout()
  A().plus()
}

class A {
  // 使用 ? 来赋值一个null
  var b: Int? = null

  // lateinit 表示稍后初始化, 用于告诉编译器先不检查数据是否初始化
  // 只能用于复杂数据类型，不能用于基本类型
  lateinit var c: String

  fun sout() {
    println(b)
  }

  fun plus() {
    // c.plus("s")
    //
    // b.plus(10) // 警告 b 可能为空
    // 对 b 强制关闭空检查,
    b!!.plus(10)
  }
}
