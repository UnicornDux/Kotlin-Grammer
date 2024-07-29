package base.method

fun method() {
  val k = K()
  val j = J()
  j.go()

  println(k.count2)
  println(j.count2)
}

open class K {
  // 1. 属性的重写
  // 属性要被重写，则需要使用 open 修饰
  open val count1 = 1
  open var count2 = 2

  // 方法默认的修饰符为 final, 是不能被继承的或者重写的
  // 当子类需要重写对应的方法的时候，需要使用 open 关键字
  // 这样的方法是可以被重写的
  open fun go() {}

  // 方法的修饰符为有以下几种
  // - private (子类不能访问)
  // - protected (子类可以访问)
  // - public (默认值)
  private fun lang() {}

  protected fun rust() {
    println("rust")
  }
}

class J : K() {
  // 重写类的方法
  override fun go() {
    println("go")
  }

  fun ko() {
    // 子类无法获取到这个方法
    // lang()
    // protected 方法是可以正常访问
    rust()
  }

  // 重写属性，
  //
  // > 实际上常量是重写了属性的 get 方法
  // > 实际上变量是重写了属性的 get/set 方法
  //
  // override val count1: Int
  //     get() = super.count1

  // 如果将同名的属性修改为变量，则会直接生成一个变量属性，覆盖掉父类的属性
  override var count1: Int = 2

  // 如果是重写变量，则只会重写父类中的属性 get/set 方法
  override var count2: Int
    get() = 10
    set(value) {}
}
