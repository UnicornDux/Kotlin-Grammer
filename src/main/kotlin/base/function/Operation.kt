package base.function
// 操作符重载
//   kotlin 中每一个运算符都是一个函数方法, 这些函数都是可以通过对应类型重载，从而覆盖默认的行为
//    > +  :: plus(other: T) T
//    > -  :: minus(other: T) T
//    > *  ::

fun operation() {
  //  为自定义类型重写操作符
  val complex1 = Complex(10.2, 3.5)
  val complex2 = Complex(20.1, 2.1)

  val complexNew = complex1 + complex2
  println(complexNew)

  // 数组的展开操作符(sprend Operator)
  // 数组的展开符虽然也是操作符，但是不能被重写
  // 目前只有数组支持展开操作符，列表是不支持的
  add(*x)
}

class Complex(
    val real: Double,
    val imaginary: Double,
) {
  // 加法重载
  operator fun plus(other: Complex): Complex =
      Complex(real + other.real, imaginary + other.imaginary)

  operator fun plus(other: Int): Complex = Complex(real + other, imaginary)

  operator fun plus(other: Any): Int = real.toInt()

  override fun toString(): String = "($real, $imaginary)"
}

// 数组展开符号
// 在函数参数传递的时候，遇到可变参数的时候，可以使用数组展开符
// 将一个数组展开传入到函数中

fun add(vararg arr: Int) {
  arr.reduce { acc, cur -> acc + cur }
}

val x = intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 8, 7)
