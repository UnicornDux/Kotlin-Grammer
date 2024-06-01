package base.oop

fun enums() {
  println(Menu.HOME)
  println(Menu.BUFFER.alias)
}

// 枚举可以实现接口
interface Operation {
  fun allocate()
}

// 枚举类型成为了类修饰符, 这里的 枚举的用法与 Java 中并没有什么不同
// 枚举值就是当前枚举类的静态实例对象
//  1.枚举可以定义抽象方法, 则它的所有实例对象必须要实现这样的方法
//  2.枚举可以事先接口，则它的所有实例对象必须要实现接口中的方法
enum class Menu(val alias: String, address: String) : Operation {
  HOME("home", "alibaba") {
    override fun allocate() {
      println(this.alias)
    }
  },
  BUFFER("buffer", "alix") {
    override fun allocate() {
      println(this.alias)
    }
  }
}
