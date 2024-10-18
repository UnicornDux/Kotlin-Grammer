package func

import kotlin.arrayOf

/**
 * 由于 reduce 没有初始值可以用，所以我们可以使用 Fold
 *
 */

fun funFold() {
  //
  // fold 作为一个可以指定初始值的 reduce
  //
  (0..10).fold(5) { acc, i -> acc + i }
  //
  // 使用 fold 拼接字符串
  //
  // 传入一个初始的 StringBuilder, 这时候 acc 第一次跌代的初始值就是传入的
  // 这个 StringBuilder
  //
  //
  // fold 的变体
  //  1. foldIndexed(initial: R, operation(int, R, T) -> R)
  //  2. foldRight(initial: R, operation(T, R) -> R)  // 操作的元素倒置过来, 参数也倒置过来
  //  3. foldRightIndexed(initial: R, operartion(int, T, R) -> R)
  //
  (0..10).fold(StringBuilder()) { acc, i ->
    acc.append(i).append(",")
  }
  //
  //
  val os = arrayOf("windows", "linux", "macos", "bsd")
      .foldRight(StringBuilder()) { i, acc ->
        acc.append(i).append(",")
      }
  println(os)
}
