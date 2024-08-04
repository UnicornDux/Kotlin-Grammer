package base.oop

/**
 * 方法重载：方法的签名主要包含了 :  方法名(参数列表)   __方法的返回值不计算在方法的签名中__
 *           在 java 和 kotlin 中不能定义方法名和参数都相同，但是返回值不同的函数(他们被认为是相同的方法)
 *
 * 从理论上来说，所有的重载方法都可以使用默认参数来完成替代
 */
fun over() {
  val o = OverloadFunc()
  o.runLoader(10, "overload")
}

class OverloadFunc {
  fun runLoader() {
    println("empty args")
  }

  // fun runLoader(a: Int) {
  //   println("$a")
  // }

  // 在一个方法中使用了默认参数的时候，在 java 中是没有默认参数的概念，那么该怎么调用呢
  // > 1. 需要在 kotlin 编写的代码中使用 @JvmOverloads 注解标注
  // > 2. 在 java 代码中即可不传入这个默认参数完成调用
  @JvmOverloads
  fun runLoader(a: Int, b: String = "hello world") {
    println("$a: $b")
  }
}
