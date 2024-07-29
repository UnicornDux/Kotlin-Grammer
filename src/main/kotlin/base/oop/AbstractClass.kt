package base.oop

// 抽象类
// 抽象类默认是 open 的, 抽象方法默认也是 open 的, 不需要写 open 关键字
abstract class H {
  // 抽象类中可以有普通的方法
  // 抽象类中的抽象方法没有方法体，当一个类继承了一个抽象类，
  // 需要实现这个抽象方法
  abstract fun click()

  // 实例方法不需要继承的子类来实现
  fun touch() {}
}

// 抽象类的继承需要实现抽象方法
class Child : H() {
  override fun click() {}
}
