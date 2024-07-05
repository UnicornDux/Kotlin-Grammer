package advance

import kotlin.reflect.KFunction

// 引用

fun reference() {

  // 基于 KFunction 来获取
  val a: KFunction<Unit> = ::pock

  call(::pock)
  // call(a)

  // 如果需要使用类中的函数引用，则需要传入类的实例作为参数
  val fn = Fact::fn
  fn(Fact())
}

// 语法
// 只能引用，属性，方法，类
// 不能引用变量
fun pock() {}

fun call(callback: () -> Unit) {
  callback()
}

class Fact {
  val a = 1
  fun fn() {
    println(a)
  }
}
