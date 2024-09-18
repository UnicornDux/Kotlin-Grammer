package regex

import kotlin.io.println

// kotlin 中原本支持 java 中的 Pattern
// kotlin 还封装了自己的 Regex

fun regex() {
  val sentence = "Hello, This is my telephone number 001-100040"
  Regex(""".*(\d{3}-\d{6}).*""")
    .findAll(sentence)
    .toList()
    .flatMap(MatchResult::groupValues)
    .forEach(::println)
}
