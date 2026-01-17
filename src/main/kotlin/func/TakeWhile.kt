package func

fun runTake(){
  useTakeWhile()
  uesTakeIf()
  useTakeUnless()
}

// 获取到第一个不满足条件的数据就停止
fun useTakeWhile() {
  listOf(1..10)
    .flatMap { it }
    .takeWhile { it % 3 != 0 }
    .forEach(::println)
}

private fun uesTakeIf() {
  val x = "hello".takeIf {
    // 匿名函数返回一个布尔值，函数本身根据返回值进行判断
    // 如果返回 true 就返回当前对象本身
    // 如果返回 false 就返回 null
    true
  }
  println("takeif with value: $x")
}

private fun useTakeUnless() {
  // 与 takeif 相反
  val x = "hello".takeUnless {
    // 匿名函数返回一个布尔值，函数本身根据返回值进行判断
    // 如果返回 true 就返回 null
    // 如果返回 false 就返回当前对象本身
    false
  }
  println("takeUnless with value: $x")
}
