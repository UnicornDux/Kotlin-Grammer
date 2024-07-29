package func

//
//  reduce 用来聚合，累计
//
fun funcReduce() {
  //
  // 使用 reduce 来计算[0.10] 区间的和
  val sum = (0..10).reduce { acc, i -> acc + i }
  println("[0..10].reduce = $sum")

  // 阶乘法
  (0..10).forEach(::factorial)
  //
  //
}

// 计算阶乘函数
fun factorial(n: Int): Int {
  if (n == 0) return 1
  return (1..n).reduce { acc, i -> acc * i }
}
