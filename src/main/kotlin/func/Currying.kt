package func

import java.io.OutputStream
import java.nio.charset.Charset

// 柯里化
fun log(tag: String, target: OutputStream, message: Any?) {
  target.write("$tag $message\n".toByteArray())
}

// fun log(tag: String) = fun(target: OutputStream) = fun(message: Any?) = target.write("$tag $message\n".toByteArray())

// 通用的柯里化的写法
fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried() = fun(p1: P1) = fun(p2: P2) = fun(p3: P3) = this(p1, p2, p3)

fun curry() {
  log("berry", System.out, "hello world")
  ::log.curried()("berry")(System.out)("hello world")
}

// 偏函数
// 一个多参数函数柯里化之后得到的函数在传入部分值
// 之后得到还是一个函数，这样的函数叫作偏函数

fun partial() {
  // 获取偏函数
  val consoleWithTag = ::log.curried()("berry")(System.out)
  // 调用偏函数
  consoleWithTag("HelloAgain, Again")
  // 定制多样的偏函数(定义任意位置的偏函数)
  makeStringFromGbkBytes("我是中国人".toByteArray(charset("GBK")))
}

val makeString = fun (byteArray: ByteArray, charset: Charset): String = String(byteArray, charset)

val makeStringFromGbkBytes = makeString.partialSecond(charset("GBK"))

fun <P1, P2, R> Function2<P1, P2, R>.partialFirst(p1: P1) = fun(p2: P2) = this(p1, p2)

fun <P1, P2, R> Function2<P1, P2, R>.partialSecond(p2: P2) = fun(p1: P1) = this(p1, p2)
