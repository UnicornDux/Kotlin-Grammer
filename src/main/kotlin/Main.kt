import advance.coroutinue
import advance.lambda
import base.branch.branch
import base.function.function
import base.loop.loop
import base.method.method
import base.oop.data
import base.oop.enums
import base.oop.oop
import base.oop.seal
import base.variable.range
import base.variable.variable
import coroutines.coroutines
import app.calculate

fun main(args: Array<String>) {
  // Try adding program arguments via Run/Debug configuration.
  // Learn more about running applications:
  // https://www.jetbrains.com/help/idea/running-applications.html.
  println("Program arguments: ${args.joinToString()}")

  // coroutines()
  calculate()
}

fun app() {
  // calculate()
  //
  // base()
  // advance()
  //
  startServer()
}

// 高级特性
fun advance() {
  //
  coroutinue()

  lambda()
}

// 基础编码能力
fun base() {
  // 测试变量的声明
  variable()

  // 区间
  range()

  // 测试分支结构
  branch()

  // 循环
  loop()

  // 函数调用
  function()

  // 类与面向对象
  oop()

  // 枚举类
  enums()

  // 密封类
  seal()

  // 数据类
  data()

  // 方法
  method()
}
