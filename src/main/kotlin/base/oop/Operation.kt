// 操作符定义, 一个普通的类怎么使用各种操作符，
// 需要定义对应的函数则对于的操作符可用，例如
// plus       函数对应 +
// plusAssign 函数对应 +=
// minus      函数对应 -
// times      函数对应 *
// div        函数对应 /
// rem        函数对应 %
// 具体有那些操作符可以使用可以查看 kotlin 的文档
// https://kotlinlang.org/docs/reference/operator-overloading.html
//

fun main() {
  val comple1 = Comple(1.0, 2.0)
  val comple2 = Comple(3.0, 4.0)
  val comple3 = comple1 + comple2
  println(comple3.real)
  println(comple3.imaginary)
  val comple4 = comple1 / comple2
  println(comple4.real)
  println(comple4.imaginary)
}

class Comple(
  val real: Double, val imaginary: Double
) {
  // 重写了 + 操作符
  operator fun plus(other: Comple): Comple = Comple(real + other.real, imaginary + other.imaginary)

  // 重写了 / 操作符号
  operator fun div(other: Comple): Comple {
    val real = (real * other.real + imaginary * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary)
    val imaginary = (imaginary * other.real - real * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary)
    return Comple(real, imaginary)
  }
}
