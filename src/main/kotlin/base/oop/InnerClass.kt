package base.oop

fun inner() {
  // 访问内部类, 需要借助外部类的对象
  Q().I().ini()

  // 访问内部静态类可以直接使用，不需要借助外部类对象
  // 直接使用外部类的类名
  Q.L().lun()
}

class Q {
  fun out() {
    println("out")
  }
  // 内部类使用 inner 修饰
  // 这时候的类可以访问外部类的方法，属性
  // 类似于 java 的内部类
  inner class I() {
    fun ini() {
      // 可以访问外部类实例中的内容，
      // 访问的语法是 this@ParentClanssName.[field/method]
      this@Q.out()
    }
  }

  // 相当于 java 中的静态内部类
  class L() {
    fun lun() {
      // 这时候相当于 静态内部类，无法访问外部类的实例
      // 只能访问外部类中的静态变量或者方法，
      // this@Q.out()
    }
  }
}
