package app

import java.io.File
// 统计某个文件中的字符数量

fun summary() {
  val map = HashMap<Char, Int>()
  File("build.gradle.kts")
    .readText()
    .toCharArray()
    .filterNot(Char::isWhitespace)
    .forEach {
      val count = map[it]
      if (count == null) map[it] = 1 else map[it] = count + 1
    }
  map.forEach(::println)
}
