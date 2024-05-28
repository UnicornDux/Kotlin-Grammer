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
      }
  )
}

class P() {
  fun call(callback: Callback) {
    callback.finish()
  }
}

// 内部类
// 匿名内部类，主要是通过接口来实现，类似于方法的参数
interface Callback {
  fun finish()
}

// 1. kotlin 中类中的字段是已经具备 get set 方法，
// 除非你需要覆盖这些默认的实现，则需要手动修改

class First {
  var a = 3
    // 使用在字段的下方的缩进来表示当前方法的 get/set 方法
    // 而不是使用具体的方法名称，使用的时候直接使用字段名即可
    // 使用 field 来表示当前的字段值
    get() = field
    set(value) {
      field = value
    }
}

// 2. 延迟初始化
class Second {
  // 延迟初始化，适用于复杂类型，基本类型不支持
  //
  lateinit var s: String

  // 以下都是错误的，延迟初始化后不能出现赋值语句
  // lateinit s:String = "hello"
  // lateinit s:String ?= null
  // lateinit s:String = null
}

// 3. 构造函数, 与类同时声明的时候，constuctor 可以省略
// > 当类名后没有括号的时候，
//    * 类里面没有其他构造的时候会有一个空的构造函数
//    * 当类里面有其他的构造器的时候，则没有空的构造函数了
// > 当我们在类里面和声明类的时候都使用了构造器
//    * 类声明的时候被称为主构造器
//    * 类中的其他构造构造器在执行的时候最终需要调用主构造器
//
// class Three constructor() {}
class Three {
  val a: Int
  // 初始化代码块可以初始化常量
  init {
    a = 1
  }
}

// 可以在构造器中定义变量
class Three_2(var b: Int = 1) {}

// 定义多个构造器，体现出来主构造与次构造函数之间的关系
class Three_3(a: Int) {

  // 除了主构造函数，我们还可以构建多个次构造函数
  // 可以在次构造函数中处理逻辑，但是最终都会调用主构造函数。
  constructor(a: Int, b: Int) : this(a)
  constructor(a: Int, b: String) : this(a) {
    println("b:" + b)
  }
}

// 4. 访问修饰符
// > kotlin 中类
//   * 默认的修饰符是 public, 不是 java 中的 (package protected)
//   * 默认被 final 修饰,不能被继承,如果想要被继承,需要添加 open
// > private, public, protected 等都与 java 类似
// > 新增 internal 修饰, 主要用于控制类在模块之间的可见性
//   * 被 internal 修饰的类在模块外不可见，无法被外部使用
//
private class A {}

// 开放后可被继承
open class B {}

internal class C {}

// 缺省的情况等价于
// public final class D{}
class D {}
