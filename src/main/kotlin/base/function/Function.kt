package base.function

import kotlin.arrayOf

// 用于测试 kotlin 中的函数
//
// kotlin 中函数也是一种类型，一共列举了 Funtion1 .. Function22
//

fun function() {
  println("-----------------fun---------------")
  first()
  second()
  third("hello")
  four("hello")
  five("hello", 13)

  six("hello", "world", "kotlin")

  // 可以使用 * 对数组，列表等进行展开
  // 当使用指定参数传参的时候，就可以省略展开的操作
  six(*arrayOf("1", "2", "3"))

  seven("hello")
  seven("hello", 100)


  // 指定传入参数的参数名称
  point(a = 10, c = 20, b = { println(it) })

  // ------------------------------
  //  调用一个参数为 lambda 表达式的函数时
  // 当这个函数只有一个函数表达式参数,
  // lambdaArgs({})
  // -| 可以将函数表达式后置 -> lambda(){}
  // -| 这时原函数参数括号省略 -> lambda {}
  lambdaArgs { args -> "$args -- $args" }

  // 匿名函数的使用
  peek(10)
  // peek 是一个变量，因为重写了操作符号，实际上相当于调用了 invoke 方法
  peek.invoke(10)
}

/**
 * - fun 开头标识一个函数, 可定义函数的结构为 >> fun funName(args:Type[,arg1:Type])[:returnType] {} <<
 * - 函数参数可以没有，可以为多个，定义可变参数, 参数可赋默认值
 * - 函数的返回值也是可选的，当没有时默认返回的时 Unit 类型
 */

fun first() {
  // 这是有一个没有参数,没有返回值的函数
}

// 这是一个具有返回值的函数
fun second(): String {
  // return "hello"
  return "hello"
}

// 单一个函数没有返回值的时候，则默认返回的 Unit 类型

// 这是一个具有一个参数的函数
fun third(args: String) {}

// 既有参数也有返回值
fun four(args: String): String = args

// 当我们的函数体只有一个表达式的时候, 可以简写为这样的方式
// 省略了 {}  和 return 关键字, 返回值类型
fun hello(x: Int) = x

// 多种类型的参数
fun five(
  arg1: String,
  arg2: Int,
) {}

// 可变参数
fun six(vararg args: String) {
  println(args::class)
  println(args.contentToString())
  for (i in args.indices) {
    println(args[i])
  }
}

// 参数默认值,
//
fun seven(
  arg1: String,
  arg2: Int = 12,
) {}

// 可以通过参数名称，对传入的参数进行指定,
// > 当没有调整参数的顺序的时候可以指定某些参数的参数名,某些不指定
// > 当调整了参数的顺序，则所有的参数都需要进行参数名的指定
// 调用的时候可以参考
// point(a=10,b=1,c={ println(it)})
fun point(
  a: Int,
  b: (Int) -> Unit,
  c: Int = 2,
) {
  b(a + c)
}

/**
 * kotlin 作为一门函数式的编程语言，
 * - lambda 表达式作为另一个函数的参数是非常常见的情况
 */

// 输入一个函数作为当前函数的参数
fun lambdaArgs(args: (String) -> String) {
  val random = Math.random() * 10
  if (random < 5) {
    args(random.toString())
  } else {
    println("not execute")
  }
}

// 函数可以被赋值给变量，这就变成了一个匿名的函数，
// 实际在使用的时候，其实是在调用变量的 invoke 方法
val peek = fun(a:Int): Int {
  return 10 + a
}
