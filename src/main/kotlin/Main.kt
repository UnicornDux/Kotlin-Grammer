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
import coroutines.coroutine
import coroutines.runWork
import coroutines.show
import app.calculate
import app.summary
import http.httpServer
import func.funFold
import func.runFilter
import func.useApply
import func.useLet
import func.runTake
import func.run
import func.curry
import func.partial
import dsl.dom
import dsl.domdsl
import coroutine.seq
import regex.regex
import flowx.useFlow
import flowx.useStateFlow
import flowx.sharedFlow

fun main(args: Array<String>) {
  // Try adding program arguments via Run/Debug configuration.
  // Learn more about running applications:
  // https://www.jetbrains.com/help/idea/running-applications.html.
  println("Program arguments: ${args.joinToString()}")
  coFlow()
}

fun coFlow() {
  // useFlow()
  // useStateFlow()
  sharedFlow()
}

fun dsl() {
  dom()
  domdsl()
}

fun app() {
  calculate()
  summary()
  // 讲解协程概念的 swing 窗口程序
  show()
  // 正则表达式
  regex()
}

// 服务端
fun server() {
  httpServer()
}

// 高级特性
fun advance() {
  // 协程
  coroutine()
  runWork()

  // 表达式
  lambda()

  funFold()
  // 高阶函数
  runFilter()
  runTake()

  useLet()
  useApply()

  func.run()

  // 柯里化
  func.curry()
  // 偏函数
  func.partial()

  // kotlin 内置的序列
  seq()
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
