package func

/**
 * let 函数的定义
 * @kotlin.internal.InlineOnly
 * public inline fun <T, R> T.let(block: (T) -> R): R = block(this)
 */

data class Person (val name: String, val age: Int) {
  fun work() {
    println("${this.name} is work hard, relax less")
  }
}

fun useLet() {
  // 使用 let 可以结合 ? 操作符, 在当前对象不为空的情况下执行代码块中的逻辑
  // let 接收的是一个以当前对象作为参数的函数
  findPerson()?.let { p ->
    // 在代码块之内如果需要使用对象的方法，需要借助参数对象
    p.work()
  }
}

fun findPerson(): Person? {
  val person = Person("Alex", 35)
  return person
}
