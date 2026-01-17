package regex

import kotlin.io.println

// kotlin 中原本支持 java 中的 Pattern
// kotlin 还封装了自己的 Regex

fun regex() {
  // base()
  substr()
  replaceRegex()
}

fun base() {
  val sentence = "Hello, This is my telephone number 001-100040"
  Regex(""".*(\d{3}-\d{6}).*""")
    .findAll(sentence)
    .toList()
    .flatMap(MatchResult::groupValues)
    .forEach(::println)
}

fun substr() {
  val x = "Hello Darwin works fine"
  println(x.substring(6, 23)) // 前包后不包
  println(x.substring(6..22)) // 前后闭包
  println(x.substring(6 until 23)) // 前包后不包
}

fun replaceRegex() {
  val pwd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

  val new = pwd.replace(Regex("[AKMNO]")) {
    // 每一个满足正则的字符都会进入到后续的回调函数中
    println(it.value)
    when (it.value) {
      "A" -> "a"
      "K" -> "k"
      "M" -> "m"
      "N" -> "n"
      "O" -> "o"
      else -> "😄"
    }
  }
  println(new)
}
