package advance
// 介绍一下 kotlin 中的 lambda
//
//
// 由于 kotlin 本身就是函数编程

fun lambda() {

  // 申明
  declare()

  // 定义一个匿名的函数
  fun() {
    println("hello world")
  }

  // 做类型扩展函数
  match(1, { println("高级") })
}

// 函数类型，可以是扩展函数
// fun match(callback: Int.() -> Unit) {
//   10.callback()
// }

// 类型通过对象参数传递进去
fun <T> match(re: T, callback: T.() -> Unit) {
  re.callback()
}

// 对于扩展函数，我们怎样控制它的泛型参数
// fun <T> T.match(callback: T.() -> Unit) {
//   this.callback()
// }

// lambda 的声明
fun declare() {
  // 直接定义一个函数，赋值给一个变量
  val func: (a: Int) -> Int = { a ->
    // 最后一行自动识别为整个表达式的返回值
    a
  }

  println(func(10))
  // 如果没有参数可以简写, 或者只有一个参数的时候，可以
  // 使用默认的参数名, it, 这时候参数名称也可以不写

  // 使用定义好的函数作为参数
  println(work(10, func))

  // 直接在传递参数的时候使用匿名函数,
  // 可以使用默认的名称 it, 这时候 lambda 可以省略参数
  println(work(20, { it -> it + 3 }))

  // 函数作为另一个函数的最后一个参数的时候，可以将函数体提取到外层
  println(work(1) { 20 })
}

// 以一个 函数作为参数
fun work(a: Int, f: (x: Int) -> Int): Int {
  return f(a) + a
}
