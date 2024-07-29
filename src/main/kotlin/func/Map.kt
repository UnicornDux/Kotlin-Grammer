package func

fun funcMap() {
  //
  // map  意味着将一种类型转换为另一种类型，然后使用一个新的集合重新收集这些元素
  //
  // public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {}
  //
  (0..19).map { it -> "NO. $it" }.forEach(::println)
  //
  // flatmap (展开多重集合的方式)
  // public inline fun <T, R> Iterable<T>.flatMap(transform(T) -> Iterable<R>): List<R> {}
  //
  listOf(1..10, 20..40, 60..80)
    .flatMap{it}
    .forEach(::println)

  listOf(1..10, 20..40, 60..80)
    .flatMap { iter ->
      // 当第一次展开的时候得到的是第一层集合的迭代器
      iter.map {
        // 第二层展开的时候得到的就是每一个元素了
        ("NO: $it")
      }
    }.forEach(::println)
}
