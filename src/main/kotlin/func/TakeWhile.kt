package func

fun runTake(){
  takeWhile()
}

// 获取到第一个不满足条件的数据就停止
fun takeWhile() {
  listOf(1..10)
    .flatMap { it }
    .takeWhile{ it % 3 != 0 }
    .forEach(::println)
}
