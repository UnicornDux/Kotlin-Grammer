package base.face

// 接口的应用
fun inter() {}

//
// 类可以实现多个接口，同时可以继承抽象类，抽象类一般会放在前面
//
// 可以在构造函数中来加入，
// class A(override var code: Int) : Call, Back {
class A : Peek(), Call, Back {
  // 可以在类内部进行重写定义
  override var code: Int
    get() = TODO("Not yet implemented")
    set(value) {}

  // 当一个类实现了多个接口，并且这些接口中有签名相同的方法，
  // 虽然这些方法具有默认的实现，但是这时候从写这个方法来指定具体应该执行哪个接口中的实现，
  // 当然你可以在重写的方法中执行需要运行的任何代码
  override fun finish() {
    super<Call>.finish()
    super<Back>.finish()
  }
  // 实现抽象类的方法
  override fun pop() {}
}

// kotlin 接口默认的开放的，可以被实现
// 接口中的方法可以有默认的实现，就是可以有方法体
interface Call {

  // 接口中可以定义属性，但是只能定义，并不能赋值，
  // 需要在实现里面进行赋值，这与 Java 中 接口只能定义常量是不一样的
  var code: Int

  fun finish() {
    println("finish1")
  }
}

interface Back {
  fun finish() {
    println("finish2")
  }
}

abstract class Peek {
  abstract fun pop()
}
