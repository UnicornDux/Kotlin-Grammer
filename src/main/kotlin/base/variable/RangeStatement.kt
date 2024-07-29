package base.variable

// 使用区间
fun range() {
  println("-----------range---------------")

  // 闭区间
  val range1: CharRange = ('a'..'k')

  // 左闭右包
  val range2: IntRange = (1 until 10)

  // 闭区间 的降序
  val range3: IntProgression = (10 downTo 1 step 2)

  // 区间可以指定步长
  val range4: IntProgression = (1..10 step 2)

  // 判断区间是否为空
  println(range3.isEmpty())
  // 步长
  println(range3.step)

  // 判断某个数是否在区间中
  val a = 13
  println(a in range2)
  println("range1 contains 20: ${range1.contains('o')}")

  for (value in range3) {
    print(" " + value)
  }
  println()
}
