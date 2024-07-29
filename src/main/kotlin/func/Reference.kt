package func

/**
 *  > 高阶函数
 *
 *  > 函数引用
 *
 *  1. 纯函数的引用
 *  2. 扩展函数的引用
 *  3. 对象方法的引用
 *
 */

fun reference() {
  // forEach 接收一个 action 的函数,(只要能满足函数的签名都可以被传入使用)
  // ac
  //  纯函数的引用
  (0..10).forEach(::println)

  // 2. 类的的引用
  listOf("windows", "linux", "MacOs").filter(String::isNotEmpty)

  // 3. 对象实例的方法引用 (v1.1+ 开始支持)
  val pdfPrinter = PdfPrinter()
  listOf("windows", "linux", "MacOs").forEach(pdfPrinter::print)
}

class PdfPrinter {
  fun print(content: String) {
    println("Pdf: $content")
  }
}
