package hooks

import advance.lambda
import app.calculate
import app.summary
import base.branch.branch
import base.function.function
import base.loop.loop
import base.method.method
import base.oop.data
import base.oop.enums
import base.oop.oop
import base.oop.runGeneric
import base.oop.seal
import base.variable.range
import base.variable.variable
import coroutine.seq
import coroutines.coroutine
import coroutines.runWork
import coroutines.show
import dsl.dom
import dsl.domdsl
import flowx.sharedFlow
import http.httpServer
import regex.regex

fun main(args: Array<String>) {
  // Try adding program arguments via Run/Debug configuration.
  // Learn more about running applications:
  // https://www.jetbrains.com/help/idea/running-applications.html.
  println("Program arguments: ${args.joinToString()}")
  // coFlow()
  // func.runTake()
  runGeneric()
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

  // kotlin 内置的序列
  seq()
}

fun utilitings() {
  func.useLet()
  func.useApply()
  func.run()
  func.runFunc()
  func.alsoFun()
  // 柯里化
  func.curry()
  // 偏函数
  func.partial()

  // 高阶函数
  func.runFilter()
  func.runTake()
  func.funFold()
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
