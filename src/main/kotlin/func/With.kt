package func

import java.io.BufferedReader
import java.io.FileReader
import kotlin.io.readLine

/**
 * @kotlin.internal.InlineOnly public inline func <T, R> with(receiver: T, block: T.() -> R): R =
 * receiver.block()
 */
fun useWith() {
  val br = BufferedReader(FileReader("./temp/hello.txt"))
  with(br) {
    // 在 with 包罗的对象就是当前对象实例的作用域，即可以直接调用
    // 这个对象内的所有可用方法
    var line: String?
    while (true) {
      line = readLine() ?: break
      print(line)
    }
    close()
  }
}
