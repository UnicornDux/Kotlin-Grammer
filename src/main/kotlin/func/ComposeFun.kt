package func

// 函数的复合, 类似 f(g(x)) | g(f(x))  的形式
// 当多个函数经常组合使用的时候，可以组合为一个新的函数来使用
//

val add5 = { i: Int -> i + 5 }
val mul2 = { i: Int -> i * 2 }

fun run() {
  println(mul2(add5(10)))
  println(add5(mul2(10)))

  val add5ThenMul2 = add5 andThen mul2
  val add5ComposeMul2 = add5 compose mul2
  println(add5ThenMul2(10))
  println(add5ComposeMul2(10))
}

infix fun <P1, P2, R> Function1<P1, P2>.andThen(function: Function1<P2, R>): Function1<P1, R> {
  return fun (p1: P1): R {
    return function.invoke(this.invoke(p1))
  }
}

infix fun <P1, P2, R> Function1<P2, R>.compose(function: Function1<P1, P2>): Function1<P1, R> {
  return fun(p1: P1): R {
    return this.invoke(function.invoke(p1))
  }
}
