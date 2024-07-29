package base.oop

/**
 *  1. 属性的初始化尽量在构造方法中完成
 *  2. 无法在构造方法中初始化，尝试降级为局部变量
 *  3. 当需要使用到延迟初始化的时候
 *     var 修饰的变量可以使用 lateinit 修饰
 *     val 修饰的变量可以使用 by lazy 的方式
 *  4. 可空类型虽然可以使用 null 进行初始化，但是需要慎用
 */

fun classField() {
  // 延迟初始化
  val second = Second()
  println(second.work())
  second.three
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
  //
  // 延迟初始化 val 修饰的变量
  // by lazy 修饰的初始化值只有在变量在第一次执行的时候才会被初始化
  val three: Three by lazy {
    println("init three by lazy")
    Three()
  }

  fun work() {
    // 延迟到某个代码块或者方法中进行延迟初始化的操作
    println("Second init....")
    s = "hello world"
  }
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

// 可以在构造器中定义变量, 在构造器中使用
//
// val / var 关键字修饰的变量都是类的属性
//
class Three2(
  var b: Int = 1,
)

// 定义多个构造器，体现出来主构造与次构造函数之间的关系
class Three3(
  a: Int,
) {
  // 除了主构造函数，我们还可以构建多个次构造函数
  // 可以在次构造函数中处理逻辑，但是最终都会调用主构造函数。
  constructor(a: Int, b: Int) : this(a)
  constructor(a: Int, b: String) : this(a) {
    println("b:" + b)
  }
}

// -----------------------------------------------------------
//  在类的外部可以为类扩展成员，但是扩展的成员不能进行初始化，
//  需要提供对应的访问器才可以操作
//
//  val 只能提供一个访问器返回具体的值
val String.param: String
  get() = ""

// var 可以同时具有 get 和 set
var Int.zero: Int
  get() = 0
  set(value) {
    this.zero = value
  }
