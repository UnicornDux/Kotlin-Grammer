package base.oop

fun seal() {
  println("--------------seal--------------------")
  val k = Kimi()

  //
  handler(k)
  handler(Ollam)

  // 模拟登录
  operation(MainIntent.Login("root", "123456"))
  operation(MainIntent.Logout)
}

// 密封类 (枚举和抽象类的结合)
// 本身作为一个类是可以实现接口，继承其他抽象类都是可以的
sealed class Tom : Pop {
  abstract fun a()
}

// 继承 Tom, 实现它的抽象方法
class Kimi : Tom() {
  override fun a() {
    println("a")
  }
  override fun pop() {}
}

// 单例继承密封
object Ollam : Tom() {

  override fun a() {
    println("ollam")
  }
  override fun pop() {}
}

fun handler(t: Tom) {

  when (t) {
    is Kimi -> println("handler by kimi")
    is Ollam -> println("handle by ollam")
  }
}

interface Pop {
  fun pop()
}

// 使用 类似 MVVM 架构的方式来模拟一个登录
// seal class 的使用场景

sealed class MainIntent {
  data class Login(val act: String, val pwd: String) : MainIntent()
  object Logout : MainIntent()
}

fun operation(mainIntent: MainIntent) {
  when (mainIntent) {
    is MainIntent.Login -> userLoginRequest(mainIntent.act, mainIntent.pwd)
    is MainIntent.Logout -> println("退出登录")
  }
}

fun userLoginRequest(act: String, pwd: String) {
  println("${act} is load in system with ${pwd}")
}

// 密封接口
//
// 主要的方式就是使用到多个密封接口
// 可以进行密封类多实现，
sealed interface Weapon {}

sealed interface Health {}

// 多接口实现
class Player : Health, Weapon

class Founder : Health, Weapon

class Killer : Health, Weapon

class Enemy : Health, Weapon

fun handleHealth(role: Health) {
  when (role) {
    is Player -> TODO()
    is Founder -> TODO()
    is Killer -> TODO()
    is Enemy -> TODO()
    else -> TODO()
  }
}
// 在函数内部进行处理的时候可以使用模式匹配进行处理
fun handleWeapon(role: Weapon) {
  when (role) {
    is Player -> TODO()
    is Founder -> TODO()
    is Killer -> TODO()
    is Enemy -> TODO()
    else -> TODO()
  }
}
