package func

/**
 * 由于 reduce 没有初始值可以用，所以我们可以使用 Fold
 *
 */

fun funFold() {
  //
  // fold 作为一个可以指定初始值的 reduce
  //
  //
  (0..10).fold(5) { acc, i -> acc + i }
  //
  // 使用 fold 拼接字符串
  //
  // 传入一个初始的 StringBuilder, 这时候 acc 第一次跌代的初始值就是传入的
  // 这个 StringBuilder
  //
  (0..10).fold(StringBuilder()) { acc, i ->
    acc.append(i).append(",")
  }
}
