package base.oop

fun oop() {
  val f = First()
  // 调用了 get 方法
  println("p current :" + f.a)
  // 调用了 set 方法
  f.a = 10
  println("f after: " + f.a)

  // 匿名内部类的使用
  val p = P()
  p.call(
    object : Callback {
      // 调用的时候实现这个类就可以了
      override fun finish() {
        println("finish")
      }
    },
  )
}

class P {
  fun call(callback: Callback) {
    callback.finish()
  }
}

// 内部类
// 匿名内部类，主要是通过接口来实现，类似于方法的参数
interface Callback {
  fun finish()
}

// 4. 访问修饰符
// > kotlin 中类
//   * 默认的修饰符是 public, 不是 java 中的 (package protected)
//   * 默认被 final 修饰,不能被继承,如果想要被继承,需要添加 open
// > private, public, protected 等都与 java 类似
// > 新增 internal 修饰, 主要用于控制类在模块之间的可见性
//   * 被 internal 修饰的类在模块外不可见，无法被外部使用
//
private class A

// 开放后可被继承
open class B

internal class C

// 缺省的情况等价于
// public final class D{}
class D
