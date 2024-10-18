package func

import java.io.BufferedReader
import java.io.FileReader

/**
 * use 函数的定义 (关闭资源等模板代码被进一步封装)
 * public inline fun <T: Closeable?, R> T.use(block: (T) -> R): R {
 *    val colosed = false
 *    try {
 *
 *    } catch(e Exception) {
 *      closed = true
 *      try {
 *        this?.close()
 *      }catch(x: Exception) {}
 *      ....
 *    }finally {
 *      if (!closed) {
 *        this?.close()
 *      }
 *    }
 * }
 */

fun useFunc() {
  // 资源在 use 作用域中自动关闭，但是
  // 使用 use 的对象必须要实现 Closeable 接口
  BufferedReader(FileReader("./temp/hello.txt")).use {
    var line: String?
    while (true) {
      line = it.readLine() ?: break
      println(line)
    }
  }
}
